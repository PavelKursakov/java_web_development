package by.epam.lab;

public class Product {
    private final String name;
    private final Byn price;

    public Product() {
        this(null, null);
    }

    public Product(String name, Byn price) {
        this.price = price;
        this.name = name;
    }

    public Product(Product product, Byn price, Byn price1) {
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