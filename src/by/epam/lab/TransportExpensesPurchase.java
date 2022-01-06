package by.epam.lab;

public class TransportExpensesPurchase extends AbstractPurchase{
    private Byn expenses;

    public TransportExpensesPurchase() {
        super();
    }

    public TransportExpensesPurchase(Product product, int numberOfUnits, Byn expenses) {
        super(product, numberOfUnits);
        this.expenses = expenses;
    }

    public Byn getExpenses() {
        return expenses;
    }

    public void setExpenses(Byn expenses) {
        this.expenses = expenses;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + expenses;
    }

    @Override
    protected Byn getCostCalculation() {
        return new Byn(getProduct().getPrice()).mul(getNumberOfUnits()).sub(expenses);
    }
}
