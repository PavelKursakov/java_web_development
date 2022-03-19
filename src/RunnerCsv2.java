import by.epam.lab.beans.ResultDao;
import by.epam.lab.beans.ResultImplCsv;
import by.epam.lab.beans.ResultsLoader;

import java.sql.*;


import static by.epam.lab.utils.Constants.*;

public class RunnerCsv2 {
    public static void main(String[] args) throws SQLException {
        ResultDao reader = new ResultImplCsv(CSV_NAME2);
        HalfResultFactory halfResultFactory = new HalfResultFactory();
        ResultsLoader.loadResults(reader);
        RunnerLogic.logicMethod(CSV_NAME2, halfResultFactory);
    }
}
