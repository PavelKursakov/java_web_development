package by.epam.lab;

import java.util.Objects;
import java.util.Scanner;

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

    public Purchase(Scanner sc) {
        this.name = sc.next();
        this.price = new Byn(sc);
        this.numberOfUnits = sc.nextInt();
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
        return new Byn().add(price).mul(numberOfUnits);
    }

    protected String fieldsToString () {
        return getClass().getSimpleName() + ";" + name + ";" + price + ";" + numberOfUnits;
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return name.equals(purchase.name) && price.equals(purchase.price);
    }

}
