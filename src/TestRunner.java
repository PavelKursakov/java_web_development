import by.epam.lab.comparators.PurchaseComparator;
import by.epam.lab.PurchaseFactory;
import by.epam.lab.beans.Byn;
import by.epam.lab.beans.PriceDiscountPurchase;
import by.epam.lab.beans.Purchase;
import by.epam.lab.beans.PurchaseList;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static by.epam.lab.Constants.*;

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
                ("Coffee", new Byn(100), new Byn(20), 3);
        Assert.assertEquals(new Byn(500), generalPurchase.getCost());
        Assert.assertEquals(new Byn(240), priceDiscountPurchase.getCost());
    }

    @Test
    public void purchaseListsMethods() {
        PurchaseList purchaseList = new PurchaseList(CSV_NAME);
        Purchase p1 = new Purchase("Milk", new Byn(150), 3);
        Purchase p2 = new PriceDiscountPurchase
                ("Fish", new Byn(100), new Byn(10), 3);
//        Test method addPurchase
        purchaseList.addPurchase(1, p1);
        purchaseList.addPurchase(120, p2);
        Assert.assertEquals(purchaseList.getPurchasesList().get(1), p1);
        Assert.assertEquals(purchaseList.getPurchasesList().
                get(purchaseList.getPurchasesList().size() - 1), p2);
//      Test method getTotalCost
        Assert.assertEquals(new Byn(5412), purchaseList.getTotalCost());
//        Test method search
        boolean purchase = purchaseList.searchElement
                (new Purchase("Oil", new Byn(50), 9));
        Assert.assertTrue(purchase);
//      Test method delete
        boolean purchaseAreDeleted = purchaseList.deletePurchase(0, 1);
        Assert.assertTrue(purchaseAreDeleted);
//      Test method sortList
        PurchaseList purchaseList1 = new PurchaseList(CSV_NAME);
        List<Purchase> list = new ArrayList<>(purchaseList1.getPurchasesList());
        purchaseList1.sortList();
        Collections.sort(list, new PurchaseComparator());
        boolean purchasesAreEqual = true;
        for (int i = 0; i < purchaseList1.getPurchasesList().size(); i++) {
            if (!purchaseList1.getPurchasesList().get(i).equals(list.get(i))) {
                purchasesAreEqual = false;
            }
        }
        Assert.assertTrue(purchasesAreEqual);
    }

    @Test
    public void testGetPurchaseFromFactory() {
        Scanner sc1 = new Scanner("Milk;150;3");
        Scanner sc2 = new Scanner("Fish;100;3;10");
        Purchase p1 = new Purchase("Milk", new Byn(150), 3);
        Purchase p2 = new PriceDiscountPurchase
                ("Fish", new Byn(100), new Byn(10), 3);
        Assert.assertEquals(p1, PurchaseFactory.getPurchaseFromFactory(sc1));
        Assert.assertEquals(p2, PurchaseFactory.getPurchaseFromFactory(sc2));
    }
}
