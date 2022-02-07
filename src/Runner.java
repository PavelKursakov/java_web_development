import by.epam.lab.*;
import by.epam.lab.beans.Byn;
import by.epam.lab.beans.PricePurchase;
import by.epam.lab.beans.Purchase;
import by.epam.lab.enums.WeekDay;
import by.epam.lab.factories.PurchaseFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader(CSV_NAME))) {
            Map<Purchase, WeekDay> firstDayMap = new HashMap<>();
            Map<Purchase, WeekDay> lastDayMap = new HashMap<>();
            Map<WeekDay, List<Purchase>> enumDayMap = new HashMap<>();
            List<Purchase> pricePurchases = new ArrayList<>();
            while (sc.hasNextLine()) {
                List<Purchase> purchaseList = new ArrayList<>();
                String s = sc.nextLine();
                Purchase purchase = PurchaseFactory.getPurchaseFromFactory(s);
                if (purchase.getClass() == PricePurchase.class) {
                    pricePurchases.add(purchase);
                }
                WeekDay day = WeekDay.valueOf(sc.nextLine());
                if (!firstDayMap.containsKey(purchase)) {
                    firstDayMap.put(purchase, day);
                }
                lastDayMap.put(purchase, day);

                if (enumDayMap.containsKey(day)) {
                    enumDayMap.get(day).add(purchase);
                } else {
                    purchaseList.add(purchase);
                    enumDayMap.put(day, purchaseList);
                }
            }
            printMap(lastDayMap);
            printMap(firstDayMap);

            System.out.println(FIRST_DAY_BREAD + searchDay(firstDayMap, new Purchase(BREAD,
                    new Byn(155), 0)));
            System.out.println(LAST_DAY_BREAD + searchDay(lastDayMap, new Purchase(BREAD,
                    new Byn(155), 0)));
            System.out.println(FIRST_DAY_BREAD + searchDay(firstDayMap, new Purchase(BREAD,
                    new Byn(170), 0)));
            removeElements(lastDayMap, MEAT_NAME_FOR_DELETE);
            removeElements(firstDayMap, FRIDAY_FOR_DELETE);

            printMap(lastDayMap);
            printMap(firstDayMap);
            getTotalCost(pricePurchases);
            printMap(enumDayMap);
            for (Map.Entry<WeekDay, List<Purchase>> entry : enumDayMap.entrySet()) {
                getTotalCost(entry.getValue());
            }
            System.out.println(searchDay(enumDayMap, WeekDay.MONDAY));

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

    }

    private static <E extends Map<?, ?>> void printMap(E currentMap) {
        for (Map.Entry<?, ?> entry : currentMap.entrySet()) {
            System.out.println(entry.getKey() + COLON + entry.getValue());
        }
        System.out.println();
    }

    private static <K, E extends Map<?, ?>> String searchDay(E currentMap, K key) {
        String requiredDay = Constants.REQUIRED_IS_NOT_FOUND;
        if (currentMap.containsKey(key)) {
            requiredDay = currentMap.get(key).toString();
        }
        return requiredDay;
    }

    private static <E extends Map<?, ?>> void removeElements(E currentMap, String key) {
        String result;
        Iterator<? extends Map.Entry<?, ?>> iterator = currentMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<?, ?> entry = iterator.next();
            if (entry.toString().contains(key)) {
                result = entry.getKey() + WAS_DELETED;
                iterator.remove();
                System.out.println(result);
            }
        }
    }

    private static <E extends List<? extends Purchase>> void getTotalCost(E list) {
        Byn totalCost = new Byn();
        for (Purchase purchase : list) {
            totalCost = totalCost.add(purchase.getCost());
        }
        System.out.println(TOTAL_COST + totalCost);
    }
}
