package Post;

import DBConnection.MyDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class PostMain {public static LocalDate now = LocalDate.now();
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while(true) {
            System.out.println("게시글 생성 메인 페이지");
            System.out.print("1.게시글 입력 / 2.게시글 수정 / 3.게시글 삭제 / 4.게시글 검색");
            String ans = sc.nextLine();
            switch (ans) {
                case "1":
                    InsertPost();
                    break;
                case "2":
                    UpdatePost();
                    break;
                case "3":
                    DeletePost();
                    break;
                case "4":
                    SelectPost();
                    break;
                case "100":
                    return;
                default:
                    System.out.println("잘못된 값 입력");
            }
        }//end of while
    }//end of main

    private static void SelectPost() {

    }

    private static void DeletePost() {
        
    }

    private static void UpdatePost() {

    }

    public static void InsertPost() {
        Connection con = MyDBConnection.getConnection();
        PreparedStatement pstmt = null;

        String sql = "insert into post values(null,?,?,?,?,?,?,?,?,?)";

        try {
            pstmt = con.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("pstmt오류");
            throw new RuntimeException(e);
        }
        try {
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("rs오류");
            throw new RuntimeException(e);
        }

        System.out.println("게시글 작성 페이지");
        int boardID = 1;
        String memID = "", title = "", content = "";
        while (true) {
            System.out.println("게시판 선택");
            System.out.print("1.자유게시판 / 2.일반게시판 / 3.유머게시판");
            boardID = Integer.parseInt(sc.nextLine());
            if (boardID > 3 || boardID < 1) {
                System.out.println("1~3번중 선택해주세요");
            } else {
                break;
            }
        }
        while (true) {
            System.out.print("\n아이디 입력 >>");
            memID = sc.nextLine();
            if (memID.length() > 20) {
                System.out.println("20자 이내로 작성해주세요");
            } else if (memID.length() < 20) {
                break;
            }
        }
        while (true) {
            System.out.print("\n제목 입력 >>");
            title = sc.nextLine();
            if (title.length() > 50) {
                System.out.println("50자 이내로 작성해주세요");
            } else if (title.length() < 50) {
                break;
            }
        }
        while (true) {
            System.out.print("\n내용 입력 >>");
            content = sc.nextLine();
            if (content.length() > 3000) {
                System.out.println("3000자 이내로 작성해주세요");
            } else if (content.length() < 3000) {
                break;
            }
        }


        try {
            pstmt.setInt(1, boardID);
            pstmt.setString(2, memID);
            pstmt.setString(3,title);
            pstmt.setString(4,content);
            pstmt.setString(5,now.toString());
            pstmt.setString(6,now.toString());
            pstmt.setInt(7,0);
            pstmt.setInt(8,0);
        } catch (SQLException e) {
            System.out.println("입력값 오류");
            throw new RuntimeException(e);
        }

    }
}
