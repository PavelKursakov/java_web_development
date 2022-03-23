package by.epam.lab.utils;

public class Constants {
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/results";
    public static final String USER = "jse";
    public static final String PASSWORD = "jse";
    public static final String CSV_NAME = "results";
    public static final String CSV_NAME2 = "results2";
    public static final String DELIMITER = ";";
    public static final String SIMPLE_DATE_FORMAT = "dd.MM.yyyy";
    public static final String DATE_FORMAT = "yy-MM-dd";
    public static final String FORMAT_MARK = "%d.%1d";
    public static final String FORMAT_FOR_AVG_MARK_TABLE = "%s:%.2f\n";
    public static final String FILE_EXT = ".csv";
    public static final String XML = ".xml";
    public static final String SOURCE_DIR = "src/";
    public static final String HALF_STRING_ELEMENT = ".5";
    public static final String RESULT_IN_THIS_MONTH = "Result in this month:";
    public static final String RESULTS_IN_THE_LATEST_DAY = "Results in the latest day:";
    public static final String INSERT_INTO_RESULT_TABLE =
            "INSERT INTO results(loginId,testId,dat,mark) values(?,?,?,?);";
    public static final String INSERT_INTO_LOGINS_TABLE = "INSERT INTO logins (name) values(?);";
    public static final String INSERT_INTO_TESTS_TABLE = "INSERT INTO tests (name) values(?);";
    public static final String SELECT_ID_LOGIN = "SELECT * FROM logins WHERE name = (?)";
    public static final String SELECT_ID_TESTS = "SELECT * FROM tests WHERE name = (?)";
    public static final String SELECT_MEAN_VALUE_OF_MARKS =
            "SELECT distinct logins.name as login," +
                    " round(AVG(mark),2) as meanMark FROM results INNER JOIN logins on " +
                    "results.loginId=logins.idLogin group by login order by meanMark desc";
    public static final String SELECT_RESULTS_IN_THIS_MONTH = "SELECT logins.name as login, " +
            "tests.name as test, results.dat as date, results.mark FROM results \n" +
            "JOIN tests on results.testId=tests.idTest\n" +
            "JOIN logins on results.loginId=logins.idLogin \n" +
            "WHERE MONTH (dat) = MONTH(current_date) and YEAR(dat) = YEAR (current_date) \n" +
            "order by date asc ";
    public static final String DELETE_ALL_FROM_RESULTS = "Delete from results";
    public static final String DELETE_ALL_FROM_TESTS = "Delete from tests";
    public static final String DELETE_ALL_FROM_LOGINS = "Delete from logins";
    public static final String RESULTS_NAME = "results";
    public static final String NO_SUCH_ELEMENT_LIST_IS_EMPTY = "No such element. List is empty!";
    public static final String ERROR_INIT_CONNECTION = "Error init connection!";
    public static final String ERROR_CLOSE_CONNECTION = "Error close connection";
    public static final String ERROR_OPEN_SOURCE = "Error open source!";
    public static final String ERROR_AVERAGE_MARKS = "Error average marks!";
    public static final String ERROR_DB_LOAD = "Error DB load!";
    public static final String ERROR_IO = "Error IO";
    public static final String LOAD_ERROR = "Load error";
    public static final String WRONG_DATE_FORMAT = "Wrong date format";
    public static final String WRONG_DATA_IN_XML_FILE = "Wrong data in the xml file";
    public static final String EMPTY_STRING = "";
    public static final int TEST_ID_FOR_RESULT_HANDLER = 0;
    public static final int DATE_ID_FOR_RESULT_HANDLER = 1;
    public static final int MARK_ID_FOR_RESULT_HANDLER = 2;
    public static final int LOGIN_ID = 1;
    public static final int TEST_ID = 2;
    public static final int DATE_ID = 3;
    public static final int MARK_ID = 4;
    public static final int LOGIN_ID_ELEMENT = 0;
    public static final int TEST_ID_ELEMENT = 1;
    public static final int DATE_ID_ELEMENT = 2;
    public static final int MARK_ID_ELEMENT = 3;
    public static final int NAME_ID_FOR_SET_LOG_TEST = 1;
    public static final int MEAN_MARK_ID = 2;
}
