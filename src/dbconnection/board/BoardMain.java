package dbconnection.board;

import dbconnection.board.BoardDAO;
import dbconnection.post.PostMain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BoardMain {
    public static Scanner scanner = new Scanner(System.in);
    public static BoardDAO boardDAO = new BoardDAO();
    public static LocalDateTime now = LocalDateTime.now();
    //SQL문 사용가능한 DATETIME 형태로 포맷
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String formattedTime = now.format(formatter);
    public static String ID;
    public static void BoardStart(String id) {

        ID = id;
        while (true) {

            System.out.println("\n📝 게시판 관리 메뉴");
            System.out.println("1.게시판 생성 / 2.게시판 전체 열람 / 3.게시판 번호로 열람 / 4.게시판 제목 수정 / 5.게시판 삭제 / 0.종료");
            System.out.print("메뉴 선택 : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    insertBoard();
                    break;
                case "2":
                    findAllBoards();
                    break;
                case "3":
                    findBoardById();
                    break;
                case "4":
                    updateBoard();
                    break;
                case "5":
                    deleteBoard();
                    break;
                case "0":
                    System.out.println("게시판 관리 종료");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요");
            }
        }
    }

    // 게시판 생성
    private static void insertBoard() {
        System.out.println("\n[게시판 생성]");
//        System.out.print("작성자 ID 입력 : ");
        String memId = ID;

        System.out.print("게시판 제목 입력 : ");
        String title = scanner.nextLine();

        String createDate = formattedTime;

        BoardDTO dto = new BoardDTO();
        dto.setMem_id(memId);
        dto.setBoard_title(title);
        dto.setBoard_createdate(createDate);

        boardDAO.insertBoard(dto);
    }

    // 전체 게시판 목록 조회
    private static void findAllBoards() {
        System.out.println("\n[ 전체 게시판 목록 ]");
        List<BoardDTO> boards = boardDAO.findAllBoards();
        if (boards.isEmpty()) {
            System.out.println("등록된 게시판이 없습니다");
        } else {
            for (BoardDTO board : boards)
                printBoard(board);
        }

        //<editor-fold desc="형관 수정">
        System.out.println("몇번 게시판을 이용하시겠습니까?");

        int id = Integer.parseInt(scanner.nextLine());

        PostMain.start(ID, id);
        //</editor-fold>
    }

    // ID로 게시판 조회
    private static void findBoardById() {
        System.out.print("\n[ 게시판 번호로 조회 ]\n게시판 번호 입력: ");
        int id = Integer.parseInt(scanner.nextLine());

        BoardDTO board = boardDAO.findBoardsById(id);
        if (board == null) {
            System.out.println("해당 번호의 게시판이 존재하지 않습니다.");
        } else {
            printBoard(board);
            PostMain.start(ID, id);
        }
    }

    // 게시판 제목 수정
    private static void updateBoard() {
        System.out.print("\n[ 게시판 제목 수정 ]\n수정할 게시판 번호 입력: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("새 제목 입력: ");
        String newTitle = scanner.nextLine();

        boardDAO.updateBoard(id, newTitle);
    }

    // 게시판 삭제
    private static void deleteBoard() {
        System.out.print("\n[ 게시판 삭제 ]\n삭제할 게시판 번호 입력: ");
        int id = Integer.parseInt(scanner.nextLine());

        boardDAO.deleteBoard(id);
    }

    // 게시판 정보 출력
    public static void printBoard(BoardDTO board) {
        System.out.println("--------------------------");
        System.out.println("게시판 번호   : " + board.getBoard_id());
        System.out.println("작성자 ID   : " + board.getMem_id());
        System.out.println("게시판 제목 : " + board.getBoard_title());
        System.out.println("생성 일자   : " + board.getBoard_createdate());
        System.out.println("--------------------------");
    }
}
