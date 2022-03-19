import by.epam.lab.beans.Result;
import by.epam.lab.enums.MarkType;

import java.sql.Date;

public class ResultFactory {
    public Result getResultFromFactory(String login, String test, Date date, int mark){
        return new Result(login, test, date, mark);
    }
}
