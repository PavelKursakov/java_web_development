import by.epam.lab.Purchase;
import by.epam.lab.WeekDay;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
            sc.useLocale(Locale.ENGLISH);
            final int PURCHASE_NUMBER = sc.nextInt();
            Purchase[] purchases = new Purchase[PURCHASE_NUMBER];
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = new Purchase(
                        sc.nextInt(),
                        sc.nextDouble(),
                        WeekDay.values()[sc.nextInt()]
                );
            }

            System.out.println(convert(Purchase.PRICE) + ";" +  Purchase.NAME);
            for (Purchase purchase: purchases) {
                System.out.println(purchase);
            }

            int totalCost = 0;
            int sumOfMonday = 0;
            int maxCost = 0;
            WeekDay dayWithMaxCost = null;
            for (Purchase purchase : purchases) {
                totalCost += purchase.getCost();
                if (purchase.getWeekDay() == WeekDay.MONDAY) {
                    sumOfMonday += purchase.getCost();
                }
                if (purchase.getCost() > maxCost) {
                    maxCost = purchase.getCost();
                    dayWithMaxCost = purchase.getWeekDay();
                }
            }

            double meanCost = 0;

            if (PURCHASE_NUMBER != 0){
                meanCost = totalCost / PURCHASE_NUMBER;
            }

            System.out.printf("Mean cost = %.3f \n",meanCost);
            System.out.println("The total cost on Monday = " + convert(sumOfMonday));
            System.out.println("Day with max cost is " + dayWithMaxCost);

            Arrays.sort(purchases);

            System.out.println(convert(Purchase.PRICE) + ";" +  Purchase.NAME);
            for (Purchase purchase: purchases) {
                System.out.println(purchase);
            }
           int requiredIndex = Arrays.binarySearch(purchases,new Purchase
                   (5,0,WeekDay.MONDAY));

            if(requiredIndex >= 0) {
                System.out.println(purchases[requiredIndex]);
            }else {
                System.out.println("Required purchase is not found");
            }

        } catch (Exception e) {
            System.out.println("File is not found " + e);
        }

    }
    private static String convert(int coins) {
        return String.format("%d.%02d", coins / 100, coins % 100);
    }
}
