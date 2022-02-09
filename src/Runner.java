import by.epam.lab.beans.Byn;
import by.epam.lab.beans.PricePurchase;
import by.epam.lab.beans.Purchase;
import by.epam.lab.enums.WeekDay;
import by.epam.lab.factories.PurchaseFactory;
import by.epam.lab.interfaces.EntryChecker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader(CSV_NAME))) {
            Map<Purchase, WeekDay> firstDayMap = new HashMap<>();
            Map<Purchase, WeekDay> lastDayMap = new HashMap<>();
            Map<WeekDay, List<Purchase>> dayPurchaseMap = new EnumMap<>(WeekDay.class);
            List<PricePurchase> pricePurchases = new ArrayList<>();
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                Purchase purchase = PurchaseFactory.getPurchaseFromFactory(currentLine);
                if (purchase.getClass() == PricePurchase.class) {
                    pricePurchases.add((PricePurchase) purchase);
                }
                WeekDay day = WeekDay.valueOf(sc.nextLine());
                lastDayMap.put(purchase, day);
                if (!firstDayMap.containsKey(purchase)) {
                    firstDayMap.put(purchase, day);
                }
                List<Purchase> list = dayPurchaseMap.get(day);
                if (list == null) {
                    dayPurchaseMap.put(day, list = new ArrayList<>());
                }
                list.add(purchase);
            }
            printMap(lastDayMap, LAST_DAY_MAP);
            printMap(firstDayMap, FIRST_DAY_MAP);

            Purchase p1 = new Purchase("bread", new Byn(155), 0);
            Purchase p2 = new Purchase("bread", new Byn(170), 0);
            searchDay(firstDayMap, p1, HEADER_DAY + p1.getName() + DELIMITER + p1.getPrice());
            searchDay(lastDayMap, p1, HEADER_DAY + p1.getName() + DELIMITER + p1.getPrice());
            searchDay(firstDayMap, p2, HEADER_DAY + p2.getName() + DELIMITER + p2.getPrice());
            removeElements(lastDayMap, new EntryChecker<Purchase, WeekDay>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return MEAT_NAME_FOR_DELETE.equals(entry.getKey().getName());
                }
            });
            removeElements(firstDayMap, new EntryChecker<Purchase, WeekDay>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return WeekDay.FRIDAY == entry.getValue();
                }
            });

            printMap(lastDayMap, LAST_DAY_MAP);
            printMap(firstDayMap, FIRST_DAY_MAP);
            getTotalCost(pricePurchases);
            printMap(dayPurchaseMap, DAY_PURCHASE_MAP);
            for (Map.Entry<WeekDay, List<Purchase>> entry : dayPurchaseMap.entrySet()) {
                System.out.println(entry.getKey() + COLON);
                getTotalCost(entry.getValue());
            }
            searchDay(dayPurchaseMap, WeekDay.MONDAY, HEADER_DAY + WeekDay.MONDAY);

            removeElements(dayPurchaseMap, new EntryChecker<WeekDay, List<Purchase>>() {
                @Override
                public boolean check(Map.Entry<WeekDay, List<Purchase>> entry) {
                    boolean haveMilk = false;
                    List<Purchase> list = entry.getValue();
                    for (Purchase purchase : list) {
                        if (MILK_NAME_FOR_DELETE.equals(purchase.getName())) {
                            haveMilk = true;
                            break;
                        }
                    }
                    return haveMilk;
                }
            });
            printMap(dayPurchaseMap, DAY_PURCHASE_MAP);
        } catch (FileNotFoundException e) {
            System.err.println(INPUT_FILE_IS_NOT_FOUND);
        }

    }

    private static <K, V> void printMap(Map<K, V> currentMap, String message) {
        System.out.println(TABULATION + message);
        for (Map.Entry<K, V> entry : currentMap.entrySet()) {
            System.out.println(entry.getKey() + COLON + entry.getValue());
        }
        System.out.println();
    }

    private static <K, V> void searchDay(Map<K, V> currentMap, K key, String header) {
        V currentValue = currentMap.get(key);
        System.out.println(header + COLON + (currentValue != null ? currentValue :
                REQUIRED_IS_NOT_FOUND));
    }

    private static <K, V> void removeElements(Map<K, V> currentMap, EntryChecker<K, V> checker) {
        for (Iterator<Map.Entry<K, V>> iterator =
             currentMap.entrySet().iterator(); iterator.hasNext(); ) {
            if (checker.check(iterator.next())) {
                iterator.remove();
            }
        }
    }

    private static void getTotalCost(List<? extends Purchase> list) {
        Byn totalCost = new Byn();
        for (Purchase purchase : list) {
            totalCost = totalCost.add(purchase.getCost());
        }
        System.out.println(TOTAL_COST + totalCost);
    }
}
