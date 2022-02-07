package by.epam.lab.beans;

import by.epam.lab.Constants;

public class PricePurchase extends Purchase {
    private Byn discount;

    public PricePurchase() {
        super();
    }

    public PricePurchase(String name, Byn price, int numberOfUnits, Byn discount) {
        super(name, price, numberOfUnits);
        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
    }

    public PricePurchase(String[] elements) {
        super(elements);
        this.discount = new Byn(elements[3]);
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    @Override
    public Byn getCost() {
        return new Byn(getPrice()).sub(discount).mul(getNumberOfUnits());
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + Constants.DELIMITER + discount;
    }
}
