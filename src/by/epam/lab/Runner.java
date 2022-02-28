package by.epam.lab;

import by.epam.lab.beans.LenNum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.constants.Constants.*;

public class Runner {
    public static void main(String[] args) {
        try {
            Class.forName(CLASS_NAME);
            Connection cn = null;
            Statement st = null;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                cn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                st = cn.createStatement();
                rs = st.executeQuery(SELECT_LEN_NUM);
                List<LenNum> lenNumList = new ArrayList<>();
                while (rs.next()) {
                    lenNumList.add(new LenNum(rs.getInt(LEN_ID), rs.getInt(NUM_ID)));
                }
                ps = cn.prepareStatement(INSERT_INTO_FR_TABLE);
                ps.executeUpdate(DELETE_ALL_FROM_FREQUENCIES);
                for (LenNum lenNum : lenNumList) {
                    ps.setInt(LEN_ID, lenNum.getLen());
                    ps.setInt(NUM_ID, lenNum.getNum());
                    ps.executeUpdate();
                }
                rs = ps.executeQuery(SELECT_LEN_NUM_FROM_FREQUENCIES);
                printLenNum(rs, LEN_ID, NUM_ID);
                rs = ps.executeQuery(SELECT_LEN_NUM_AFTER_SORTING);
                printLenNum(rs, LEN_ID, NUM_ID);
            } finally {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printLenNum(ResultSet resultSet, int lenId, int numId) {
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(lenId) + DELIMITER + resultSet.getInt(numId));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
