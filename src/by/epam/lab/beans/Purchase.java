package by.epam.lab.beans;

import by.epam.lab.exceptions.NonPositiveArgumentException;
import by.epam.lab.exceptions.WrongArgumentTypeException;

import java.util.Objects;

import static by.epam.lab.Constants.*;

public class Purchase implements Comparable<Purchase> {
    private final String name;
    private final Byn price;
    private final int numberOfUnits;

    public Purchase() {
        throw new IllegalStateException(EMPTY_PURCHASE);
    }

    public Purchase(Purchase purchase) {
        this(purchase.name, purchase.price, purchase.numberOfUnits);
    }

    public Purchase(String name, Byn price, int numberOfUnits) {
        if (name.isEmpty()) {
            throw new WrongArgumentTypeException(EMPTY_NAME);
        }
        if (price.compareTo(new Byn(0)) <= 0 || numberOfUnits <= 0) {
            throw new NonPositiveArgumentException(NONE_POSITIVE_ARGUMENT_PRICE_AND_UNITS);
        }
        this.name = name;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
    }

    public Purchase(String[] elements) {
        this(getArrayForPurchase(elements));
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public Byn getCost() {
        return price.mull(numberOfUnits);
    }

    public Purchase getClone() {
        return new Purchase(name, new Byn(getPrice()), numberOfUnits);
    }

    protected String filedToString() {
        return name + DELIMITER + price + DELIMITER + numberOfUnits;
    }

    private static Purchase getArrayForPurchase(String[] strings) {
        if (strings.length != MIN_PURCHASE_LENGTH) {
            throw new IndexOutOfBoundsException(WRONG_ARGUMENT_MESSAGE);
        }
        return new Purchase(strings[0], new Byn(strings[1]), Integer.parseInt(strings[2]));
    }

    @Override
    public String toString() {
        return filedToString() + DELIMITER + getCost();
    }

    @Override
    public int compareTo(Purchase purchase) {
        return this.getCost().compareTo(purchase.getCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(name, purchase.name) && Objects.equals(price, purchase.price);
    }
}
