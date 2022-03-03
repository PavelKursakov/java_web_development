package by.epam.lab.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import static by.epam.lab.utils.Constants.*;

public class Result {
    private String login;
    private String test;
    private Date date;
    private int mark;

    public Result() {
    }

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
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

    @Override
    public String toString() {
        return login + DELIMITER + test + DELIMITER +
                new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(date) +
                DELIMITER + String.format(DOUBLE_MARK_FORMAT, mark / 10, mark % 10);
    }
}
