import by.epam.lab.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        final Product MILK = new Product("Milk", new Byn(100));
        AbstractPurchase[] purchases = {
                new DiscountPurchase(MILK, 4, 10.0),
                new DiscountPurchase(MILK, 3, 15.0),
                new PriceDiscountPurchase(MILK, 5, new Byn(32)),
                new PriceDiscountPurchase(MILK, 2, new Byn(25)),
                new TransportExpensesPurchase(MILK, 4, new Byn(140)),
                new TransportExpensesPurchase(MILK, 3, new Byn(65))
        };
        showArray(purchases);
        Arrays.sort(purchases);
        System.out.println("Array after sorting: \n");
        showArray(purchases);
        System.out.println("Min cost: " + purchases[purchases.length - 1].getCost());

        int index = search(purchases, new DiscountPurchase(MILK, 5, 0.0));

        if (index < 0) {
            System.out.println("Element is not found.");
        } else {
            System.out.println("\nRequired element: " + purchases[index]);
        }

    }

    public static int search(AbstractPurchase[] purchases, AbstractPurchase purchase) {
        return Arrays.binarySearch(purchases, purchase);
    }

    private static void showArray(AbstractPurchase[] array) {
        for (AbstractPurchase abstractPurchase : array) {
            System.out.println(abstractPurchase + "\n");
        }
    }
}
