import by.epam.lab.Result;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        try (Connection cn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = cn.createStatement();
             PreparedStatement ps = cn.prepareStatement(INSERT_INTO_RESULT_TABLE)) {
            try (Scanner sc = new Scanner(new FileReader(CSV_NAME))) {
                sc.useLocale(Locale.ENGLISH);
                while (sc.hasNextLine()) {
                    int loginId = 0;
                    int testId = 0;
                    int coolMark = 0;
                    int meanCoolMark = 0;
                    String[] elements = sc.next().split(";");
                    try (ResultSet rs = st.executeQuery(String.format(SELECT_ID_LOGIN, elements[0]))) {
                        while (rs.next()) {
                            loginId = rs.getInt(1);
                        }
                    }
                    try (ResultSet rs = st.executeQuery(String.format(SELECT_ID_TESTS, elements[1]))) {
                        while (rs.next()) {
                            testId = rs.getInt(1);
                        }
                    }
                    Result result = new Result(elements[0], elements[1], elements[2], elements[3]);
                    ps.setInt(1, loginId);
                    ps.setInt(2, testId);
                    ps.setDate(3, result.getDate());
                    ps.setInt(4, result.getMark());
                    try(ResultSet rs = st.executeQuery(SELECT_MEAN_VALUE_OF_MARKS_COOL)) {
                        ResultSetMetaData rsm = rs.getMetaData();
                        int count = rsm.getColumnCount();
                        while (rs.next()){
                            coolMark += rs.getInt(1);
                        }
                        meanCoolMark = coolMark / count;
                        System.out.println(meanCoolMark);
                    }
                }
            }
            System.out.println();
        } catch (SQLException | FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
