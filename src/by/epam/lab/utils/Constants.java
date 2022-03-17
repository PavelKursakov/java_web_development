package by.epam.lab.utils;

public class Constants {
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/results";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final String CSV_NAME = "src/results.csv";
    public static final String CSV_NAME2 = "src/results2.csv";
    public static final String DELIMITER = ";";
    public static final String SIMPLE_DATE_FORMAT = "dd.MM.yyyy";
    public static final String DOUBLE_MARK_FORMAT = "%d.%d";
    public static final String INT_MARK_FORMAT = "%d";
    public static final String RESULT_IN_THIS_MONTH = "Result in this month:";
    public static final String RESULTS_IN_THE_LATEST_DAY = "Results in the latest day:";
    public static final String INSERT_INTO_RESULT_TABLE =
            "INSERT INTO results(loginId,testId,date,mark) values(?,?,?,?);";
    public static final String SELECT_ID_LOGIN = "SELECT idLogin FROM logins WHERE name = '%s'";
    public static final String SELECT_ID_TESTS = "SELECT idTest FROM tests WHERE name = '%s'";
    public static final String SELECT_MEAN_VALUE_OF_MARKS =
            "SELECT distinct logins.name as login," +
                    " round(AVG(mark/10),2) as meanMark FROM results INNER JOIN logins on " +
                    "results.loginId=logins.idLogin group by login order by meanMark desc";
    public static final String SELECT_RESULTS_IN_THIS_MONTH = "SELECT logins.name as login, " +
            "tests.name as test, results.date as date, results.mark FROM results \n" +
            "JOIN tests on results.testId=tests.idTest\n" +
            "JOIN logins on results.loginId=logins.idLogin \n" +
            "WHERE MONTH (date) = MONTH(current_date) and YEAR(date) = YEAR (current_date) \n" +
            "order by date asc ";
    public static final String DELETE_ALL_FROM_RESULTS = "Delete from results";
    public static final String RESULTS_NAME = "src/results.xml";
    public static final String NO_SUCH_ELEMENT_LIST_IS_EMPTY = "No such element. List is empty!";
    public static final int TEST_ID_FOR_RESULT_HANDLER = 0;
    public static final int DATE_ID_FOR_RESULT_HANDLER = 1;
    public static final int MARK_ID_FOR_RESULT_HANDLER = 2;
    public static final int TEN_FOR_INT_MAR = 10;
    public static final int LOGIN_ID = 1;
    public static final int TEST_ID = 2;
    public static final int DATE_ID = 3;
    public static final int MARK_ID = 4;
    public static final int FIVE_FOR_EQUALS = 5;
}
