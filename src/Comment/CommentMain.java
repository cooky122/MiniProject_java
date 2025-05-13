package Comment;

import DBConnection.MyDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class CommentMain {
//        Connection con = MyDBConnection.getConnection();
//        PreparedStatement pstmt = con.prepareStatement(sql);
//        ResultSet rs = pstmt.executeQuery();
    public static LocalDate now = LocalDate.now();
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while(true) {
            System.out.println("댓글 생성 메인 페이지");
            System.out.print("1.댓글 입력 / 2.댓글 수정 / 3.댓글 삭제 / 4.댓글 검색");
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
                default:
                    System.out.println("잘못된 값 입력");
            }
        }
    }

    public static void InsertComment() {
        Connection con = MyDBConnection.getConnection();
        PreparedStatement pstmt = null;

        String sql = "insert into comment values(null,?,?,?,?,?)";

        try {
            pstmt = con.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("pstmt 오류");
            throw new RuntimeException(e);
        }

        try {
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("rs 오류");
            throw new RuntimeException(e);
        }

        System.out.println("댓글 입력 페이지");
        System.out.print("아이디 입력 >");
        String comment = sc.nextLine();
        System.out.print("\n댓글 입력 >");
        String ID = sc.nextLine();

        try {
            //TODO:postID 끌고올것
            pstmt.setInt(1,1);
            pstmt.setString(2,ID);
            pstmt.setString(3,comment);
            pstmt.setString(4, now.toString());
            pstmt.setInt(5,0);
        } catch (SQLException e) {
            System.out.println("입력값 오류");
            throw new RuntimeException(e);
        }

        System.out.println("댓글 생성 완료!");

        MyDBConnection.close(null, pstmt, con);
    }


}
