package by.epam.lab.constants;

public class Constants {
    public static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/segments";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final String SELECT_LEN_NUM = "SELECT(round(sqrt((x1-x2)*(x1-x2)))) as len," +
            " count(*) as num FROM coordinates group by len \n" + "order by len asc";
    public static final String DELIMITER = ";";
    public static final String INSERT_INTO_FR_TABLE = "INSERT INTO frequencies(len,num) values(?,?);";
    public static final String DELETE_ALL_FROM_FREQUENCIES = "Delete from frequencies";
    public static final String SELECT_LEN_NUM_AFTER_SORTING = "Select len,num from frequencies where len>num";
    public static final String SELECT_LEN_NUM_FROM_FREQUENCIES = "Select len,num from frequencies";
    public static final int LEN_ID = 1;
    public static final int NUM_ID = 2;
}
