package by.epam.lab;

public class Purchase implements Comparable<Purchase> {
    public final static String NAME = "Milk";
    public final static int PRICE = 750;
    private int numberOfUnits;
    private double percent;
    private WeekDay weekDay;

    public Purchase() {

    }

    public Purchase(int numberOfUnits, double percent, WeekDay weekDay) {
        this.numberOfUnits = numberOfUnits;
        this.percent = percent;
        this.weekDay = weekDay;
    }

    public Purchase(int numberOfUnits, double percent, int day) {
        this(numberOfUnits, percent, WeekDay.values()[day]);
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public int getCost() {
        return (int) Math.round((PRICE * numberOfUnits * (100 - percent) / 100) / 100) * 100;
    }

    @Override
    public String toString() {
        return numberOfUnits + ";" + percent + ";" + weekDay + ";" + Converter.convert(getCost());
    }

    @Override
    public int compareTo(Purchase purchase) {
        return numberOfUnits - purchase.numberOfUnits;
    }
}
