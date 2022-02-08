package by.epam.lab.beans;

import static by.epam.lab.Constants.*;

public class Purchase {
    private String name;
    private Byn price;
    private int numberOfUnits;

    public Purchase() {
    }

    public Purchase(String name, Byn price, int numberOfUnits) {
        this.name = name;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
    }

    public Purchase(String[] elements) {
        this(elements[0], new Byn(elements[1]), Integer.parseInt(elements[2]));
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
        return new Byn(price).mul(numberOfUnits);
    }

    protected String fieldsToString() {
        return getClass().getSimpleName() + DELIMITER + name + DELIMITER +
                price + DELIMITER + numberOfUnits;
    }

    @Override
    public String toString() {
        return fieldsToString() + DELIMITER + getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) o;
        return name.equals(purchase.name) && price.equals(purchase.price);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * NUMBER_TO_HASH_MUL_PURCHASES + price.hashCode();
    }
}
