package by.epam.lab;

import java.util.Scanner;

public class DiscountPurchase extends Purchase {
    private static final int UNIT_NUMBER = 10;
    private double discountPercent;

    public DiscountPurchase() {
        super();
    }

    public DiscountPurchase(String name, Byn price, int numberOfUnits, double discountPercent) {
        super(name, price, numberOfUnits);
        this.discountPercent = discountPercent;
    }

    public DiscountPurchase(Scanner sc) {
        super(sc);
        this.discountPercent = sc.nextDouble();
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public Byn getCost() {
        Byn byn = new Byn(super.getCost());
        if (getNumberOfUnits() > UNIT_NUMBER) {
            byn.mul(1 - discountPercent / 100,RoundMethod.ROUND,0);
        }
        return byn;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discountPercent;
    }
}
