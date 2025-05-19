package dbconnection.post;

import dbconnection.MyDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//PostDAO 클래스
//게시물 작업에 대해 DB와 연결
public class PostDAO {
    Connection con = null;
    PreparedStatement pstmt  = null;
    ResultSet rs = null;

    //<editor-fold desc="게시물 insert 메소드">
    public void insertPost(Post post){
        String sql = "insert into post (board_id, mem_id, post_title, content, post_type)" + "values (?, ?, ?, ?, ?)";

        con = MyDBConnection.getConnection();

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, post.getBoard_id());
            pstmt.setString(2, post.getMem_id());
            pstmt.setString(3, post.getPost_title());
            pstmt.setString(4, post.getContent());
            pstmt.setBoolean(5, post.isPost_type());

            pstmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }
    //</editor-fold>

    //<editor-fold desc="게시물 1개 select 메소드">
    public Post findPost(int post_id){
        Post post = new Post();

        String sql = "select * from post where post_id=?";
        con = MyDBConnection.getConnection();

        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, post_id);
            rs = pstmt.executeQuery();

            rs.next();
            post.setPost_id(rs.getInt("post_id"));
            post.setBoard_id(rs.getInt("board_id"));
            post.setMem_id(rs.getString("mem_id"));
            post.setPost_title(rs.getString("post_title"));
            post.setContent(rs.getString("content"));
            post.setCreate_Time(rs.getString("create_Time"));
            post.setView_count(rs.getInt("view_count"));
            post.setLike_count(rs.getInt("like_count"));
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }

        return post;
    }
    //</editor-fold>

    //<editor-fold desc="게시물 전체 select 메소드">
    public List<Post> findPostAll() {
        List<Post> postList = new ArrayList<>();

        String sql = "select * from post";
        con = MyDBConnection.getConnection();

        try{
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                Post post = new Post();

                post.setPost_id(rs.getInt("post_id"));
                post.setBoard_id(rs.getInt("board_id"));
                post.setPost_title( rs.getString("post_title"));
                post.setMem_id(rs.getString("mem_id"));
                post.setCreate_Time(rs.getString("create_Time"));
                post.setView_count(rs.getInt("view_count"));

                postList.add(post);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }

        return postList;
    }
    //</editor-fold>

    //<editor-fold desc="게시물 update 메소드">
    public void updatePost(Post post) {
        String sql = "update post set post_title=?, content=? where post_id=?";

        con = MyDBConnection.getConnection();

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, post.getPost_title());
            pstmt.setString(2, post.getContent());
            pstmt.setInt(3, post.getPost_id());

            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }
    //</editor-fold>

    //<editor-fold desc="게시물 delete 메소드">
    public void deletePost(int post_id) {
        String sql = "delete from post where post_id=?";

        con = MyDBConnection.getConnection();

        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, post_id);

            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }
    //</editor-fold>

    //<editor-fold desc="게시물 좋아요 업데이트 메소드">
    public void updateLike(int post_id) {
        String sql = "update post set like_count = like_count+1 where post_id=?";

        con = MyDBConnection.getConnection();

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, post_id);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }
    //</editor-fold>

    //<editor-fold desc="게시물 조회수 업데이트 메소드">
    public void updateView(int post_id) {
        String sql = "update post set view_count = view_count+1 where post_id=?";

        con = MyDBConnection.getConnection();

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, post_id);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }
    //</editor-fold>

    //<editor-fold desc="특정 게시물 select 메소드">
    public List<Post> searchPost(int i, String dateQuery, String keyword) {
        List<Post> searchPost = new ArrayList<>();

        String sql = switch (i) {
            case 1 -> "select * from post where post_title like '%"+ keyword +"%'";
            case 2 -> "select * from post where content like '%"+ keyword +"%'";
            case 3 -> "select * from post where post_title like '%"+ keyword +"%' or content like '%"+ keyword +"%'";
            default -> throw new IllegalStateException("입력 오류");
        };
        sql += (" and " + dateQuery);

        con = MyDBConnection.getConnection();

        try{
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                Post post = new Post();
                post.setPost_id(rs.getInt("post_id"));
                post.setBoard_id(rs.getInt("board_id"));
                post.setPost_title( rs.getString("post_title"));
                post.setMem_id(rs.getString("mem_id"));
                post.setCreate_Time(rs.getString("create_Time"));
                post.setView_count(rs.getInt("view_count"));

                searchPost.add(post);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }

        return searchPost;
    }
    //</editor-fold>
}
