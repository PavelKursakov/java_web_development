package by.epam.lab.factories;

import by.epam.lab.beans.PricePurchase;
import by.epam.lab.beans.Purchase;

import static by.epam.lab.Constants.*;

public class PurchaseFactory {
    private enum PurchaseKind {
        GENERAL_PURCHASE {
            protected Purchase getPurchase(String[] elements) {
                return new Purchase(elements);
            }
        },
        PRICE_DISCOUNT {
            protected Purchase getPurchase(String[] elements) {
                return new PricePurchase(elements);
            }
        };

        abstract protected Purchase getPurchase(String[] elements);
    }

    private static PurchaseKind getPurchaseKind(int elementsLength) {
        PurchaseKind purchaseKind = PurchaseKind.GENERAL_PURCHASE;
        if (elementsLength == MAX_PURCHASE_LENGTH) {
            purchaseKind = PurchaseKind.PRICE_DISCOUNT;
        }
        return purchaseKind;
    }

    public static Purchase getPurchaseFromFactory(String csvLine) {
        String[] elements = csvLine.split(DELIMITER);
        return getPurchaseKind(elements.length).getPurchase(elements);
    }
}
