package by.epam.lab.comparators;

import by.epam.lab.beans.Purchase;

import java.util.Comparator;

public class PurchaseComparator implements Comparator<Purchase> {
    @Override
    public int compare(Purchase p1, Purchase p2) {
        return p1.getCost().compareTo(p2.getCost());
    }
}
