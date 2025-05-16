package dbconnection.Comment;

public class CommentDTO {
    int comment_id;
    int post_id;
    String mem_id;
    String content;
    String createTime;

    public CommentDTO(int post_id, String mem_id, String content, String createTime) {
        this.post_id = post_id;
        this.mem_id = mem_id;
        this.content = content;
        this.createTime = createTime;
    }

    public CommentDTO(String mem_id, int comment_id) {
        this.mem_id = mem_id;
        this.comment_id = comment_id;
    }

    public CommentDTO() {}



    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "댓글 번호: " + comment_id + " 작성자: " + mem_id + " 작성일자: " + createTime
                + "\n내용: " + content + "\n게시글 번호: " + post_id;
    }
}
