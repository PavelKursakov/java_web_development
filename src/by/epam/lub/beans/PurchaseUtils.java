package by.epam.lub.beans;

import static by.epam.lub.Constants.*;

public class PurchaseUtils<T extends Priceable, N extends Number> {
    private Purchase<T, N> purchase;

    public PurchaseUtils() {
        this(null);
    }

    public PurchaseUtils(Purchase<T, N> purchase) {
        this.purchase = purchase;
    }

    public Purchase<T, N> getPurchase() {
        return purchase;
    }

    public void printPurchase() {
        System.out.println(purchase);
    }

    public void printCost() {
        System.out.println(COST + purchase.getCost() + BYN);
    }

    public <E extends Purchase<? extends Priceable, ? extends Number>> void printCostDiff(E p) {
        String diffPurchase = EMPTY_STRING;
        Byn cost1 = purchase.getCost();
        Byn cost2 = p.getCost();
        int diff = cost1.compareTo(cost2);
        Byn diffCost = new Byn(0);
        if (diff > 0) {
            diffCost = cost1.sub(cost2);
            diffPurchase = POSITIVE;
        } else if (diff < 0) {
            diffCost = cost2.sub(cost1);
            diffPurchase = NEGATIVE;
        }
        System.out.println(diffPurchase + DIFF + diffCost);
    }

    @SafeVarargs
    public final <E extends Purchase<? extends Priceable, ? extends Number>>
    void printSameCost(E... purchases) {
        boolean purchaseWasFound = false;
        for (E purchase1 : purchases) {
            if (purchase.getCost().compareTo(purchase1.getCost()) == 0) {
                purchaseWasFound = true;
                break;
            }
        }
        System.out.println(purchaseWasFound ? PURCHASE_WITH_SAME_COST : PURCHASE_IS_NOT_FOUND);
    }
}
