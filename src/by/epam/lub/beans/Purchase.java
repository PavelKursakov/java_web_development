package by.epam.lub.beans;
import by.epam.lub.enums.RoundMethod;

import static by.epam.lub.Constants.*;
public class Purchase implements Comparable<Purchase> {
    private AbstractItem item;
    private Number quantityOfItem;

    public Purchase() {
    }

    public Purchase(AbstractItem item, Number quantityOfItem) {
        this.item = item;
        this.quantityOfItem = quantityOfItem;
    }

    public AbstractItem getItem() {
        return item;
    }

    public void setItem(AbstractItem item) {
        this.item = item;
    }

    public Number getQuantityOfItem() {
        return quantityOfItem;
    }

    public void setQuantityOfItem(double quantityOfItem) {
        this.quantityOfItem = quantityOfItem;
    }

    public Byn getCost() {
        return quantityOfItem.getClass() == Double.class ?
                item.getPrice().mul((double) quantityOfItem, RoundMethod.ROUND, 0) :
                item.getPrice().mul((int) quantityOfItem, RoundMethod.ROUND, 0);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + DELIMITER + item +
                DELIMITER + quantityOfItem + DELIMITER + getCost();
    }

    @Override
    public int compareTo(Purchase purchase) {
        return this.getCost().compareTo(purchase.getCost());
    }
}
