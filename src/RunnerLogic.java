import by.epam.lab.beans.Result;
import by.epam.lab.enums.MarkType;

import java.sql.*;
import java.util.LinkedList;

import static by.epam.lab.utils.Constants.*;

public class RunnerLogic {
    public static void logicMethod(String csvName, ResultFactory resultFactory) {
        try(Connection cn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement st = cn.createStatement()) {
            try (ResultSet rs = st.executeQuery(SELECT_MEAN_VALUE_OF_MARKS)) {
                while (rs.next()) {
                    System.out.println(rs.getString(COLUMN_INDEX_ONE) +
                            DELIMITER + rs.getString(COLUMN_INDEX_TWO));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            try (ResultSet rs = st.executeQuery(SELECT_RESULTS_IN_THIS_MONTH)) {
                LinkedList<Result> resultList = new LinkedList<>();

                System.out.println(RESULT_IN_THIS_MONTH);
                while (rs.next()) {
                    Result result = resultFactory.getResultFromFactory (
                            rs.getString(LOGIN_ID),
                            rs.getString(TEST_ID),
                            rs.getDate(DATE_ID),
                            rs.getInt(MARK_ID));
                    resultList.add(result);
                    System.out.println(result);
                }
                if (!resultList.isEmpty()) {
                    System.out.println(RESULTS_IN_THE_LATEST_DAY);
                    for (Result result : resultList) {
                        if (result.getDate().equals(resultList.getLast().getDate())) {
                            System.out.println(result);
                        }
                    }
                } else {
                    System.out.println(NO_SUCH_ELEMENT_LIST_IS_EMPTY);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
