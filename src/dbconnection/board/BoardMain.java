package dbconnection.board;

import java.util.Scanner;

public class BoardMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BoardDAO boardDAO = new BoardDAO();

        while(true){
            System.out.println("\n.📝게시판 관리 메뉴 ");
            System.out.println("1. 게시판 생성");
            System.out.println("2. 게시판 전체 열람");
            System.out.println("3. 게시판 ID로 열람");
            System.out.println("4. 게시판 제목 수정");
            System.out.println("5. 게시판 삭제");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택 : ");
            String input = scanner.nextLine();

        }
    }

    public static void printBoard(BoardDTO board) {
        System.out.println("--------------------------");
        System.out.println("게시판 ID   : " + board.getBoard_id());
        System.out.println("작성자 ID   : " + board.getMem_id());
        System.out.println("게시판 제목 : " + board.getBoard_title());
        System.out.println("생성 일자   : " + board.getBoard_createdate());
        System.out.println("--------------------------");
    }
}
