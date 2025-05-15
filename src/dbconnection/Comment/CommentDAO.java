package dbconnection.Comment;

import dbconnection.MyDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
  Connection con = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  public void insertComment(CommentDTO commentDTO) {
    String sql = "insert into comment (post_id, mem_id, content, createTime, like_count) values (?,?,?,?,?)";

    try {
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);

      pstmt.setInt(1, commentDTO.post_id);
      pstmt.setString(2, commentDTO.mem_id);
      pstmt.setString(3, commentDTO.content);
      pstmt.setString(4, commentDTO.createTime);
      pstmt.setInt(5, 0);

      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    MyDBConnection.close(rs, pstmt, con);

  }//end of insert

  public List<CommentDTO> findALlComment() {
    List comments = new ArrayList<CommentDTO>();

    String sql = "select * from Comment order by comment_id desc";

    try {
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setComment_id(rs.getInt("comment_id"));
        commentDTO.setPost_id(rs.getInt("post_id"));
        commentDTO.setMem_id(rs.getString("mem_id"));
        commentDTO.setContent(rs.getString("content"));
        commentDTO.setCreateTime(rs.getString("createTime"));

        comments.add(commentDTO);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      MyDBConnection.close(rs, pstmt, con);
    }
    return comments;
  }

  public List<CommentDTO> findALlCommentByPostId(int post_id) {
    List comments = new ArrayList<CommentDTO>();

    String sql = "select * from Comment where post_id=? order by comment_id desc";

    try{
      pstmt.setInt(1, post_id);

      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setComment_id(rs.getInt("comment_id"));
        commentDTO.setPost_id(rs.getInt("post_id"));
        commentDTO.setMem_id(rs.getString("mem_id"));
        commentDTO.setContent(rs.getString("content"));
        commentDTO.setCreateTime(rs.getString("createTime"));

        comments.add(commentDTO);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      MyDBConnection.close(rs, pstmt, con);
    }
    return comments;
  }//end of findAllCommentByPostId

  /*public int getCommentId() throws SQLException {
    String sql = "select max(comment_id)+1 from comment";

      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);
      rs=pstmt.executeQuery();

      rs.next();

      int lastCommentId = rs.getInt(1);

      MyDBConnection.close(rs, pstmt, con);

    return lastCommentId;
  }*/
}
