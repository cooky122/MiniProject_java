package dbconnection.post;

import dbconnection.Comment.CommentMain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//<editor-fold desc="원하는 이름">
//</editor-fold>

/*
게시물 작성 메인페이지
 */
public class PostMain {
    public static Scanner scanner = new Scanner(System.in);
    public static PostDAO postdao = new PostDAO();

    //<editor-fold desc="게시물 CRUD 시작 선택지">
    public static void start() {
        System.out.println("<게시글 작성 페이지>");

        //while 문으로 번호 입력받아서 다음 행동 수행
        while(true){
            //테이블 전체를 보여주는 메소드
            //소속 게시판 | 제목 | 작성자 | 작성일 | 조회수 순서로 출력
            printPostList();

            System.out.println("1.게시글 조회 / 2.게시글 작성 / 3.게시글 삭제 / 4.게시글 검색 / 5. 종료");
            int select = scanner.nextInt();
            scanner.nextLine();

            if(select == 5) //게시글에 대한 작업 종료
                break;

            switch(select) {
                case 1:
                    readProcess();    //조회할 게시글 id를 입력받는 메소드
                    break;
                case 2:
                    insertProcess();    //작성할 게시글 제목, 내용을 입력받는 메소드
                    break;
                case 3:
                    deleteProcess();    //삭제할 게시글 id를 입력받는 메소드
                    break;
                case 4:
                    searchProcess();    //게시글 검색어를 입력받는 메소드
                    break;
               default:
                    break;
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="게시물 조회">
    public static void readProcess() {
        System.out.print("조회할 게시글 번호 > ");
        int selectPost = scanner.nextInt();
        scanner.nextLine();

        //PostDAO 의 findPost 메소드에 post_id를 매개변수로 넘겨 해당 게시글 정보를 객체로 받아오기
        Post post = postdao.findPost(selectPost);

        //board_id가 1이면 공지게시판, 2이면 자유게시판
        if(post.getBoard_id() == 1){
            System.out.println("<공지게시판>");
        }else if(post.getBoard_id() == 2){
            System.out.println("<자유게시판>");
        }
        System.out.println(post);   //DB 에서 가져온 post 객체 출력
        System.out.println();

        postdao.updateView(selectPost); //해당 게시글의 view_count 업데이트

        while(true) {
            //좋아요 할지 말지 선택
            System.out.println(" 1.좋아요 / 2.게시글 수정 / 3.다음");
            int selectACT = scanner.nextInt();
            scanner.nextLine();

            if(selectACT == 3){ //다음 선택시 아무 행동도 하지 않고 넘어감
                break;
            }else if(selectACT == 1){
                postdao.updateLike(selectPost); //좋아요 선택시 좋아요 업데이트 후 반복문 처음으로 이동(게시글 수정을 할수도 있기 때문에)
            }else if(selectACT == 2){
                updateProcess(post); //게시글 수정 선택시 update 메소드 호출, 수정할 제목, 내용 입력받는 메소드, 수행 후 반복문 처음으로 이동(게시글 좋아요 누를수도 있기 때문에)
            }else{
                System.out.println("잘못된 입력입니다.");
            }
        }

        //여기에 댓글 CRUD 기능 삽입
        //매개변수로 현재 읽은 게시물에 대한 post 객체 넘겨주면 될듯
        CommentMain.Start();
    }
    //</editor-fold>

    //<editor-fold desc="게시물 리스트 출력">
    //전체 게시물 리스트로 출력
    public static void printPostList() {
        //List<Post> postList = new ArrayList<Post>();    //게시글 객체들을 저장할 리스트

        List<Post> postList = postdao.findPostAll();   //PostDAO 의 findPostAll 메소드로 post 테이블에서 게시글 객체 모두 가져오기
        String board="";

        System.out.println("게시글 아이디 | 게시판 | 제목 | 작성자 | 작성일 | 조회수"); //메뉴
        System.out.println("-------------------------------------");
        for (Post post : postList) {
            //board_id에 따라 게시판 구분하기
            //1이면 공지게시판에 속하고 2이면 자유게시판
            //boardDAO 에서 메소드 받아서 처리하는거로 수정하기
            if (post.getBoard_id() == 1) {
                board = "공지";
            } else if(post.getBoard_id() == 2){
                board = "자유";
            }

            //게시글 출력
            System.out.println(post.getPost_id() + "\t" + board + "\t" + post.getPost_title() + "\t" + post.getMem_id()
                    + "\t" + post.getCreate_Time() + "\t\t" + post.getView_count());
        }
        System.out.println();
    }
    //</editor-fold>

    //<editor-fold desc="게시물 작성">
    //insert 값을 입력받는 메소드
    public static void insertProcess() {
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
        post_type = a.equals("y");

        //post dto 객체에 값 넣기
        post.setBoard_id(board_id);
        post.setMem_id(mem_id);
        post.setPost_title(post_title);
        post.setContent(content);
        post.setPost_type(post_type);

        //PostDAO 의 insertPost 호출, db에 insert 수행
        postdao.insertPost(post);
    }
    //</editor-fold>

    //<editor-fold desc="게시물 수정사항 작성">
    //수정할 제목, 내용을 입력받는 메소드
    public static void updateProcess(Post post) {
        //수정하려는 사람이 작성자 본인인지 확인, 맞으면 수정 진행하고 아니면 updateProcess 메소드 종료
//        if(!<여기에 작성자 mem_id 가져오기>.equals(post.getMem_id())){
//            System.out.println("게시물 작성자만 수정할 수 있습니다.");
//            return;
//        }

        System.out.println("<게시글 수정 페이지>");
        System.out.println(post);   //기존 제목, 내용 가져와 출력
        System.out.println();

        System.out.print("제목 수정: ");    //수정한 제목 입력
        String edit_title = scanner.nextLine();
        post.setPost_title(edit_title);

        System.out.println("내용 수정: ");  //수정한 내용 입력
        String edit_content = scanner.nextLine();
        post.setContent(edit_content);

        //자바에서 현재 날짜 시간 받아서 post.setUpdate_Time 으로 업데이트
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        post.setUpdate_time(now.format(formatter));

        postdao.updatePost(post);   //updatePost 로 post 객체 전달
    }
    //</editor-fold>

    //<editor-fold desc="게시물 삭제">
    public static void deleteProcess() {
        System.out.print("삭제할 게시글 번호 > ");  //삭제할 게시글 post_id 입력
        int selectPost = scanner.nextInt();
        scanner.nextLine();

        postdao.deletePost(selectPost); //deletePost 에 입력한 post_id 전달
        System.out.println();

        System.out.println("게시글이 삭제되었습니다.\n");
    }
    //</editor-fold>

    //<editor-fold desc="게시물 검색">
    public static void searchProcess() {
        List<Post> searchPost;

        //sql 문을 실행하는 날짜에서 검색범위를 1년 또는 1개월로 제한하는 쿼리문
        String dateQuery = "create_Time > date_add(now(), interval -1";

        System.out.println("기간 선택");
        System.out.println("1.전체 | 2.지난 1년 | 3.지난 1달");
        int selectNum = scanner.nextInt();
        scanner.nextLine();

        switch (selectNum){
            case 1:
                dateQuery = ""; //기간을 전체로 하면 dateQuery 가 필요하지 않기 때문에 초기화
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
        String keyword = scanner.nextLine();

        searchPost = switch (selectNum) {
            case 2 -> postdao.searchPost(2, dateQuery, keyword);
            case 3 -> postdao.searchPost(3, dateQuery, keyword);
            default -> postdao.searchPost(1, dateQuery, keyword);
        };

        String board;
        System.out.println("게시글 아이디 | 게시판 | 제목 | 작성자 | 작성일 | 조회수");
        System.out.println("-------------------------------------");
        for (Post post : searchPost) {
            if (post.getBoard_id() == 1) {
                board = "공지";
            } else {
                board = "일반";
            }
            System.out.println(post.getPost_id() + "\t" + board + "\t" + post.getPost_title() + "\t" + post.getMem_id()
                    + "\t" + post.getCreate_Time() + "\t\t" + post.getView_count());
        }
        System.out.println();
    }
    //</editor-fold>
}