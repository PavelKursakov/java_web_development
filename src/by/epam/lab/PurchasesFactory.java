package by.epam.lab;

import java.util.Scanner;

public class PurchasesFactory {
    private enum PurchaseKind {
        GENERAL_PURCHASE,
        PRICE_DISCOUNT,
        DISCOUNT_PURCHASE
    }

    public static Purchase getPurchaseFromFactory(Scanner sc) {
        String id = sc.next();
        PurchaseKind kind = PurchaseKind.valueOf(id);
        switch (kind) {
            case GENERAL_PURCHASE:
                return new Purchase(sc);
            case PRICE_DISCOUNT:
                return new PriceDiscountPurchase(sc);
            case DISCOUNT_PURCHASE:
                return new DiscountPurchase(sc);
            default:
                throw new IllegalArgumentException();
        }
    }

}
