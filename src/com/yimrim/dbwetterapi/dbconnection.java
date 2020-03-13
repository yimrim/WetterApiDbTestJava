package com.yimrim.dbwetterapi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class dbconnection {

    public int insert(double Temperatur, double windms) throws SQLException {

        Connection con = null;
        try {
            String url1 = "jdbc:mysql://localhost:3306/apidata";
            String user = "yimrim";
            String pw = "duaredumm";
            con = getConnection(url1, user, pw);
            Statement stmt = con.createStatement();
            return stmt.executeUpdate("INSERT INTO wetter (Temperatur,Windgeschwindigkeit) VALUES (" + Temperatur + "," + windms + ")");
        } catch (SQLException e) {
            return 0;
        }

    }

}
