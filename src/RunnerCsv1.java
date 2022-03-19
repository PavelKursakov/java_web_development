import by.epam.lab.beans.DecimalResult;
import by.epam.lab.beans.ResultDao;
import by.epam.lab.beans.ResultImplCsv;
import by.epam.lab.beans.ResultsLoader;

import java.io.IOException;
import java.sql.*;

import static by.epam.lab.utils.Constants.*;

public class RunnerCsv1 {
    public static void main(String[] args) throws SQLException {
        ResultDao reader = new ResultImplCsv(CSV_NAME);
        ResultFactory resultFactory = new ResultFactory();
        ResultsLoader.loadResults(reader);
        RunnerLogic.logicMethod(CSV_NAME, resultFactory);
    }
}
