package by.epam.lab.beans;

import by.epam.lab.beans.Byn;
import by.epam.lab.beans.Purchase;

import static by.epam.lab.Constants.*;

public class PriceDiscountPurchase extends Purchase {
    private Byn discount;

    public PriceDiscountPurchase() {
        super();
    }

    public PriceDiscountPurchase(String name, Byn price, Byn discount, int numberOfUnits) {
        super(name, price, numberOfUnits);
        this.discount = discount;
    }

    public PriceDiscountPurchase(String[] elements) {
        super(elements);
        boolean discountIsCorrect = true;
        String currentLine = String.join(DELIMITER, elements);
        try {
            if (Integer.parseInt(elements[3]) <= 0) {
                System.err.println(currentLine + NON_POSITIVE_DISCOUNT);
                discountIsCorrect = false;
            }
            Byn discountCheck = new Byn(elements[3]);
            if (discountIsCorrect && discountCheck.compareTo(getPrice()) >= 0) {
                System.err.println(currentLine + DISCOUNT_EQUAL);
                discountIsCorrect = false;
            }
        } catch (NumberFormatException e) {
            System.err.println(currentLine + WRONG_ELEMENT_DISCOUNT);
            discountIsCorrect = false;
        }
        if (discountIsCorrect) {
            this.discount = new Byn(elements[3]);
        } else {
            throw new NumberFormatException();
        }
    }

    @Override
    public Byn getCost() {
        return super.getCost().sub(discount.mull(getNumberOfUnits()));
    }

    @Override
    protected String filedToString() {
        return super.filedToString() + DELIMITER + discount;
    }

}
