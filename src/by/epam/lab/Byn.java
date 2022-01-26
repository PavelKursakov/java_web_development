package by.epam.lab;

import java.util.Scanner;

public class Byn implements Comparable<Byn> {
    private int value;

    public Byn() {
    }

    public Byn(int value) {
        this.value = value;
    }

    public Byn(int rubs, int coins) {
        this(rubs * 100 + coins);
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public Byn(Scanner sc) {
        this(sc.nextInt());
    }

    public int getRubs() {
        return value / 100;
    }

    public int getCoins() {
        return value % 100;
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
        return mul(d, RoundMethod.ROUND, 0);
    }

    public Byn sub(Byn byn) {
        value -= byn.value;
        return this;
    }

    public Byn mul(double k, RoundMethod roundMethod, int d) {
        value = roundMethod.round(value * k, d);
        return this;
    }

    public Byn round(RoundMethod roundMethod, int d) {
        value = roundMethod.round(value, d);
        return this;
    }


    @Override
    public String toString() {
        return String.format("%d.%02d", value / 100, value % 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Byn byn = (Byn) o;
        return value == byn.value;
    }

    @Override
    public int compareTo(Byn byn) {
        return value - byn.value;
    }
}
