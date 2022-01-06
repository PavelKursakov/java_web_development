package by.epam.lab;

public class PriceDiscountPurchase  extends AbstractPurchase{
    private Byn discount;

    public PriceDiscountPurchase() {
        super();
    }

    public PriceDiscountPurchase(Product product, int numberOfUnits, Byn discount) {
        super(product, numberOfUnits);
        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }

    @Override
    protected Byn getCostCalculation() {
        return new Byn(getProduct().getPrice()).sub(discount).mul(getNumberOfUnits());
    }
}
