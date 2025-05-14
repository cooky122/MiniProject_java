package dbconnection.Comment;

import dbconnection.MyDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CommentMain {
//        Connection con = MyDBConnection.getConnection();
//        PreparedStatement pstmt = con.prepareStatement(sql);
//        ResultSet rs = pstmt.executeQuery();
    //로컬 시간 불러오기
    public static LocalDateTime now = LocalDateTime.now();
    //SQL문 사용가능한 DATETIME 형태로 포맷
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String formattedTime = now.format(formatter);
    //Scanner 객체 선언
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            System.out.println("댓글 생성 메인 페이지");
            System.out.println("1.댓글 입력 / 2.댓글 수정 / 3.댓글 삭제 / 4.댓글 검색");
            String ans = sc.nextLine();
            switch (ans) {
                case "1":
                    InsertComment();
                    break;
                case "2":
                    UpdateComment();
                    break;
                case "3":
                    DeleteComment();
                    break;
                case "4":
                    SelectComment();
                    break;
                case "100":
                    return;
                default:
                    System.out.println("잘못된 값 입력");
            }
        }
    }

    private static void SelectComment() {


    }

    private static void DeleteComment() {

    }

    private static void UpdateComment() {
        
    }

    public static void InsertComment() {

        String sql = "insert into comment values(null,?,?,?,?,?)";

        Connection con = MyDBConnection.getConnection();
        PreparedStatement pstmt = null;

        try {
             pstmt = con.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("pstmt 오류");
            throw new RuntimeException(e);
        }

        System.out.println("댓글 입력 페이지");
        System.out.print("아이디 입력 >");
        String ID = sc.nextLine();
        System.out.print("\n댓글 입력 >");
        String comment = sc.nextLine();

        try {
            //TODO:postID 끌고올것 현재 2번 포스트 고정
            pstmt.setInt(1,2);
            pstmt.setString(2,ID);
            pstmt.setString(3,comment);
            pstmt.setString(4, formattedTime);
            pstmt.setInt(5,0);
        } catch (SQLException e) {
            System.out.println("입력값 오류");
            throw new RuntimeException(e);
        }

        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("올리기 실패");
            throw new RuntimeException(e);
        }

        System.out.println("댓글 생성 완료!");

        MyDBConnection.close(null, pstmt, con);
    }


}
