package by.epam.lab.beans;

import java.text.SimpleDateFormat;
import java.sql.Date;

import static by.epam.lab.utils.Constants.*;

public class Result {
    private String login;
    private String test;
    private Date date;
    private int mark;
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat(SIMPLE_DATE_FORMAT);

    public Result() {
    }

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, String date, String mark) {
        this(login, test, Date.valueOf(date), (int) (Double.parseDouble(mark) * TEN_FOR_INT_MAR));
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getStringDate() {
        return OUTPUT_DATE_FORMAT.format(date);
    }

    public String getStringMark() {
        return String.format(DOUBLE_MARK_FORMAT, mark /
                TEN_FOR_INT_MAR, mark % TEN_FOR_INT_MAR);
    }

    @Override
    public String toString() {
        return login + DELIMITER + test + DELIMITER + getStringDate() + DELIMITER + getStringMark();
    }
}
