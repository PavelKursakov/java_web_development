package by.epam.lab.beans;

import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.interfaces.ResultDao;
import by.epam.lab.service.DBManager;

import java.sql.*;

import static by.epam.lab.utils.Constants.*;

public class ResultLoader {

    public static void loadResults(ResultDao reader) throws ConnectionException {
        Connection connection = DBManager.getConnection();
        try (
                PreparedStatement psSelectLogin =
                        connection.prepareStatement(SELECT_ID_LOGIN);
                PreparedStatement psInsertLogin =
                        connection.prepareStatement(INSERT_INTO_LOGINS_TABLE);
                PreparedStatement psSelectTest =
                        connection.prepareStatement(SELECT_ID_TESTS);
                PreparedStatement psInsertTest =
                        connection.prepareStatement(INSERT_INTO_TESTS_TABLE);
                PreparedStatement psInsertForResult =
                        connection.prepareStatement(INSERT_INTO_RESULT_TABLE);
                Statement st = connection.createStatement()) {
            st.executeUpdate(DELETE_ALL_FROM_LOGINS);
            st.executeUpdate(DELETE_ALL_FROM_TESTS);
            st.executeUpdate(DELETE_ALL_FROM_RESULTS);
            while (reader.hasResult()) {
                Result result = reader.nextResult();
                String login = result.getLogin();
                String test = result.getTest();
                int idLogin = getId(login, psSelectLogin, psInsertLogin);
                int idTest = getId(test, psSelectTest, psInsertTest);
                psInsertForResult.setInt(LOGIN_ID, idLogin);
                psInsertForResult.setInt(TEST_ID, idTest);
                psInsertForResult.setDate(DATE_ID, result.getDate());
                psInsertForResult.setInt(MARK_ID, result.getMark());
                psInsertForResult.addBatch();
            }
            psInsertForResult.executeBatch();
        } catch (SQLException e) {
            throw new ConnectionException(LOAD_ERROR);
        }
    }

    public static int getId(String name, PreparedStatement selectRequest,
                            PreparedStatement insertRequest) throws SQLException {
        selectRequest.setString(NAME_ID_FOR_SET_LOG_TEST, name);
        int id;
        try (ResultSet rs = selectRequest.executeQuery()) {
            if (rs.next()) {
                id = rs.getInt(NAME_ID_FOR_SET_LOG_TEST);
            } else {
                insertRequest.setString(NAME_ID_FOR_SET_LOG_TEST, name);
                insertRequest.executeUpdate();
                try (ResultSet rs2 = selectRequest.executeQuery()) {
                    rs2.next();
                    id = rs2.getInt(NAME_ID_FOR_SET_LOG_TEST);
                }
            }
        }
        return id;
    }
}
