import by.epam.lub.comparators.NumComparator;
import by.epam.lub.beans.LenNum;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lub.constants.Constants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {
            Set<LenNum> lenNumSet = new HashSet<>();
            while (sc.hasNextLine()) {
                String currentString = sc.nextLine();
                String[] coordinates = currentString.split(REGEX_FOR_LINE);
                double x1 = Double.parseDouble(coordinates[INDEX_X1]);
                double y1 = Double.parseDouble(coordinates[INDEX_Y1]);
                double x2 = Double.parseDouble(coordinates[INDEX_X2]);
                double y2 = Double.parseDouble(coordinates[INDEX_Y2]);
                int len = (int) Math.round(Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
                lenNumSet.add(new LenNum(len));
            }
            List<LenNum> lenNumList = new ArrayList<>(lenNumSet);
            Collections.sort(lenNumList, new NumComparator());

            for (LenNum lenNum : lenNumList) {
                System.out.println(lenNum);
            }

        } catch (FileNotFoundException e) {
            System.out.println(FILE_IS_NOT_FOUND);
        }
    }
}
