package by.epam.lab.beans;

import java.sql.Date;
import static by.epam.lab.utils.Constants.*;

public class HalfResult extends Result {
    public static final int FACTOR = 2;

    public HalfResult(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    public HalfResult(String login, String test, String date, String mark) {
        this(login, test, toDate(date), (int) (Double.parseDouble(mark) * FACTOR));
    }

    public String getStringMark() {
        int mark = getMark();
        return (mark >> 1) + ((mark & 1) == 0 ? EMPTY_STRING : HALF_STRING_ELEMENT);
    }

}
