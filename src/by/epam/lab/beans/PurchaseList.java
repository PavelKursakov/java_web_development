package by.epam.lab.beans;

import by.epam.lab.comparators.PurchaseComparator;
import by.epam.lab.PurchaseFactory;
import by.epam.lab.exceptions.CsvLineException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lab.Constants.*;

public class PurchaseList {
    private boolean listIsSorted = false;
    private List<Purchase> purchasesList;
    private final Comparator<Purchase> comparator;

    public PurchaseList() {
        this.purchasesList = new ArrayList<>();
        this.comparator = new PurchaseComparator();
        listIsSorted = true;
    }

    public PurchaseList(String csvName, Comparator<Purchase> comparator) {
        this.comparator = comparator;
        purchasesList = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                try {
                    Purchase purchase = PurchaseFactory.getPurchaseFromFactory(s);
                    purchasesList.add(purchase);
                } catch (CsvLineException e) {
                    System.err.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_NOT_FOUND);
            purchasesList = new ArrayList<>();
            listIsSorted = true;
        }
    }

    public void addPurchase(int index, Purchase purchase) {
        if (index >= 0 && index < purchasesList.size()) {
            purchasesList.add(index, purchase);
        } else if (index >= purchasesList.size()) {
            purchasesList.add(purchase);
        } else {
            purchasesList.add(0, purchase);
        }
        listIsSorted = false;
    }

    public int deletePurchase(int indexFrom, int indexTo) {
        if (indexFrom < 0) {
            indexFrom = 0;
        }
        if (indexFrom >= purchasesList.size()) {
            return 0;
        }
        if (indexTo < 0) {
            return 0;
        }
        if (indexTo > purchasesList.size()) {
            indexTo = purchasesList.size();
        }
        purchasesList.subList(indexFrom, indexTo).clear();
        return indexTo - indexFrom;
    }

    public Byn getTotalCost() {
        Byn totalCost = new Byn();
        for (Purchase purchase : purchasesList) {
            totalCost = totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    public void sortList() {
        Collections.sort(purchasesList, comparator);
        listIsSorted = true;
    }

    public int searchElement(Purchase purchase) {
        if (!listIsSorted) {
            sortList();
        }
        return Collections.binarySearch(purchasesList, purchase, new PurchaseComparator());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Purchase purchase : purchasesList) {
            stringBuilder.append(purchase).append("\n");
        }
        return stringBuilder.toString();
    }
}
