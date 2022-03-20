package by.epam.lab.factorys;

import by.epam.lab.beans.Result;
import by.epam.lab.interfaces.ResultDao;
import by.epam.lab.resultsImpl.ResultImplXml;
import by.epam.lab.enums.MarkType;

import java.sql.Date;

public class DecimalResultFactory extends ResultFactory {
    @Override
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new Result(login, test, date, mark, MarkType.DOUBLE_MARK);
    }

    @Override
    public ResultDao getDaoFromFactory(String fileName) {
        return new ResultImplXml(fileName);
    }
}
