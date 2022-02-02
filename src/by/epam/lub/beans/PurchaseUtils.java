package by.epam.lub.beans;

import java.util.Arrays;
import static by.epam.lub.Constants.*;

public class PurchaseUtils {
    private final Purchase purchase;

    public PurchaseUtils() {
        this(null);
    }

    public PurchaseUtils(Purchase purchase) {
        this.purchase = purchase;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void printPurchase() {
        System.out.println(purchase);
    }

    public void printCost() {
        System.out.println(COST + purchase.getCost());
    }

    public void printCostDiff(Purchase p) {
        int xxx = purchase.compareTo(p);
        if (xxx > 0) {
            System.out.println(POSITIVE + DIFF + new Byn(xxx));
        }
        if (xxx < 0) {
            System.out.println(NEGATIVE + DIFF + new Byn(xxx));
        }
        if (xxx == 0) {
            System.out.println(EMPTY_STRING + DIFF + new Byn(xxx));
        }
    }

    public void printSameCost(Purchase[] purchases) {
        Arrays.sort(purchases);
        int index = Arrays.binarySearch(purchases, purchase);
        if (index < 0) {
            System.out.println(PURCHASE_IS_NOT_FOUND);
        } else {
            System.out.println(PURCHASE_WITH_SAME_COST + purchases[index]);
        }
    }
}
