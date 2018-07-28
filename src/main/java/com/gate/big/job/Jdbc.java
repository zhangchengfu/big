package com.gate.big.job;

import java.sql.*;

// 手写jdbc
public class Jdbc {
    public static void main(String[] args) {
        String driver = "***";
        String url = "***";
        PreparedStatement stmt = null;
        ResultSet res = null;
        Connection conn = null;
        CallableStatement proc = null;
        String sql = "select * from t";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,"u", "p");
            stmt = conn.prepareCall(sql);
            res = stmt.executeQuery();
            while (res.next()) {

            }
        } catch (ClassNotFoundException e) {

        }
        catch (SQLException e) {

        } finally {

        }
    }
}
