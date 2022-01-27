import by.epam.lab.beans.Byn;
import by.epam.lab.beans.Purchase;
import by.epam.lab.beans.PurchaseList;
import by.epam.lab.comparators.PurchaseComparator;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        PurchaseList purchaseList = new PurchaseList(CSV_NAME,new PurchaseComparator());
        purchaseList.addPurchase(11, new Purchase("cheeps",
                new Byn(200), 5));

        purchaseList.show();
        purchaseList.deletePurchase(0, 3);
        System.out.println();
        purchaseList.sortList();
        purchaseList.searchElement(new Purchase("bread", new Byn(200), 5));
        purchaseList.show();
        System.out.println(TOTAL_COST + purchaseList.getTotalCost());
    }
}

