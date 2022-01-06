import by.epam.lab.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        Product milk = new Product("milk", new Byn(100));
        AbstractPurchase[] purchases = {
                new DiscountPurchase(milk, 4, 10.0),
                new DiscountPurchase(milk, 3, 15.0),
                new PriceDiscountPurchase(milk, 5, new Byn(32)),
                new PriceDiscountPurchase(milk, 2, new Byn(25)),
                new TransportExpensesPurchase(milk, 7, new Byn(140)),
                new TransportExpensesPurchase(milk, 3, new Byn(65))
        };
        showArray(purchases);
        Arrays.sort(purchases);
        System.out.println("Array after sorting: \n");
        showArray(purchases);
        System.out.println("Min cost: " + purchases[purchases.length - 1].getCost());

        int index = search(purchases);

        if (index < 0){
            System.out.println("Element is not found.");
        } else {
            System.out.println("\nRequired element: " + purchases[index]);
        }

    }
    private static int search (AbstractPurchase[] purchases) {
        return Arrays.binarySearch(purchases,new DiscountPurchase(
                new Product("",new Byn(500)),1,0.0));
    }

    private static void showArray(AbstractPurchase[] array) {
        for (AbstractPurchase abstractPurchase : array) {
            System.out.println(abstractPurchase + "\n");
        }
    }
}
