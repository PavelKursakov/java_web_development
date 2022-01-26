package by.epam.lab;

public class DiscountPurchase extends AbstractPurchase {
    private double discountPercent;
    public final static int UNITS_NUMBER = 10;

    public DiscountPurchase() {
        super();
    }

    public DiscountPurchase(Product product, int numberOfUnits, double discountPercent) {
        super(product, numberOfUnits);
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discountPercent;
    }

    @Override
    protected Byn getCostCalculation() {
        Byn byn = new Byn(getProduct().getPrice()).mul(getNumberOfUnits());
        if (getNumberOfUnits() > UNITS_NUMBER) {
            byn.mul(discountPercent);
        }
        return byn;
    }
}
