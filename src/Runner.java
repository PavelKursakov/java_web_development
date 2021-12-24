import by.epam.lab.BusinessTrip;

public class Runner {
    public static void main(String[] args) {
        BusinessTrip[] businessTrips = {
                new BusinessTrip("Pavel", 1540, 5),
                new BusinessTrip("Vlad", 1710, 7),
                null,
                new BusinessTrip("Kirill", 734, 11),
                new BusinessTrip("Vova", 2120, 3),
                new BusinessTrip()
        };
        int maxTotal = 0;
        int count = 0;
        int index = 0;
        for (BusinessTrip businessTrip : businessTrips) {
            if (businessTrip == null) {
                count++;
                continue;
            }
            businessTrip.show();
            if (businessTrip.getTotal() > maxTotal) {
                maxTotal = businessTrip.getTotal();
                index = count;
            }
            count++;
        }
        System.out.println("Max total trip: " + businessTrips[index]);
        businessTrips[businessTrips.length - 1].setExpenses(211);
        System.out.println("Duration = " + businessTrips[0].getDaysNumber() +
                businessTrips[1].getDaysNumber());
        for (BusinessTrip businessTrip : businessTrips) {
            System.out.println(businessTrip);
        }
    }
}
