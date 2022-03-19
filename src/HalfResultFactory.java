import by.epam.lab.beans.Result;

import java.sql.Date;

public class HalfResultFactory extends ResultFactory{
    @Override
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return super.getResultFromFactory(login, test, date, mark);
    }
}
