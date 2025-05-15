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
    private static CommentDTO commentDTO;
    private static CommentDAO commentDAO = new CommentDAO();

    public static void main(String[] args) throws SQLException {
        while(true) {
            System.out.println("댓글 생성 메인 페이지");
            System.out.println("1.댓글 입력 / 2.댓글 수정 / 3.댓글 삭제 / 4.댓글 검색");
            String ans = sc.nextLine();
            switch (ans) {
                case "1":
                    InsetComment();
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

    private static void DeleteComment() {

    }

    private static void UpdateComment() {
        
    }

    private static void InsetComment() {

        System.out.println("댓글 입력 페이지");
        System.out.println("원하는 게시글을 선택해 주세요");
//        보드 목록을 불러오는 메서드()
        System.out.print("게시글 번호 입력 >>");
        int post_id = Integer.parseInt(sc.nextLine());
//        추후 로그인 상태라면 String mem_id = 입력된 ID
        System.out.println("\n당신의 ID를 입력해 주세요");
        System.out.print("ID 입력 >>");
        String mem_id = sc.nextLine();
        System.out.println("\n작성하고자 하는 내용을 입력해주세요");
        System.out.print(">>");
        String content = sc.nextLine();
        String createTime = now.format(formatter);

        CommentDTO dto = new CommentDTO(post_id,mem_id,content,createTime);

        commentDAO.insertComment(dto);
    }

    private static void SelectComment() {
        System.out.println("댓글 찾기 페이지");
        System.out.println("원하는 검색방법을 선택해 주세요");
    }


}
