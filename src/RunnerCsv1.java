import by.epam.lab.enums.MarkType;
import by.epam.lab.beans.Result;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

import static by.epam.lab.utils.Constants.*;

public class RunnerCsv1 {
    public static void main(String[] args) {
        try (Connection cn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = cn.createStatement();
             PreparedStatement ps = cn.prepareStatement(INSERT_INTO_RESULT_TABLE)) {
            try (Scanner sc = new Scanner(new FileReader(CSV_NAME))) {
                sc.useLocale(Locale.ENGLISH);
                st.executeUpdate(DELETE_ALL_FROM_RESULTS);
                while (sc.hasNextLine()) {
                    int loginId = 0;
                    int testId = 0;
                    String[] elements = sc.next().split(DELIMITER);
                    try (ResultSet rs =
                                 st.executeQuery(String.format(SELECT_ID_LOGIN,
                                         elements[LOGIN_ID_ELEMENT]))) {
                        while (rs.next()) {
                            loginId = rs.getInt(COLUMN_INDEX_ONE);
                        }
                    }
                    try (ResultSet rs =
                                 st.executeQuery(String.format(SELECT_ID_TESTS,
                                         elements[TEST_ID_ELEMENT]))) {
                        while (rs.next()) {
                            testId = rs.getInt(COLUMN_INDEX_ONE);
                        }
                    }
                    Result result = new Result(
                            elements[LOGIN_ID_ELEMENT],
                            elements[TEST_ID_ELEMENT],
                            elements[DATE_ID_ELEMENT],
                            elements[MARK_ID_ELEMENT]);

                    ps.setInt(LOGIN_ID, loginId);
                    ps.setInt(TEST_ID, testId);
                    ps.setDate(DATE_ID, result.getDate());
                    ps.setInt(MARK_ID, result.getMark());
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (FileNotFoundException e) {
                System.err.println(e);
            }
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
                MarkType markType = MarkType.INT_MARK;
                System.out.println(RESULT_IN_THIS_MONTH);
                while (rs.next()) {
                    Result result = new Result(
                            rs.getString(LOGIN_ID),
                            rs.getString(TEST_ID),
                            rs.getString(DATE_ID),
                            rs.getString(MARK_ID),
                            markType);
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
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
