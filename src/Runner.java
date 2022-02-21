import by.epam.lub.comparators.NumComparator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lub.constants.Constants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {
            Map<Integer, Integer> lenNumMap = new HashMap<>();
            while (sc.hasNextLine()) {
                String currentString = sc.nextLine();
                String[] coordinates = currentString.split(REGEX_FOR_LINE);
                double x1 = Double.parseDouble(coordinates[INDEX_X1]);
                double y1 = Double.parseDouble(coordinates[INDEX_Y1]);
                double x2 = Double.parseDouble(coordinates[INDEX_X2]);
                double y2 = Double.parseDouble(coordinates[INDEX_Y2]);
                int len = (int) Math.round(Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
                int num = lenNumMap.get(len) != null ? lenNumMap.get(len) + 1 : 1;
                lenNumMap.put(len, num);
            }
            List<Map.Entry<Integer, Integer>> lenNumList = new ArrayList<>(lenNumMap.entrySet());
            Collections.sort(lenNumList, new NumComparator());

            for (Map.Entry<Integer, Integer> entry : lenNumList) {
                System.out.println(entry.getKey() + DELIMITER + entry.getValue());
            }

        } catch (FileNotFoundException e) {
            System.out.println(FILE_IS_NOT_FOUND);
        }
    }
}
