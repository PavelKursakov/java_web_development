package by.epam.lab.beans;

import by.epam.lab.exceptions.CsvLineException;
import by.epam.lab.exceptions.NonPositiveArgumentException;

import java.util.Objects;

import static by.epam.lab.Constants.*;

public class Purchase implements Comparable<Purchase> {
    private final String name;
    private final Byn price;
    private final int numberOfUnits;

    public Purchase() {
        this(null, new Byn(0), 0);
    }

    public Purchase(Purchase purchase) {
        this.name = purchase.name;
        this.price = new Byn(purchase.price);
        this.numberOfUnits = purchase.numberOfUnits;
    }

    public Purchase(String name, Byn price, int numberOfUnits) {
        this.name = name;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
    }

    public Purchase(String[] elements) throws CsvLineException {
        String currentLine = String.join(DELIMITER, elements);
        if (elements[0].isEmpty()) {
            throw new CsvLineException(currentLine, EMPTY_ELEMENT, NAME);
        }
        if (Integer.parseInt(elements[1]) <= 0 || Integer.parseInt(elements[2]) <= 0) {
            throw new NonPositiveArgumentException(NONE_POSITIVE_ARGUMENT_PRICE_AND_UNITS);
        }
        if (elements.length > MAX_PURCHASE_LENGTH) {
            throw new IndexOutOfBoundsException();
        }
        this.name = elements[0];
        this.price = new Byn(elements[1]);
        this.numberOfUnits = Integer.parseInt(elements[2]);
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
    public Purchase getClone (){
        return new Purchase(name, new Byn(getPrice()), numberOfUnits);
    }

    protected String filedToString() {
        return name + DELIMITER + price + DELIMITER + numberOfUnits;
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
