import by.epam.lab.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestRunner {
    @Test
    public void testClassByn() {
        Byn byn1 = new Byn(120);
        Byn byn2 = new Byn(210);
        Byn bynAdd = byn1.add(byn2);
        Assert.assertEquals(new Byn(330), bynAdd);
        Assert.assertEquals("3.30", bynAdd.toString());
        Byn bynMull = byn1.mul(5);
        Assert.assertEquals(new Byn(600), bynMull);
        Byn bynSub = byn2.sub(byn1);
        Assert.assertEquals(new Byn(90), bynSub);
    }

    @Test
    public void testMethodGetCost() {
        Product milk = new Product("milk", new Byn(100));
        PriceDiscountPurchase p1 =
                new PriceDiscountPurchase(milk, 5, new Byn(32));
        DiscountPurchase p2 =
                new DiscountPurchase(milk, 3, 15.0);
        TransportExpensesPurchase p3 =
                new TransportExpensesPurchase(milk, 4, new Byn(140));
        Assert.assertEquals(new Byn(300), p1.getCost());
        Assert.assertEquals(new Byn(300), p2.getCost());
        Assert.assertEquals(new Byn(500), p3.getCost());
    }

    @Test
    public void testRequiredElementPosition() {
        Product milk = new Product("milk", new Byn(100));
        AbstractPurchase[] purchases = {
                new PriceDiscountPurchase(milk, 5, new Byn(32)),
                new DiscountPurchase(milk, 4, 15.0),
                new TransportExpensesPurchase(milk, 3, new Byn(140)),
                new TransportExpensesPurchase(milk, 4, new Byn(190))
        };
        //Sort array.
        Arrays.sort(purchases);
        //Search element in array, when required element first in array.
        int index = Runner.search(purchases, new TransportExpensesPurchase(
                milk, 5, new Byn(50)));
        Assert.assertEquals(purchases[0], purchases[index]);
        //Search element in array, when required element last in array.
        index = Runner.search(purchases, new TransportExpensesPurchase(
                milk, 3, new Byn(90)));
        Assert.assertEquals(purchases[purchases.length - 1], purchases[index]);
        //Check required element more than max element or little than min element
        AbstractPurchase requiredElement =
                new PriceDiscountPurchase(milk, 15, new Byn(0));
        Assert.assertTrue(requiredElement.getCost().
                compareTo(purchases[purchases.length - 1].getCost()) < 0 ||
                requiredElement.getCost().compareTo(purchases[0].getCost()) > 0);
    }
}
