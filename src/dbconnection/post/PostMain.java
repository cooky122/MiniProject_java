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

        //조회 후 댓글crud로 이동하거나 좋아요 누를지 말지 선택, view_count 업데이트
        //댓글 crud 이동은 commentMain에서 그대로 가져왔습니다.
        System.out.println("1.댓글 입력 / 2.댓글 수정 / 3.댓글 삭제 / 4.댓글 검색 / 5.좋아요 / 6.게시글 수정 / 7.목록으로 돌아기기");
        int selectAC = scanner.nextInt();
        scanner.nextLine();

        switch (selectAC) {
            case 1:
                //InsetComment();
                break;
            case 2:
                //UpdateComment();
                break;
            case 3:
                //DeleteComment();
                break;
            case 4:
                //SelectComment();
                break;
            case 5:
                postdao.updateLike(selectPost);
                break;
            case 6:
                //게시글 수정 메소드
                updateProcess(post);
                break;
            case 7:
                //별도의 액션 없이 메뉴로 돌아가기
                break;
            default:
                System.out.println("잘못된 값 입력");
        }
    }

    //전체 게시물 리스트로 출력
    private static void printPostList() {
        List<Post> postList = new ArrayList<Post>();

        postList = postdao.findPostAll();
        Post row = new Post();
        String board="";

        System.out.println("번호 | 게시판 | 제목 | 작성자 | 작성일 | 조회수");
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

    }

    private static void deleteProcess() {
    }

    private static void searchProcess() {
    }



}