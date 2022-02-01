import by.epam.lab.PurchaseFactory;
import by.epam.lab.beans.Byn;
import by.epam.lab.beans.PriceDiscountPurchase;
import by.epam.lab.beans.Purchase;
import by.epam.lab.beans.PurchaseList;
import by.epam.lab.exceptions.CsvLineException;
import org.junit.Assert;
import org.junit.Test;

public class TestRunner {

    @Test
    public void testClassByn() {
        Byn byn1 = new Byn(120);
        Byn byn2 = new Byn(210);
        byn1 = byn1.add(byn2);
        Assert.assertEquals(new Byn(330), byn1);
        Assert.assertEquals("3,30", byn1.toString());
        byn1 = byn1.sub(new Byn(150));
        Assert.assertEquals(new Byn(180), byn1);
        byn1 = byn1.mull(5);
        Assert.assertEquals(new Byn(900), byn1);
    }

    @Test
    public void testGetCost() {
        Purchase generalPurchase = new Purchase("Milk", new Byn(250), 2);
        Purchase priceDiscountPurchase = new PriceDiscountPurchase
                ("Coffee", new Byn(100), 3, new Byn(20));
        Assert.assertEquals(new Byn(500), generalPurchase.getCost());
        Assert.assertEquals(new Byn(240), priceDiscountPurchase.getCost());
    }

    @Test
    public void purchaseListsMethods() {
        PurchaseList purchaseList = new PurchaseList();
        Purchase p1 = new Purchase("Milk", new Byn(150), 3);
        Purchase p2 = new PriceDiscountPurchase
                ("Fish", new Byn(100), 3, new Byn(10));
//        Test method addPurchase
        purchaseList.addPurchase(120, p1);
        purchaseList.addPurchase(1, p2);
        System.out.println(purchaseList);
        String[] purchases = purchaseList.toString().split("\n");
        Assert.assertEquals(purchases[0], p1.toString());
        Assert.assertEquals(purchases[purchases.length - 1], p2.toString());
//      Test method getTotalCost
        Assert.assertEquals(new Byn(720), purchaseList.getTotalCost());
//        Test method search
        int purchaseID = purchaseList.searchElement
                (new Purchase("Fish", new Byn(90), 3));
        Assert.assertEquals(p1.toString(), purchases[purchaseID]);
//      Test method delete
        int numberOfDeletedElements = purchaseList.deletePurchase(0, 1);
        Assert.assertEquals(1,numberOfDeletedElements);
//      Test method sortList
        PurchaseList purchaseList1 = new PurchaseList();
        Purchase purchaseWithMaxCost = new Purchase("Milk", new Byn(15000), 5);
        Purchase purchaseWithMinCost = new Purchase("Coffee", new Byn(1), 1);
        Purchase purchaseWithMeanCost = new Purchase("Oil", new Byn(500), 3);
//      Add purchase with Max cost into the first position of collection
        purchaseList1.addPurchase(0, purchaseWithMaxCost);
//      Add purchase with Min cost into the mid of collection
        purchaseList1.addPurchase(1, purchaseWithMinCost);
//      Add purchase with Mean cost into the last position of collection
        purchaseList1.addPurchase(2, purchaseWithMeanCost);
        purchaseList1.sortList();
        String[] strings = purchaseList1.toString().split("\n");
        Assert.assertEquals(strings[0], purchaseWithMinCost.toString());
        Assert.assertEquals(strings[1], purchaseWithMeanCost.toString());
        Assert.assertEquals(strings[2], purchaseWithMaxCost.toString());

    }

    @Test
    public void testGetPurchaseFromFactory() throws CsvLineException {
        String s1 = "Milk;150;3";
        String s2 = "Fish;100;3;10";
        Purchase p1 = new Purchase("Milk", new Byn(150), 3);
        Purchase p2 = new PriceDiscountPurchase
                ("Fish", new Byn(100), 3, new Byn(10));
        Assert.assertEquals(p1.toString(), PurchaseFactory.getPurchaseFromFactory(s1).toString());
        Assert.assertEquals(p2.toString(), PurchaseFactory.getPurchaseFromFactory(s2).toString());
    }
}
