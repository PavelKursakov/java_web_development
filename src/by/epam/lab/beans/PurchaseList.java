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

    public PurchaseList(String csvName, Comparator<Purchase> comparator) {
        this.comparator = comparator;
        purchasesList = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                Purchase purchase = null;
                try {
                    purchase = PurchaseFactory.getPurchaseFromFactory(s);
                } catch (CsvLineException e) {
                    System.err.println(e);
                }
                if (purchase != null) {
                    purchasesList.add(purchase);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_NOT_FOUND);
            purchasesList = new ArrayList<>();
        }
    }

    public List<Purchase> getPurchasesList() {
        List<Purchase> purchases = new ArrayList<>();
        for (Purchase purchase: purchasesList) {
            purchases.add(purchase.getClone());
        }
        return purchases;
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

    public boolean deletePurchase(int indexFrom, int indexTo) {
        boolean purchasesAreDeleted = false;
        if (indexFrom < 0 || indexFrom >= purchasesList.size()) {
            System.out.println(WRONG_INDEX + indexFrom);
        } else if (indexTo < 0 || indexTo >= purchasesList.size()) {
            System.out.println(WRONG_INDEX + indexTo);
        } else {
            purchasesList.removeAll(purchasesList.subList(indexFrom,indexTo));
            purchasesAreDeleted = true;
        }
        return purchasesAreDeleted;
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

    public boolean searchElement(Purchase purchase) {
        boolean elementIsFound = false;
        if (!listIsSorted) {
            sortList();
        }
        int index = Collections.binarySearch(purchasesList, purchase, new PurchaseComparator());
        if (index >= 0) {
            elementIsFound = true;
        }
        return elementIsFound;
    }

    public void show() {
        for (Purchase purchase : purchasesList) {
            System.out.println(purchase);
        }
    }
}
