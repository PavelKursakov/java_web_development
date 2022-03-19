import by.epam.lab.beans.DecimalResult;
import by.epam.lab.beans.Result;

import java.sql.Date;

public class DecimalResultFactory extends ResultFactory{
    public Result getResultFromFactory(String login, String test, Date date, int mark){
        return new DecimalResult(login, test, date, mark);
    }

}
