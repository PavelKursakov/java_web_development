package by.epam.lab;

public class Byn implements Comparable<Byn> {
    private int value;

    public Byn() {
    }

    public Byn(int value) {
        this.value = value;
    }

    public Byn add(Byn byn) {
        value += byn.value;
        return this;
    }

    public Byn mul(int i) {
        value *= i;
        return this;
    }

    public Byn mul(double d) {
        value = (int) Math.round(value * d);
        return this;
    }

    public Byn diff(Byn byn) {
        value -= byn.value;
        return this;
    }

    @Override
    public String toString() {
        return Converter.convert(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return value == byn.value;
    }

    @Override
    public int compareTo(Byn o) {
        return value - o.value;
    }
}

