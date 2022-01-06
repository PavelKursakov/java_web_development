package by.epam.lab;

public class Product {
    private String name;
    private Byn price;

    public Product() {
    }

    public Product(String name, Byn price) {
        this.name = name;
        this.price = price;
    }

    public Product(Product product) {
        this(product.name, product.price);
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + ";" + price;
    }
}