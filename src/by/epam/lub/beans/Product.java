package by.epam.lub.beans;

import by.epam.lub.Constants;

public class Product extends AbstractItem {
    private String name;
    private Byn price;

    public Product() {
    }

    public Product(String name, Byn price) {
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return fieldsToString();
    }

    public String fieldsToString(){
        return name + Constants.DELIMITER + price;
    }
}
