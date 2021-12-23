package by.epam.lab;

public class BusinessTrip {
    private final static int DAILY = 750;
    private String employee;
    private int transportationExpenses;
    private int numberOfDays;

    public BusinessTrip() {
    }

    public BusinessTrip(String employee, int transportationExpenses, int numberOfDays) {
        this.employee = employee;
        this.transportationExpenses = transportationExpenses;
        this.numberOfDays = numberOfDays;
    }
    public int getTotal() {
        return transportationExpenses + DAILY * numberOfDays;
    }

    private static String convert(int penny) {
        String result;
        int rub = penny / 100;
        int pennyResult = penny % 100;
        if (pennyResult < 10) {
            result = rub + ".0" + pennyResult;
        } else {
            result = rub + "." + pennyResult;
        }
        return result;
    }

    public void show() {
        System.out.println("rate = " + convert(DAILY) + "\n" + "Employee's account = " + employee + "\n" + "transport = " + convert(transportationExpenses) + "\n" +
                "days = " + numberOfDays + "\n" + "total = " + convert(getTotal()));
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public int getTransportationExpenses() {
        return transportationExpenses;
    }

    public void setTransportationExpenses(int transportationExpenses) {
        this.transportationExpenses = transportationExpenses;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }


    @Override
    public String toString() {
        return employee + ";" + convert(transportationExpenses) + ";" + numberOfDays + ";" + convert(getTotal());
    }
}
