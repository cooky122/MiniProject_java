package dbconnection.post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostMain {
    public static Scanner scanner = new Scanner(System.in);
    public static PostDAO postdao = new PostDAO();

    public static void start() {
        System.out.println("<게시글 작성 페이지>");

        //while 문으로 번호 입력받아서 crud 실행 코드 넣기
        while(true){
            //테이블 전체를 보여주는 메소드
            //소속 게시판/제목/작성자/작성일/조회수 순서로 출력
            printPostList();

            System.out.println("1.게시글 조회 / 2.게시글 작성 / 3.게시글 삭제 / 4.게시글 검색 / 5. 종료");

            int select = scanner.nextInt();
            scanner.nextLine();
            if(select == 5) //입력 종료
                break;

            switch(select) {
                case 1:
                    readProcess();    //게시글 조회 메소드
                    break;
                case 2:
                    insertProcess();    //게시글 작성 메소드
                    break;
                case 3:
                    deleteProcess();    //delete 값을 입력받는 메소드
                    break;
                case 4:
                    searchProcess();    //검색어를 입력받는 메소드
                    break;
               default:
                    break;
            }
        }
    }

    private static void readProcess() {
        System.out.print("조회할 게시글 번호 > ");
        int selectPost = scanner.nextInt();
        scanner.nextLine();

        Post post = postdao.findPost(selectPost);
        System.out.println(post);
        System.out.println();
        postdao.updateView(selectPost); //view_count 업데이트

        while(true) {
            //좋아요 누를지 말지 선택
            System.out.println(" 1.좋아요 / 2.게시글 수정 / 3.다음");
            int selectACT = scanner.nextInt();
            scanner.nextLine();

            if(selectACT == 3){
                break;
            }else if(selectACT == 1){
                postdao.updateLike(selectPost); //좋아요 선택시 좋아요 업데이트
                break;
            }else if(selectACT == 2){
                updateProcess(post); //게시글 수정 선택시 update 메소드 호출
                continue;
            }else{
                System.out.println("잘못된 입력입니다.");
                continue;
            }
        }

        //여기에 댓글 CRUD 기능 삽입

    }

    //전체 게시물 리스트로 출력
    public static void printPostList() {
        List<Post> postList = new ArrayList<Post>();

        postList = postdao.findPostAll();
        Post row = new Post();
        String board="";

        System.out.println("게시글 아이디 | 게시판 | 제목 | 작성자 | 작성일 | 조회수");
        System.out.println("-------------------------------------");
        for (Post post : postList) {
            row = post;

            if (row.getBoard_id() == 1) {
                board = "공지";
            } else {
                board = "일반";
            }
            System.out.println(row.getPost_id() + "\t" + board + "\t" + row.getPost_title() + "\t" + row.getMem_id()
                    + "\t" + row.getCreate_Time() + "\t\t" + row.getView_count());
        }
        System.out.println();
    }

    //insert 값을 입력받는 메소드
    private static void insertProcess() {
        Post post = new Post();

        //게시판 선택
        System.out.println("게시판 선택");
        System.out.println("1. 공지게시판 / 2. 자유게시판");
        int select = scanner.nextInt();
        scanner.nextLine();

        int board_id;
        if(select == 1){
            board_id = 1;
        }else {
            board_id = 2;
        }
        //작성자 설정
        String mem_id = "user01";
        //제목 입력
        System.out.print("제목: ");
        String post_title = scanner.nextLine();
        //글 내용 입력
        System.out.println("내용: ");
        String content = scanner.nextLine();
        //공지글 설정
        System.out.println("공지글로 등록(y/n)");
        String a = scanner.nextLine();

        boolean post_type;
        if(a.equals("y")){
            post_type = true;
        }else{
            post_type = false;
        }

        //post dto 객체에 값 넣기
        post.setBoard_id(board_id);
        post.setMem_id(mem_id);
        post.setPost_title(post_title);
        post.setContent(content);
        post.setPost_type(post_type);

        //PostDAO 의 insertPost 호출, db에 insert 수행
        postdao.insertPost(post);
    }

    private static void updateProcess(Post post) {
        System.out.println("<게시글 수정 페이지>");
        System.out.println(post);
        System.out.println();

        System.out.print("제목 수정: ");
        String edit_title = scanner.nextLine();
        post.setPost_title(edit_title);

        System.out.println("내용 수정: ");
        String edit_content = scanner.nextLine();
        post.setContent(edit_content);

        postdao.updatePost(post);
    }

    private static void deleteProcess() {
        System.out.print("삭제할 게시글 번호 > ");
        int selectPost = scanner.nextInt();
        scanner.nextLine();

        postdao.deletePost(selectPost);
        System.out.println();

        System.out.println("게시글이 삭제되었습니다.\n");
    }

    private static void searchProcess() {
        List<Post> searchPost = new ArrayList<>();

        LocalDate now = LocalDate.now();
        System.out.println(now);
        String dateQuery = "create_Time > date_add(now(), interval -1";
        String keyword = "";

        System.out.println("기간 선택");
        System.out.println("1.전체 | 2.지난 1년 | 3.지난 1달");
        int selectNum = scanner.nextInt();
        scanner.nextLine();

        switch (selectNum){
            case 1:
                dateQuery = ""; //기간을 전체로 하면 dateQuery가 필요하지 않기 때문에 초기화
                break;
            case 2:
                dateQuery += " year";   //기간 1년으로 설정
                break;
            case 3:
                dateQuery += " month";  //기간 1달로 설정
                break;
            default:
                dateQuery = ""; //선택 없으면 기본으로 전체 기간 선택
                break;
        }

        System.out.println("검색 범위를 선택하세요");
        System.out.println("1.제목 | 2.내용 | 3.제목+내용");
        selectNum = scanner.nextInt();
        scanner.nextLine();

        System.out.print("검색어를 입력하세요 >");
        keyword = scanner.nextLine();

        switch (selectNum){
            case 2:
                searchPost = postdao.searchPost(2, dateQuery, keyword);
                break;
            case 3:
                searchPost = postdao.searchPost(3, dateQuery, keyword);
                break;
            default:
                searchPost = postdao.searchPost(1, dateQuery, keyword);
                break;
        }

        String board;
        System.out.println("게시글 아이디 | 게시판 | 제목 | 작성자 | 작성일 | 조회수");
        System.out.println("-------------------------------------");
        for (Post post : searchPost) {
            Post row = post;

            if (row.getBoard_id() == 1) {
                board = "공지";
            } else {
                board = "일반";
            }
            System.out.println(row.getPost_id() + "\t" + board + "\t" + row.getPost_title() + "\t" + row.getMem_id()
                    + "\t" + row.getCreate_Time() + "\t\t" + row.getView_count());
        }
        System.out.println();
    }
}