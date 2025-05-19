package dbconnection.post;

public class Post {
    private int post_id;
    private int board_id;
    private String mem_id;
    private String post_title;
    private String content;
    private String create_Time;
    private String update_time;
    private int view_count;
    private int like_count;
    private boolean post_type;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_Time() {
        return create_Time;
    }

    public void setCreate_Time(String create_Time) {
        this.create_Time = create_Time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public boolean isPost_type() {
        return post_type;
    }

    public void setPost_type(boolean post_type) {
        this.post_type = post_type;
    }

    @Override
    public String toString() {
        return
                "작성자: " + mem_id + "\n" +
                "제목: " + post_title + "\n" +
                "내용:\n" + content + "\n" +
                "작성일: " + create_Time + " | 조회수:" + view_count + " | 좋아요: " + like_count + "\n";
    }
}
