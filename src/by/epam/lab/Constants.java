package by.epam.lab;

public class Constants {
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/results";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final String CSV_NAME = "src/results.csv";
    public static final String DELIMITER = ";";
    public static final String SIMPLE_DATE_FORMAT = "dd.MM.yyyy";
    public static final String DOUBLE_MARK_FORMAT = "%d.%d";
    public static final int TEN_FOR_INT_MAR = 10;
    public static final String INSERT_INTO_RESULT_TABLE = "INSERT INTO results(loginId,testId,date,mark) values(?,?,?,?);";
    public static final String SELECT_ID_LOGIN = "SELECT idLogin FROM logins WHERE name = '%s'";
    public static final String SELECT_ID_TESTS = "SELECT idTest FROM tests WHERE name = '%s'";
    public static final String SELECT_MEAN_VALUE_OF_MARKS_COOL = "SELECT mark FROM results where loginId=1";
    public static final String SELECT_MEAN_VALUE_OF_MARKS_CLEVER = "SELECT mark FROM results where loginId=2";
}
