package JDBC;

import java.sql.*;

public class JDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/attendance", "root", "");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/student?autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement ps = con.prepareStatement("insert into student_info values(?,?,?,?,?)");
        ps.setString(1, "177");
        ps.setString(2, "vishwali");
        ps.setString(3, "124");
        ps.setString(4, "CSE");
        ps.setString(5, "Vita");
        ps.executeUpdate();

        // Close resources
        ps.close();
        con.close();
    }
}