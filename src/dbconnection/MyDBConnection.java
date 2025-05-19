package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDBConnection {
    private static final String URL="jdbc:mysql://localhost:3306/Cafe_db?serverTimezone=UTC" ;
    private static final String USER="Manager" ;
    private static final String PASSWORD="My123456789!!m" ;

    public static Connection getConnection() {
        Connection con = null;

        try {
            //1. 드라이버 파일 찾기
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);

        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void close(ResultSet rs, PreparedStatement pstmt, Connection con) {

        // ====== rs
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // ===== pstmt
        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // ===== con
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}