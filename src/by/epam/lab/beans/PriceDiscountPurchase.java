package by.epam.lab.beans;

import by.epam.lab.exceptions.NonPositiveArgumentException;
import by.epam.lab.exceptions.WrongArgumentTypeException;

import static by.epam.lab.Constants.*;

public final class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public PriceDiscountPurchase() {
        throw new IllegalStateException(EMPTY_PURCHASE);
    }

    public PriceDiscountPurchase(Purchase purchase, Byn discount) {
        this(purchase.getName(), purchase.getPrice(), purchase.getNumberOfUnits(), discount);
    }

    public PriceDiscountPurchase(String name, Byn price, int numberOfUnits, Byn discount) {
        super(name, price, numberOfUnits);
        if (discount.compareTo(getPrice()) >= 0) {
            throw new WrongArgumentTypeException(DISCOUNT_EQUAL);
        }
        if (discount.compareTo(new Byn(0)) == 0) {
            throw new NonPositiveArgumentException(NON_POSITIVE_DISCOUNT);
        }
        this.discount = discount;
    }

    public PriceDiscountPurchase(String[] elements) {
        this(new Purchase(getArrayForPurchase(elements)), new Byn(elements[3]));
    }

    private static String[] getArrayForPurchase(String[] strings) {
        if (strings.length != MAX_PURCHASE_LENGTH) {
            throw new IndexOutOfBoundsException(WRONG_ARGUMENT_MESSAGE);
        }
        return new String[]{strings[0], strings[1], strings[2]};
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
