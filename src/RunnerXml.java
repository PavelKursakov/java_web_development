import by.epam.lab.beans.*;

import java.sql.*;

import static by.epam.lab.utils.Constants.*;

public class RunnerXml {
    public static void main(String[] args) throws SQLException {
        ResultDao reader = new ResultImplCsv(RESULTS_NAME);
        DecimalResultFactory decimalResultFactory = new DecimalResultFactory();
        ResultsLoader.loadResults(reader);
        RunnerLogic.logicMethod(RESULTS_NAME, decimalResultFactory);
    }
}
