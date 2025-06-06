package dbconnection.Comment;

import dbconnection.board.BoardDAO;
import dbconnection.board.BoardDTO;
import dbconnection.board.BoardMain;
import dbconnection.post.PostDAO;
import dbconnection.post.PostMain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    private static PostDAO postDAO = new PostDAO();
    private static BoardDAO boardDAO = new BoardDAO();
    private static CommentDAO commentDAO = new CommentDAO();

    public static String ID;
    public static int POST_ID;
    //<editor-fold desc="시작 선택지">
    public static void Start(String id, int post_id){
        ID = id;
        POST_ID = post_id;

        while(true) {
            System.out.println("댓글 생성 메인 페이지");
            System.out.println("1.댓글 입력 / 2.댓글 수정/삭제 / 3.댓글 검색 / 4.게시글로 이동");
            String ans = sc.nextLine();
            switch (ans) {
                case "1":
                    InsertComment(ID, POST_ID);
                    break;
                case "2":
                    UpdateComment(ID);
                    break;
                case "3":
                    SelectComment();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("잘못된 값 입력");
            }
        }
    }
    //</editor-fold>

//<editor-fold desc="삭제/수정">
    private static void UpdateComment(String id) {
        System.out.println("댓글 수정 페이지");
//        System.out.println("본인의 ID 를 입력 해주세요");
//        System.out.print("ID 입력>>");
//        String mem_id = sc.nextLine();
        System.out.println("수정/삭제 하고자 하는 댓글의 번호를 입력해주세요");
        System.out.print("댓글 번호 입력>>");
        int comment_id = Integer.parseInt(sc.nextLine());
        if(commentDAO.isOwner(comment_id, id)) {
            System.out.println("게시글을 수정/삭제 하시겠습니까?");
            System.out.println("1수정 / 2.삭제 / 3.취소");
            System.out.print(">>");
            String result = sc.nextLine();
            if(result.equals("1")) {
                System.out.println("수정하실 내용을 입력해 주세요");
                System.out.println("기존 내용: " + printContent(commentDAO.findCommentByCommentId(comment_id)));
                System.out.print(">>");
                String content = sc.nextLine();
                commentDAO.updateComment(content,comment_id);
            }
            else if(result.equals("2")) {
                System.out.println("정말로 삭제하시겠습니까?");
                System.out.println("1.삭제 / 2.취소");
                System.out.print(">>");
                result = sc.nextLine();
                if(result.equals("1")) {
                    commentDAO.deleteComment(comment_id);
                }
            }
        }else {
            System.out.println("댓글은 본인만 수정/삭제할 수 있습니다.");
        }
    }//end of UpdateComment
//</editor-fold>

//<editor-fold desc="삽입">
    private static void InsertComment(String id, int post_id) {
        List<BoardDTO> boards = boardDAO.findAllBoards();

        System.out.println("댓글 입력 페이지");
//        System.out.println("원하는 게시글을 선택해 주세요");
//        PostMain.printPostList();
//        System.out.print("게시글 번호 입력 >>");
//        int post_id = Integer.parseInt(sc.nextLine());
////        추후 로그인 상태라면 String mem_id = 입력된 ID
//        System.out.println("\n당신의 ID를 입력해 주세요");
//        System.out.print("ID 입력 >>");
//        String mem_id = sc.nextLine();
        System.out.println("\n작성하고자 하는 내용을 입력해주세요");
        System.out.print(">>");
        String content = sc.nextLine();

        CommentDTO dto = new CommentDTO(post_id, id, content);

        commentDAO.insertComment(dto);
    }//end of InsertComment
//</editor-fold>

//<editor-fold desc="검색">
    private static void SelectComment() {
        System.out.println("댓글 찾기 페이지");
        System.out.println("원하는 검색방법을 선택해 주세요");
        System.out.println("1.전체 댓글 검색 / 2.게시글 기준 검색 / 3.게시자 기준 검색");
        int result = Integer.parseInt(sc.nextLine());
        switch (result) {
            case 1: printAll(commentDAO.findAllComment());
            break;
            case 2:
                System.out.println("찾기를 원하는 게시글 입력");
                System.out.print(">> ");
                int post_id = Integer.parseInt(sc.nextLine());
                System.out.println(postDAO.findPost(post_id));
                printAll(commentDAO.findAllCommentByPostId(post_id));
            break;
            case 3:
                System.out.println("검색을 원하는 멤버 ID 입력");
                System.out.print(">> ");
                String mem_id = sc.nextLine();
                printAll(commentDAO.findAllCommentByMemId(mem_id));
                break;
        }
    }//end of SelectComment
//</editor-fold>
    
//<editor-fold desc="내용 출력">
    //commentDAO 에서 출력하고자 하는 메서드 끌고오면 출력 가능합니다.
    public static void printAll(List<CommentDTO> list) {
        System.out.println("========== 댓글 목록 ==========");
        for (CommentDTO comment : list ) {
            System.out.println(comment); // toString 자동 호출
        }
        System.out.println("--------------------------------");
    }//end of printAll
    //댓글의 내용만 출력하고자 할때 CommentDAO에서 원하는 값 매개변수로 추가 후 호출하시면 됩니다.
    public static String printContent(List<CommentDTO> list) {
        CommentDTO commentDTO = list.getFirst();

        return  commentDTO.content;
    }//end of printContent

//</editor-fold>

}
