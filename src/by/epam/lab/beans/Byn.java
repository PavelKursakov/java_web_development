package by.epam.lab.beans;

import java.util.Scanner;

import static by.epam.lab.Constants.*;

public class Byn implements Comparable<Byn> {
    private int value;

    public Byn() {
    }

    public Byn(int value) {
        this.value = value;
    }

    public Byn(String s) {
        this(Integer.parseInt(s));
    }

    public Byn(int rubs, int coins) {
        this(rubs * HUNDRED_FOR_BYN + coins);
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public Byn(Scanner sc) {
        this(sc.nextInt());
    }

    public int getRubs() {
        return value / HUNDRED_FOR_BYN;
    }

    public int getCoins() {
        return value % HUNDRED_FOR_BYN;
    }

    public Byn add(Byn byn) {
        value += byn.value;
        return this;
    }

    public Byn mul(int i) {
        value *= i;
        return this;
    }

    public Byn sub(Byn byn) {
        value -= byn.value;
        return this;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_FOR_BYN, value / HUNDRED_FOR_BYN, value % HUNDRED_FOR_BYN);
    }

    @Override
    public int hashCode() {
        return value + NUMBER_TO_HASH_BYN;
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