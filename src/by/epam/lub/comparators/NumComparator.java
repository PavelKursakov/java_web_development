package by.epam.lub.comparators;

import by.epam.lub.beans.LenNum;

import java.util.Comparator;

public class NumComparator implements Comparator<LenNum> {

    @Override
    public int compare(LenNum o1, LenNum o2) {
        return o2.getNum() - o1.getNum();
    }
}
