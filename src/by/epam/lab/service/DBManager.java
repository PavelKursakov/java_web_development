package by.epam.lab.service;

import by.epam.lab.exceptions.InitRuntimeException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.epam.lab.utils.Constants.*;

public class DBManager {
    private final static Connection CONNECTION = getInstance();

    private DBManager() {
    }

    private static Connection getInstance() {
        try {
            return DriverManager.getConnection(DB_URL,USER, PASSWORD);
        } catch (SQLException e) {
            throw new InitRuntimeException(ERROR_INIT_CONNECTION);
        }
    }

    public static Connection getConnection() {
        return CONNECTION;
    }

    public static void closeConnection() {
        if(CONNECTION != null) {
            try {
                CONNECTION.close();
            } catch (SQLException e) {
                System.err.println(ERROR_CLOSE_CONNECTION);
            }
        }
    }


}
