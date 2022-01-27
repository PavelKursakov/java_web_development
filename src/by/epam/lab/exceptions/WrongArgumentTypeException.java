package by.epam.lab.exceptions;

public class WrongArgumentTypeException extends IllegalArgumentException{
    private final String wrongField;

    public WrongArgumentTypeException() {
        this(null);
    }

    public WrongArgumentTypeException(String wrongField) {
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
