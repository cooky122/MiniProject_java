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

            System.out.println("DB접속 성공");

        }catch(ClassNotFoundException e) {
            System.out.println("찾는 파일 없음");
        }catch(SQLException e) {
            System.out.println("DB접속 실패");
        }

        return con;
    }

    public static void close(ResultSet rs, PreparedStatement pstmt, Connection con) {

        // ====== rs
        if(rs != null) {
            try {
                rs.close();
                System.out.println("rs 닫기 성공");
                //확인 후 주석 처리 또는 제거
            } catch (SQLException e) {
                System.out.println("rs 닫기 실패");
                //확인 후 주석 처리 또는 제거
            }
        }

        // ===== pstmt
        if(pstmt != null) {
            try {
                pstmt.close();
                System.out.println("pstmt 닫기 성공");
                //확인 후 주석 처리 또는 제거
            } catch (SQLException e) {
                System.out.println("pstmt 닫기 실패");
                //확인 후 주석 처리 또는 제거
            }
        }

        // ===== con
        if(con != null) {
            try {
                con.close();
                System.out.println("con 닫기 성공");
                //확인 후 주석 처리 또는 제거
            } catch (SQLException e) {
                System.out.println("con 닫기 실패");
                //확인 후 주석 처리 또는 제거
            }
        }
    }
}