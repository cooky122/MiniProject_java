package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDBConnection {  //db 접속과 접속 끊기

    private static final  String URL="jdbc:mysql://localhost:3306/cafe_db?serverTimezone=UTC";
    //오라클 :   "jdbc:oracle:thin:@localhost:1521/데이터베이스명"
    private static final  String USER="cafeadmin" ;
    private static final  String PASSWORD="CafeAdmin123!!" ;

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("DB접속 성공");

        }catch(ClassNotFoundException e) {
            System.out.println("찾는 파일 없음");
        }catch(SQLException e) {
            System.out.println("접속 실패");
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
                System.out.println("rs 닫기 실패");
            }
        }

        // ===== pstmt
        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                System.out.println("pstmt 닫기 실패");
            }
        }

// ===== con
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("con 닫기 실패");
            }
        }
    }
}