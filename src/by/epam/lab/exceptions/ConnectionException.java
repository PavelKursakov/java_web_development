package by.epam.lab.exceptions;

import java.sql.SQLException;

public class ConnectionException extends SQLException {
    public ConnectionException(String reason) {
        super(reason);
    }
}
