package dbconnection.post;

public class PostMain {
    public static void start() {
        //while문으로 번호 입력받아서 crud실행 코드 넣기
    }

    //main메소드는 테스트용, 나중에 완성할때 main 하나에 합쳐야함
    public static void main(String[] args) {
        Post post = new Post();
        PostDAO dao = new PostDAO();

        post.setBoard_id(1);
        post.setMem_id("user01");
        post.setPost_title("test");
        post.setContent("test content");
        post.setPost_type(false);

        dao.insertPost(post);
    }
}
