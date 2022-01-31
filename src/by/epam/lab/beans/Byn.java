package by.epam.lab.beans;

import static by.epam.lab.Constants.*;

import by.epam.lab.exceptions.NonPositiveArgumentException;

import java.util.Scanner;

public final class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        this(0);
    }

    public Byn(int value) {
        if (value < 0) {
            throw new NonPositiveArgumentException(NONE_POSITIVE_ARGUMENT + value);
        }
        this.value = value;
    }


    public Byn(Byn byn) {
        this(byn.value);
    }

    public Byn(Scanner sc) {
        this(sc.nextInt());
    }

    /**
     * @throws NumberFormatException if wrong format of s.
     */

    public Byn(String s) {
        this(Integer.parseInt(s));
    }

    public Byn(int rubs, int coins) {
        this(new Byn(getValidValue(rubs, coins)));
    }

    private static int getValidValue(int rubs, int coins) {
        if (rubs < 0) {
            throw new NonPositiveArgumentException();
        }
        if (coins <= 0) {
            throw new NonPositiveArgumentException();
        }
        return rubs * HUNDRED_FOR_BYN + coins;
    }

    public int getRubs() {
        return value / HUNDRED_FOR_BYN;
    }

    public int getCoins() {
        return value % HUNDRED_FOR_BYN;
    }

    public Byn add(Byn byn) {
        return new Byn(value + byn.value);
    }

    public Byn sub(Byn byn) {
        return new Byn(value - byn.value);
    }

    public Byn mull(int i) {
        return new Byn(value * i);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return value == byn.value;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_FOR_BYN, value / HUNDRED_FOR_BYN, value % HUNDRED_FOR_BYN);
    }


    @Override
    public int compareTo(Byn byn) {
        return value - byn.value;
    }
}
