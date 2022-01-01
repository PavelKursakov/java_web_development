package by.epam.lab;

public class Converter {
    public static String convert(int coins) {
        return String.format("%d.%02d", coins / 100, coins % 100);
    }
}
