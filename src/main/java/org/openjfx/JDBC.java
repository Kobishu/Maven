package org.openjfx;

import java.sql.*;

public class JDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Test","root","");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Produkte");
        while(rs.next())
            System.out.println(rs.getInt(1)+" "+rs.getString(2));
        con.close();

    }
}
