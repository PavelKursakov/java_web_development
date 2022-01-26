package by.epam.lab.beans;

import by.epam.lab.comparators.PurchaseComparator;
import by.epam.lab.PurchaseFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lab.Constants.*;

public class PurchaseList {
    private boolean listIsSorted = false;
    private final List<Purchase> purchasesList = new ArrayList<>();

    public List<Purchase> getPurchasesList() {
        return purchasesList;
    }

    public PurchaseList(String csvName) {
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNextLine()) {
                Purchase purchase;
                try {
                    purchase = PurchaseFactory.getPurchaseFromFactory(sc);
                } catch (NumberFormatException e) {
                    continue;
                }
                if (purchase != null) {
                    purchasesList.add(purchase);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_NOT_FOUND);
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

    public boolean deletePurchase(int indexFrom, int indexTo) {
        boolean purchasesAreDeleted = false;
        if (indexFrom < 0 || indexFrom >= purchasesList.size()) {
            System.out.println(WRONG_INDEX + indexFrom);
        } else if (indexTo < 0 || indexTo >= purchasesList.size()) {
            System.out.println(WRONG_INDEX + indexTo);
        } else {
            for (int i = indexFrom; i < indexTo; i++) {
                purchasesList.remove(i);
                purchasesAreDeleted = true;
            }
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
        Collections.sort(purchasesList, new PurchaseComparator());
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
