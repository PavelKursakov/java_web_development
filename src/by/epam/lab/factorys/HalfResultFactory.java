package by.epam.lab.factorys;

import by.epam.lab.beans.HalfResult;
import by.epam.lab.beans.Result;

import java.sql.Date;

public class HalfResultFactory extends ResultFactory {
    @Override
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new HalfResult(login, test, date, mark);
    }

    public Result getResultFromFactory(String login, String test, String date, String mark) {
        return new HalfResult(login, test, date, mark);
    }

    public double getScaledMark(double mark) {
        return mark / HalfResult.FACTOR;
    }
}
