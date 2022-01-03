import by.epam.lab.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

public class TestRunner {
    @Test
    public void testClassByn(){
        Byn byn1 = new Byn(120);
        Byn byn2 = new Byn(210);
        byn1.add(byn2);
        Assert.assertEquals(new Byn(330),byn1);
        Assert.assertEquals("3.30",byn1.toString());
        byn1.mul(5);
        Assert.assertEquals(new Byn(1650),byn1);
        byn1.diff(new Byn(150));
        Assert.assertEquals(new Byn(1500),byn1);
    }

    @Test
    public void testPurchasesCreate(){
        Scanner sc1 = new Scanner("DISCOUNT_PURCHASE Milk 140 7 0");
        Purchase purchase1 = PurchasesFactory.getPurchaseFromFactory(sc1);
        Scanner sc2 = new Scanner("GENERAL_PURCHASE Milk 140 5");
        Purchase purchase2 = PurchasesFactory.getPurchaseFromFactory(sc2);
        Assert.assertEquals(new DiscountPurchase("Milk",new Byn(140),
                7,0),purchase1);
        Assert.assertEquals(new Purchase("Milk",new Byn(140),5),purchase2);
    }
    @Test
    public void testMethodsGetCostAndEquals(){
        Purchase purchase1 = new Purchase("Milk",new Byn(140),7);
        Purchase purchase2 = new Purchase("Milk",new Byn(140), 7);
        Assert.assertEquals(purchase1,purchase2);
        DiscountPurchase discountPurchase1 = new DiscountPurchase("Milk",
                new Byn(140), 7,0.0);
        Assert.assertEquals(discountPurchase1,purchase1);
        PriceDiscountPurchase priceDiscountPurchase1 = new PriceDiscountPurchase("Milk",
                new Byn(140),10,new Byn(15));
        Assert.assertEquals(priceDiscountPurchase1,purchase2);
        Assert.assertEquals(purchase1.getCost(),discountPurchase1.getCost());
    }
}
