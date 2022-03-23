import by.epam.lab.beans.Result;
import by.epam.lab.beans.ResultLoader;
import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.exceptions.LoadRuntimeException;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.factorys.ResultFactory;
import by.epam.lab.interfaces.ResultDao;
import by.epam.lab.service.DBManager;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static by.epam.lab.utils.Constants.*;

public class RunnerLogic {
    public static void logicMethod(String fileName, ResultFactory resultFactory) {
        try {
            loadResults(resultFactory, fileName);
            printAverageMarks(resultFactory);
            getCurrentAndPrintLastDay(resultFactory);
        } finally {
            DBManager.closeConnection();
        }
    }

    private static void loadResults(ResultFactory factory, String sourceName) {
        try (ResultDao reader = factory.getDaoFromFactory(sourceName)) {
            ResultLoader.loadResults(reader);
        } catch (SourceException e) {
            System.err.println(ERROR_OPEN_SOURCE);
        } catch (ConnectionException e) {
            throw new LoadRuntimeException(ERROR_DB_LOAD);
        } catch (IOException e) {
            System.err.println(ERROR_IO);
        }
    }

    private static void printAverageMarks(ResultFactory factory) {
        try (Statement statement = DBManager.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(SELECT_MEAN_VALUE_OF_MARKS)) {
            while (rs.next()) {
                String login = rs.getString(NAME_ID_FOR_SET_LOG_TEST);
                double mark = rs.getDouble(MEAN_MARK_ID);
                mark = factory.getScaledMark(mark);
                System.out.printf(FORMAT_FOR_AVG_MARK_TABLE, login, mark);
            }
        } catch (SQLException e) {
            System.err.println(ERROR_AVERAGE_MARKS + e.getMessage());
        }
    }

    private static void getCurrentAndPrintLastDay(ResultFactory factory) {
        System.out.println(RESULT_IN_THIS_MONTH);
        try {
            List<Result> currentMonthResults = getCurrentMonthResults(factory);
            printLastDayResults(currentMonthResults);
        } catch (SQLException e) {
            System.err.println(ERROR_AVERAGE_MARKS + e.getMessage());
        }
    }

    private static List<Result> getCurrentMonthResults(ResultFactory factory) throws SQLException {
        try (Statement statement = DBManager.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(SELECT_RESULTS_IN_THIS_MONTH)) {
            List<Result> currentMonthResults = new LinkedList<>();
            while (rs.next()) {
                Result result = factory.getResultFromFactory(
                        rs.getString(LOGIN_ID),
                        rs.getString(TEST_ID),
                        rs.getDate(DATE_ID),
                        rs.getInt(MARK_ID));
                System.out.println(result);
                currentMonthResults.add(result);
            }
            return currentMonthResults;
        }
    }

    private static void printLastDayResults(List<Result> results) {
        System.out.println(RESULTS_IN_THE_LATEST_DAY);
        if (!results.isEmpty()) {
            int dateMaxDay = results.get(results.size() - 1)
                    .getDate().toLocalDate().getDayOfMonth();
            ListIterator<Result> listIterator = results.listIterator(results.size());
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
}
