import by.epam.lab.enums.MarkType;
import by.epam.lab.beans.Result;
import by.epam.lab.beans.ResultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;

import static by.epam.lab.utils.Constants.*;

public class Runner2 {
    public static void main(String[] args) {
        try (Connection cn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = cn.createStatement();
             PreparedStatement ps = cn.prepareStatement(INSERT_INTO_RESULT_TABLE)) {
            st.executeUpdate(DELETE_ALL_FROM_RESULTS);
            XMLReader reader = XMLReaderFactory.createXMLReader();
            ResultHandler handler = new ResultHandler();
            reader.setContentHandler(handler);
            reader.parse(RESULTS_NAME);
            MarkType markType = MarkType.DOUBLE_MARK;
            for (Result result : handler.getResults()) {
                int loginId = 0;
                int testId = 0;
                try (ResultSet rs = st.executeQuery(String.format(SELECT_ID_LOGIN, result.getLogin()))) {
                    while (rs.next()) {
                        loginId = rs.getInt(1);
                    }
                }
                try (ResultSet rs = st.executeQuery(String.format(SELECT_ID_TESTS, result.getTest()))) {
                    while (rs.next()) {
                        testId = rs.getInt(1);
                    }
                }
                ps.setInt(1, loginId);
                ps.setInt(2, testId);
                ps.setDate(3, result.getDate());
                ps.setInt(4, result.getMark());
                ps.addBatch();
            }
            ps.executeBatch();
            try (ResultSet rs = st.executeQuery(SELECT_MEAN_VALUE_OF_MARKS)) {
                while (rs.next()) {
                    System.out.println(rs.getString(1) + DELIMITER + rs.getString(2));
                }
            }
            LinkedList<Result> resultList = new LinkedList<>();
            try (ResultSet rs = st.executeQuery(SELECT_RESULTS_IN_THIS_MONTH)) {
                System.out.println(RESULT_IN_THIS_MONTH);
                while (rs.next()) {
                    Result result = new Result(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
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
        } catch (SAXException | IOException | SQLException e) {
            System.err.println(e);
        }
    }
}
