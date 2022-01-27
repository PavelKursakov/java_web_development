package by.epam.lab.beans;

import by.epam.lab.exceptions.CsvLineException;
import by.epam.lab.exceptions.NonPositiveArgumentException;
import by.epam.lab.exceptions.WrongArgumentTypeException;

import static by.epam.lab.Constants.*;

public class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public PriceDiscountPurchase() {
        super();
        this.discount = new Byn(0);
    }

    public PriceDiscountPurchase(Purchase purchase, Byn discount) {
        super(purchase);
        this.discount = discount;
    }

    public PriceDiscountPurchase(String name, Byn price, Byn discount, int numberOfUnits) {
        super(name, price, numberOfUnits);
        this.discount = discount;
    }

    public PriceDiscountPurchase(String[] elements) throws CsvLineException {
        super(elements);
        try {
            if (Integer.parseInt(elements[3]) <= 0) {
                throw new NonPositiveArgumentException(NON_POSITIVE_DISCOUNT);
            }
            Byn discountCheck = new Byn(elements[3]);
            if (discountCheck.compareTo(getPrice()) >= 0) {
                throw new NonPositiveArgumentException(DISCOUNT_EQUAL);
            }
        } catch (NumberFormatException e) {
            throw new WrongArgumentTypeException(WRONG_ELEMENT_DISCOUNT);
        }
        this.discount = new Byn(elements[3]);
    }

    @Override
    public Purchase getClone() {
        return new PriceDiscountPurchase(super.getClone(), new Byn(discount));
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
