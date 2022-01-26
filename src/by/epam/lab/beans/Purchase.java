package by.epam.lab.beans;

import java.util.Objects;

import static by.epam.lab.Constants.*;

public class Purchase implements Comparable<Purchase> {
    private String name;
    private Byn price;
    private int numberOfUnits;

    public Purchase() {
        this(null, new Byn(0), 0);
    }

    public Purchase(String name, Byn price, int numberOfUnits) {
        this.name = name;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
    }

    public Purchase(String[] elements) {
        boolean argumentsIsCorrect = true;
        String currentLine = String.join(DELIMITER, elements);
        for (String element : elements) {
            if (element.isEmpty()) {
                System.err.println(currentLine + EMPTY_ELEMENT + element);
                argumentsIsCorrect = false;
            }
        }
        try {
            if (argumentsIsCorrect && Integer.parseInt(elements[1]) <= 0 || Integer.parseInt(elements[2]) <= 0) {
                System.err.println(currentLine + NONE_POSITIVE_ARGUMENT_PRICE_AND_UNITS);
                argumentsIsCorrect = false;
            }

        } catch (NumberFormatException e) {
            System.err.println(currentLine + WRONG_ARGUMENT_PRICE_AND_UNITS);
            argumentsIsCorrect = false;
        }
        if (argumentsIsCorrect) {
            this.name = elements[0];
            this.price = new Byn(elements[1]);
            this.numberOfUnits = Integer.parseInt(elements[2]);
        } else {
            throw new NumberFormatException();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Byn getCost() {
        return price.mull(numberOfUnits);
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
