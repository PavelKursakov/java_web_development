package by.epam.lub.comparators;

import java.util.Comparator;
import java.util.Map;

public class NumComparator implements Comparator<Map.Entry<Integer, Integer>> {

    @Override
    public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
        return entry2.getValue() - entry1.getValue();
    }
}
