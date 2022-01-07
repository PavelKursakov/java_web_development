package by.epam.lab;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    private final Product product;
    private int numberOfUnits;

    public AbstractPurchase() {
        this(null, 0);
    }

    public AbstractPurchase(Product product, int numberOfUnits) {
        this.product = product;
        this.numberOfUnits = numberOfUnits;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Byn getCost() {
        Byn baseCost = product.getPrice().mul(numberOfUnits);
        Byn finalCost = getCostCalculation(baseCost);
        return finalCost.round(RoundMethod.FLOOR, 2);
    }

    protected String fieldsToString() {
        return getClass().getSimpleName() + ";" + product + ";" + numberOfUnits;
    }

    protected abstract Byn getCostCalculation(Byn baseCost);

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    @Override
    public int compareTo(AbstractPurchase purchase) {
        return purchase.getCost().compareTo(getCost());
    }
}
