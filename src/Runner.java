import by.epam.lab.Converter;
import by.epam.lab.Purchase;
import by.epam.lab.WeekDay;

import java.io.FileNotFoundException;
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
                        sc.nextInt()
                );
            }
            showInfo(purchases);

            int totalCost = 0;
            int sumOfMonday = 0;
            int maxCost = 0;

            WeekDay dayWithMaxCost = null;

            for (Purchase purchase : purchases) {
                int cost = purchase.getCost();
                totalCost += cost;
                if (purchase.getWeekDay() == WeekDay.MONDAY) {
                    sumOfMonday += cost;
                }
                if (cost > maxCost) {
                    maxCost = cost;
                    dayWithMaxCost = purchase.getWeekDay();
                }
            }

            double meanCost = 0.0;

            if (PURCHASE_NUMBER > 0) {
                meanCost = (double) totalCost / purchases.length / 100;
            }

            System.out.printf("Mean cost = %.3f \n", meanCost);
            System.out.println("The total cost on Monday = " + Converter.convert(sumOfMonday));
            System.out.println("Day with max cost is " + dayWithMaxCost);

            Arrays.sort(purchases);

            showInfo(purchases);

            int requiredIndex = Arrays.binarySearch(purchases, new Purchase
                    (5, 0, WeekDay.MONDAY));

            if (requiredIndex >= 0) {
                System.out.println("Required purchase is " + purchases[requiredIndex]);
            } else {
                System.out.println("Required purchase is not found");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File is not found " + e);
        }

    }

    private static void showInfo(Purchase[] purchases) {
        System.out.println(Converter.convert(Purchase.PRICE) + ";" + Purchase.NAME);
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

}
