package dbconnection.post;

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
            if(select == 5)
                break;

            switch(select) {
                case 1:
                    insertProcess();    //insert 값을 입력받는 메소드
                    break;
                case 2:
                    updateProcess();    //update 값을 입력받는 메소드
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

    //전체 게시물 리스트로 출력
    private static void printPostList() {
        List<Post> postList = new ArrayList<Post>();

        postList = postdao.findPostAll();
        Post row = new Post();
        String board;

        System.out.println("\t제목\t\t작성자\t작성일\t\t조회수");
        for(int i=0; i<postList.size(); i++){
            row = postList.get(i);

            if(row.getBoard_id() == 1){
                board = "공지";
            }
            //System.out.println(row.);
        }
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

    private static void updateProcess() {
    }

    private static void deleteProcess() {
    }

    private static void searchProcess() {
    }



}