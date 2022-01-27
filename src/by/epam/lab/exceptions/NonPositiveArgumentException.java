package by.epam.lab.exceptions;

public class NonPositiveArgumentException extends IllegalArgumentException {
    private final String wrongField;


    public NonPositiveArgumentException() {
        this(null);
    }

    public NonPositiveArgumentException(String wrongField) {
        this.wrongField = wrongField;
    }

    public String getWrongField() {
        return wrongField;
    }

    @Override
    public String toString() {
        return wrongField;
    }
}
