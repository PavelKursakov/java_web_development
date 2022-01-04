import by.epam.lab.Byn;
import by.epam.lab.Purchase;
import by.epam.lab.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
            sc.useLocale(Locale.ENGLISH);
            final int PURCHASE_NUMBER = 6;
            Purchase[] purchases = new Purchase[PURCHASE_NUMBER];
            Purchase purchaseMaxCost = new Purchase("Milk",new Byn(0),0);
            boolean purchaseEquals = true;
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(sc);
                System.out.println(purchases[i]);
                if (purchases[i].getCost().compareTo(purchaseMaxCost.getCost()) > 0) {
                    purchaseMaxCost = purchases[i];
                }
                if (!purchases[0].equals(purchases[i])){
                    purchaseEquals = false;
                }

            }

            if (purchaseEquals) {
                System.out.println("All purchases are equals!");
            } else {
                System.out.println("Not all purchases are equals!");
            }
            System.out.println("Purchase with max cost: " + purchaseMaxCost);

        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }
}
