package by.epam.lab.beans;

import by.epam.lab.interfaces.ResultDao;

import java.sql.*;

import static by.epam.lab.utils.Constants.*;

public class ResultLoader {
    private static Connection cn;
    private static Statement st;

    public static void loadResults(ResultDao reader) throws SQLException {
        cn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        st = cn.createStatement();
        PreparedStatement psInsertForResult = cn.prepareStatement(INSERT_INTO_RESULT_TABLE);
        while (reader.hasResult()) {
            Result result = reader.nextResult();
            String login = result.getLogin();
            String test = result.getTest();
            int idLogin = getId(login, SELECT_ID_LOGIN, INSERT_INTO_LOGINS_TABLE);
            int idTest = getId(test, SELECT_ID_TESTS, INSERT_INTO_TESTS_TABLE);
            psInsertForResult.setInt(LOGIN_ID, idLogin);
            psInsertForResult.setInt(TEST_ID, idTest);
            psInsertForResult.setDate(DATE_ID, result.getDate());
            psInsertForResult.setInt(MARK_ID, result.getMark());
            psInsertForResult.addBatch();
            psInsertForResult.executeBatch();
        }
    }

    public static int getId(String name, String selectRequest, String insertRequest) {
        int id = 0;
        try (PreparedStatement psInsertLogin = cn.prepareStatement(insertRequest)) {
            boolean elementWasCreated = false;
            try (ResultSet rs = st.executeQuery(String.format(selectRequest, name))) {
                while (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        elementWasCreated = true;
                        id = rs.getInt(1);
                    }
                }
                if (!elementWasCreated) {
                    psInsertLogin.setString(NAME_ID_FOR_SET_LOG_TEST, name);
                    psInsertLogin.addBatch();
                    psInsertLogin.executeBatch();
                }
            }
            try (ResultSet rs = st.executeQuery(String.format(selectRequest, name))) {
                if (!elementWasCreated) {
                    while (rs.next()) {
                        id = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
            ;
        }
        return id;
    }
}
