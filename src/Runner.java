import by.epam.lub.beans.LenNum;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static by.epam.lub.constants.Constants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {
            int num = 1;
            ArrayList<LenNum> arrayList = new ArrayList<>();
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] strings = s.split(REGEX_FOR_LINE);
                double x1 = Double.parseDouble(strings[INDEX_X1]);
                double y1 = Double.parseDouble(strings[INDEX_Y1]);
                double x2 = Double.parseDouble(strings[INDEX_X2]);
                double y2 = Double.parseDouble(strings[INDEX_Y2]);
                int len = (int) Math.round(Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
                LenNum lenNum1 = new LenNum(len, num);
                Collections.sort(arrayList);
                int index = Collections.binarySearch(arrayList, lenNum1);
                if (index >= 0) {
                    LenNum lenN = arrayList.get(index);
                    lenN.setNum(lenN.getNum() + 1);
                } else {
                    arrayList.add(lenNum1);
                }
            }
            Collections.sort(arrayList, new Comparator<LenNum>() {
                @Override
                public int compare(LenNum o1, LenNum o2) {
                    return o2.getNum() - o1.getNum();
                }
            });
            for (LenNum array : arrayList) {
                System.out.println(array);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
