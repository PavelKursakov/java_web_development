package by.epam.lub.beans;

import by.epam.lub.enums.RoundMethod;

import static by.epam.lub.Constants.*;

public class Purchase {
    private Priceable item;
    private Number quantityOfItem;

    public Purchase() {
    }

    public Purchase(Priceable item, Number quantityOfItem) {
        this.item = item;
        this.quantityOfItem = quantityOfItem;
    }

    public Priceable getItem() {
        return item;
    }

    public void setItem(Priceable item) {
        this.item = item;
    }

    public Number getQuantityOfItem() {
        return quantityOfItem;
    }

    public void setQuantityOfItem(double quantityOfItem) {
        this.quantityOfItem = quantityOfItem;
    }

    public Byn getCost() {
        return item.getPrice().mul(quantityOfItem.doubleValue(), RoundMethod.ROUND, 0);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + DELIMITER + item +
                DELIMITER + quantityOfItem + DELIMITER + getCost();
    }
}
