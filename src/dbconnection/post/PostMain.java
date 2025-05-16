package dbconnection.post;

import java.util.Scanner;

public class PostMain {
    public static Scanner scanner = new Scanner(System.in);
    public static PostDAO postdao = new PostDAO();

    public static void start() {
        //while 문으로 번호 입력받아서 crud 실행 코드 넣기
        while(true){
            System.out.println("게시글 작성 페이지");
            System.out.println("1.게시글 작성 / 2.게시글 수정 / 3.게시글 삭제 / 4.게시글 검색");

            int select = scanner.nextInt();
            scanner.nextLine();
            switch(select){
                case 1:
                    insertProcess();
                    break;
                case 2:
                    updateProcess();
                    break;
                case 3:
                    deleteProcess();
                    break;
                case 4:
                    searchProcess();
                    break;
                default:
                    break;
            }
        }
    }

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

        System.out.println("공지글로 등록(y/n)");
        String a = scanner.nextLine();

        boolean post_type;
        if(a.equals("y")){
            post_type = true;
        }else{
            post_type = false;
        }

        post.setBoard_id(board_id);
        post.setMem_id(mem_id);
        post.setPost_title(post_title);
        post.setContent(content);
        post.setPost_type(post_type);

        postdao.insertPost(post);
    }

    private static void updateProcess() {
    }

    private static void deleteProcess() {
    }

    private static void searchProcess() {
    }



}

//        Post post = new Post();
//        PostDAO dao = new PostDAO();
//
//        post.setBoard_id(1);
//        post.setMem_id("user01");
//        post.setPost_title("test");
//        post.setContent("test content");
//        post.setPost_type(false);
//
//        dao.insertPost(post);