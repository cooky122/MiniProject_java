package dbconnection;

import dbconnection.board.BoardMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class ExamMain {
  //Scanner
  public static Scanner sc = new Scanner(System.in);
  //DB 접속
  static Connection con = null;
  static PreparedStatement pstmt = null;
  static ResultSet rs = null;

  //테스트용 간단 main
  public static void main(String[] args) {
    while (true) {
      System.out.println("로그인을 진행 해주세요");
      System.out.print("ID 입력 >>");
      String id = sc.nextLine();
      System.out.print("\nPW 입력 >>");
      String pw = sc.nextLine();
      if(ExLogin(id,pw)){
        System.out.println("카페에 오신걸 환영합니다!");
        BoardMain.BoardStart();
        break;
      }
      else {
        System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
      }
    }
  }


  public static boolean ExLogin(String id, String pw) {
    String sql = "select mem_pw from board where mem_id=?";
    boolean result = false;

    try {
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1,id);

      rs = pstmt.executeQuery();

      rs.next();

      if(pw.equals(rs.getString("mem_pw"))){
        result = true;
      } else {
        result = false;
      }


    } catch (SQLException e){
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      MyDBConnection.close(rs, pstmt, con);
    }
    return result;
  }
}//end of class
