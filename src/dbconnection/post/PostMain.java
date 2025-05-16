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

        //제목 입력
        System.out.print("제목: ");
        String post_title = scanner.nextLine();
        //글 내용 입력
        System.out.println("내용: ");
        String content = scanner.nextLine();

        System.out.println("공지글로 등록");
        scanner.nextLine();


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