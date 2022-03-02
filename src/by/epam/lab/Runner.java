package by.epam.lab;

import by.epam.lab.beans.LenNum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.constants.Constants.*;

public class Runner {
    public static void main(String[] args) {
        try (Connection cn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = cn.createStatement();
             PreparedStatement ps = cn.prepareStatement(INSERT_INTO_FR_TABLE)) {
            try (ResultSet rs = st.executeQuery(SELECT_LEN_NUM)) {
                List<LenNum> lenNumList = new ArrayList<>();
                while (rs.next()) {
                    LenNum lenNum = new LenNum(rs.getInt(LEN_ID), rs.getInt(NUM_ID));
                    lenNumList.add(lenNum);
                    System.out.println(lenNum);
                }
                st.executeUpdate(DELETE_ALL_FROM_FREQUENCIES);
                for (LenNum lenNum : lenNumList) {
                    ps.setInt(LEN_ID, lenNum.getLen());
                    ps.setInt(NUM_ID, lenNum.getNum());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            try (ResultSet rs = st.executeQuery(SELECT_LEN_NUM_AFTER_SORTING)) {
                while (rs.next()) {
                    System.out.println(rs.getInt(LEN_ID) + DELIMITER + rs.getInt(NUM_ID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
