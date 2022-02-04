package by.epam.lub.beans;

import by.epam.lub.enums.RoundMethod;

import static by.epam.lub.Constants.*;

public class Purchase<T extends Priceable, N extends Number> {
    private T item;
    private N quantityOfItem;

    public Purchase() {
    }

    public Purchase(T item, N quantityOfItem) {
        this.item = item;
        this.quantityOfItem = quantityOfItem;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Number getQuantityOfItem() {
        return quantityOfItem;
    }

    public void setQuantityOfItem(N quantityOfItem) {
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
