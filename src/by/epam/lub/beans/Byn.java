package by.epam.lub.beans;

import by.epam.lub.Constants;
import by.epam.lub.enums.RoundMethod;

public class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        this(0);
    }

    public Byn(int value) {
        this.value = value;
    }

    public Byn sub(Byn byn) {
        return new Byn(value - byn.value);
    }

    public Byn mul(double k, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value * k, d));

    }

    public Byn div(double k, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value / k, d));
    }

    @Override
    public String toString() {
        return String.format(Constants.FORMAT_FOR_BYN, value /
                Constants.HUNDRED, value % Constants.HUNDRED);
    }

    @Override
    public int compareTo(Byn byn) {
        return value - byn.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return value == byn.value;
    }
}
