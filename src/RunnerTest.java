import by.epam.lab.Purchase;
import by.epam.lab.WeekDay;
import org.junit.Assert;
import org.junit.Test;


public class RunnerTest {

    private static String convert(int coins) {
        return String.format("%d.%02d", coins / 100, coins % 100);
    }

    @Test
    public void testPurchaseConstructor() {
        Purchase p1 = new Purchase(5, 20.0, WeekDay.MONDAY);
        Assert.assertEquals(5, p1.getNumberOfUnits());
        Assert.assertEquals(20.0, p1.getPercent(), 0.01);
        Assert.assertEquals(WeekDay.MONDAY, p1.getWeekDay());
    }

    @Test
    public void testGetCost() {
        Purchase p1 = new Purchase(3, 25.0, WeekDay.FRIDAY);
        Assert.assertEquals(1700, p1.getCost());
        Purchase p2 = new Purchase(5, 30.0, WeekDay.SATURDAY);
        Assert.assertEquals(2600, p2.getCost());
    }

    @Test
    public void testStringConvert() {
        Purchase p1 = new Purchase(3, 25.0, WeekDay.MONDAY);
        Assert.assertEquals("17.00", convert(p1.getCost()));
        Assert.assertEquals("7.50", convert(Purchase.PRICE));
        Assert.assertEquals("0.01", convert(1));
    }
}