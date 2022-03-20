import by.epam.lab.beans.Result;
import by.epam.lab.beans.ResultLoader;
import by.epam.lab.factorys.ResultFactory;
import by.epam.lab.utils.Util;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ListIterator;

import static by.epam.lab.utils.Constants.*;

public class RunnerLogic {
    public static void logicMethod(String fileName, ResultFactory resultFactory) {
        try (Connection cn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = cn.createStatement()) {
            st.executeUpdate(String.format(DELETE_ALL_FROM_LOGINS));
            st.executeUpdate(String.format(DELETE_ALL_FROM_TESTS));
            st.executeUpdate(String.format(DELETE_ALL_FROM_RESULTS));
            try (ResultSet rs = st.executeQuery(SELECT_MEAN_VALUE_OF_MARKS)) {
                while (rs.next()) {
                    System.out.println(rs.getString(COLUMN_INDEX_ONE) +
                            DELIMITER + rs.getString(COLUMN_INDEX_TWO));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            ResultLoader.loadResults(resultFactory.getDaoFromFactory(fileName));
            try (ResultSet rs = st.executeQuery(SELECT_RESULTS_IN_THIS_MONTH)) {
                LinkedList<Result> resultList = new LinkedList<>();
                LocalDate localDateNow = LocalDate.now();
                System.out.println(RESULT_IN_THIS_MONTH);
                while (rs.next()) {
                    Result result = resultFactory.getResultFromFactory(
                            rs.getString(LOGIN_ID),
                            rs.getString(TEST_ID),
                            rs.getDate(DATE_ID),
                            rs.getInt(MARK_ID));
                    LocalDate localDateResult = result.getDate().toLocalDate();
                    if (localDateResult.getMonth().equals(localDateNow.getMonth()) &&
                            localDateResult.getYear() == localDateNow.getYear()) {
                        resultList.add(result);
                    }
                }
                Util.printResultList(resultList);
                System.out.println(RESULTS_IN_THE_LATEST_DAY);
                if (!resultList.isEmpty()) {
                    int dateMaxDay = resultList.get(resultList.size() - 1)
                            .getDate().toLocalDate().getDayOfMonth();
                    ListIterator<Result> listIterator = resultList.listIterator(resultList.size());
                    while (listIterator.hasPrevious()) {
                        Result currentResult = listIterator.previous();
                        if (currentResult.getDate().toLocalDate().getDayOfMonth() == dateMaxDay) {
                            System.out.println(currentResult);
                        }
                    }
                } else {
                    System.out.println(NO_SUCH_ELEMENT_LIST_IS_EMPTY);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
