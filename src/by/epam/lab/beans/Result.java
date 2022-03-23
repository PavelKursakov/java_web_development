package by.epam.lab.beans;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static by.epam.lab.utils.Constants.*;

public class Result {
    private String login;
    private String test;
    private java.sql.Date date;
    private int mark;
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat(SIMPLE_DATE_FORMAT);
    private final static SimpleDateFormat SET_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);

    public Result() {
    }

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, String date, String mark) {
        this(login, test, toDate(date), Integer.parseInt(mark));
    }

    protected static Date toDate(String dateStr) {
        try {
            return new Date(SET_DATE_FORMAT.parse(dateStr).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(WRONG_DATE_FORMAT);
        }
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

    public java.sql.Date getDate() {
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
        return EMPTY_STRING + mark;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + DELIMITER + login + DELIMITER +
                test + DELIMITER + getStringDate() + DELIMITER + getStringMark();
    }
}
