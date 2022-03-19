package by.epam.lab.beans;

import by.epam.lab.enums.MarkType;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

import static by.epam.lab.utils.Constants.*;

public class Result {
    private String login;
    private String test;
    private java.sql.Date date;
    private int mark;
    private MarkType markType;

    private final static SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat(SIMPLE_DATE_FORMAT);

    public Result() {
    }

    public Result(String login, String test, java.sql.Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, String date, String mark, MarkType markType) {
        this(login, test, java.sql.Date.valueOf(date), (int) (Double.parseDouble(mark)));
        this.markType = markType;
    }

    public Result(String login, String test, String date, String mark) {
        this(login, test, java.sql.Date.valueOf(date),
                (int) (Double.parseDouble(mark) * TEN_FOR_INT_MAR));
    }
    public Result(String[] elements) {
        this(elements[LOGIN_ID_ELEMENT], elements[TEST_ID_ELEMENT], Date.valueOf(elements[DATE_ID_ELEMENT]),
                (int) (Double.parseDouble(elements[MARK_ID_ELEMENT]) * TEN_FOR_INT_MAR));
    }

    public MarkType getMarkType() {
        return markType;
    }

    public void setMarkType(MarkType markType) {
        this.markType = markType;
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
        return markType.getStringMark(mark);
    }

    @Override
    public String toString() {
        return login + DELIMITER + test + DELIMITER + getStringDate() + DELIMITER + getStringMark();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(date, result.date);
    }
}
