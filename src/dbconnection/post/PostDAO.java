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

    public List<Post> findPostAll() {
        List<Post> postList = new ArrayList<Post>();

        String sql = "select * from post";
        con = MyDBConnection.getConnection();

        try{
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                Post post = new Post();

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

    public void updatePost(Post post) {

    }

    public void deletePost(Post post) {

    }
}
