package by.epam.lub.beans;

import by.epam.lub.Constants;

public class DiscountProduct extends Product {
    private Byn discount;

    public DiscountProduct() {
    }

    public DiscountProduct(String name, Byn price, Byn discount) {
        super(name, price);
        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    @Override
    public Byn getPrice() {
        return super.getPrice().sub(discount);
    }

    @Override
    public String fieldsToString() {
        return super.fieldsToString() + Constants.DELIMITER + discount;
    }
}
