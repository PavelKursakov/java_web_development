package by.epam.lab;

public class BusinessTrip {
    private final static int DAILY_RATE = 950;
    private String account;
    private int expenses;
    private int daysNumber;

    public BusinessTrip() {
    }

    public BusinessTrip(String employee, int transportationExpenses, int numberOfDays) {
        this.account = employee;
        this.expenses = transportationExpenses;
        this.daysNumber = numberOfDays;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getDaysNumber() {
        return daysNumber;
    }

    public void setDaysNumber(int daysNumber) {
        this.daysNumber = daysNumber;
    }

    public int getTotal() {
        return expenses + DAILY_RATE * daysNumber;
    }

    private static String convert(int penny) {
        return String.format("%d.%02d", penny / 100, penny % 100);
    }

    public void show() {
        System.out.println("rate = " + convert(DAILY_RATE) + "\n" + "Employee's account = " +
                account + "\n" + "transport = " + convert(expenses) + "\n" +
                "days = " + daysNumber + "\n" + "total = " + convert(getTotal()));
    }


    @Override
    public String toString() {
        return account + ";" + convert(expenses) + ";" + daysNumber + ";" +
                convert(getTotal());
    }
}
