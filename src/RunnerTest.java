import by.epam.lab.Converter;
import by.epam.lab.Purchase;
import by.epam.lab.WeekDay;
import org.junit.Assert;
import org.junit.Test;


public class RunnerTest {


    @Test
    public void testPurchaseConstructor() {
        Purchase p1 = new Purchase(5, 20.0, WeekDay.MONDAY);
        Purchase p2 = new Purchase(5,20.0,1);
       Assert.assertEquals(p1.getWeekDay(), p2.getWeekDay());
    }

    @Test
    public void testGetCost() {
        Purchase p1 = new Purchase(3, 25, WeekDay.FRIDAY);
        Assert.assertEquals(1700, p1.getCost());
        Purchase p2 = new Purchase(5, 32.2, WeekDay.SATURDAY);
        Assert.assertEquals(2500, p2.getCost());
    }

    @Test
    public void testStringConvert() {
        Assert.assertEquals("3.50", Converter.convert(350));
        Assert.assertEquals("3.05", Converter.convert(305));
        Assert.assertEquals("3.00", Converter.convert(300));
        Assert.assertEquals("0.00", Converter.convert(0));
        Assert.assertEquals("1005.00", Converter.convert(100500));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void testIndexCheck(){
        Purchase purchase1 = new Purchase(5,33.0,11);
        Purchase purchase2 = new Purchase(5,33.0,-1);
    }
}