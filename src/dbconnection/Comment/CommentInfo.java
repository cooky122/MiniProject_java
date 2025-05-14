package dbconnection.Comment;

public class CommentInfo {
    int comment_id;
    int post_id;
    String mem_id;
    String content;
    String createTime;

    public CommentInfo(int comment_id, int post_id, String mem_id, String content, String createTime) {
        this.comment_id = comment_id;
        this.post_id = post_id;
        this.mem_id = mem_id;
        this.content = content;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "댓글 번호: " + comment_id + " 작성자: " + mem_id + " 작성일자: " + createTime
                + "\n내용: " + content + "작성 게시글: " + post_id;
    }
}
