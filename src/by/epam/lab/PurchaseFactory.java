package by.epam.lab;

import by.epam.lab.beans.PriceDiscountPurchase;
import by.epam.lab.beans.Purchase;

import static by.epam.lab.Constants.*;

import java.util.Scanner;

public class PurchaseFactory {
    public enum PurchaseKind {
        GENERAL_PURCHASE {
            Purchase getPurchase(String[] elements) {
                return new Purchase(elements);
            }
        },
        PRICE_DISCOUNT {
            Purchase getPurchase(String[] elements) {
                return new PriceDiscountPurchase(elements);
            }
        };

        abstract Purchase getPurchase(String[] elements);
    }

    public static Purchase getPurchaseFromFactory(Scanner sc) {
        Purchase purchase = null;
        String s = sc.nextLine();
        String[] elements = s.split(DELIMITER);
        if (elements.length < MIN_PURCHASE_LENGTH || elements.length > MAX_PURCHASE_LENGTH) {
            System.err.println(s + WRONG_ARGUMENT_MESSAGE);
        }
        if (elements.length == MAX_PURCHASE_LENGTH) {
            purchase = PurchaseKind.PRICE_DISCOUNT.getPurchase(elements);
        } else if (elements.length == MIN_PURCHASE_LENGTH) {
            purchase = PurchaseKind.GENERAL_PURCHASE.getPurchase(elements);
        }
        return purchase;
    }
}
