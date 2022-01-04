package by.epam.lab;

import java.util.Scanner;

public class PurchasesFactory {
    private enum PurchaseKind {

        GENERAL_PURCHASE {
            Purchase getPurchase(Scanner sc) {
                return new Purchase(sc);
            }
        },
        PRICE_DISCOUNT {
            Purchase getPurchase(Scanner sc) {
                return new PriceDiscountPurchase(sc);
            }
        },
        DISCOUNT_PURCHASE {
            Purchase getPurchase(Scanner sc) {
                return new DiscountPurchase(sc);
            }
        };

        abstract Purchase getPurchase(Scanner sc);
    }

    public static Purchase getPurchaseFromFactory(Scanner sc) {
        String id = sc.next();
        return PurchaseKind.valueOf(id).getPurchase(sc);
    }
}

