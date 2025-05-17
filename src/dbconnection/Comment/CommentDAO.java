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
// <editor-fold desc="추가"
  public void insertComment(CommentDTO commentDTO) {
    String sql = "insert into comment (post_id, mem_id, content, createTime) values (?,?,?,?)";

    try {
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);

      pstmt.setInt(1, commentDTO.post_id);
      pstmt.setString(2, commentDTO.mem_id);
      pstmt.setString(3, commentDTO.content);
      pstmt.setString(4, commentDTO.createTime);

      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    MyDBConnection.close(rs, pstmt, con);

  }//end of insertComment
// </editor-fold>

// <editor-fold desc="검색">
  public List<CommentDTO> findAllComment() {
    List<CommentDTO> comments = new ArrayList<>();

    String sql = "select * from Comment order by comment_id desc";

    try {
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();

      SetResult(rs,comments);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      MyDBConnection.close(rs, pstmt, con);
    }
    return comments;
  }//end of findAllComment


  public List<CommentDTO> findAllCommentByPostId(int post_id) {
    List<CommentDTO> comments = new ArrayList<CommentDTO>();

    String sql = "select * from Comment where post_id=? order by createTime desc";

    try{
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);

      pstmt.setInt(1, post_id);

      rs = pstmt.executeQuery();

      SetResult(rs,comments);
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      MyDBConnection.close(rs, pstmt, con);
    }
    return comments;
  }//end of findAllCommentByPostId

  public List<CommentDTO> findAllCommentByMemId(String mem_id) {
    List<CommentDTO> comments = new ArrayList<CommentDTO>();

    String sql = "select * from Comment where mem_id=? order by createTime desc";

    try{
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);

      pstmt.setString(1, mem_id);

      rs = pstmt.executeQuery();

      SetResult(rs,comments);
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      MyDBConnection.close(rs, pstmt, con);
    }
    return comments;
  }//end of findAllCommentByMemID

  public List<CommentDTO> findCommentByPostId(int comment_id) {
    List<CommentDTO> comments = new ArrayList<>();

    String sql = "select * from Comment where comment_id=?";

    try {
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);

      pstmt.setInt(1,comment_id);

      rs = pstmt.executeQuery();

      SetResult(rs,comments);
    } catch (SQLException e){
      e.printStackTrace();
    } finally {
      MyDBConnection.close(rs, pstmt, con);
    }
    return comments;
  }//end of findCommentByPostId

  // </editor-fold>

// <editor-fold desc="수정 && 삭제">
  public boolean isOwner(int comment_id,String mem_id) {
    String sql = "select * from Comment where comment_id=? and mem_id=?";

    try {
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);

      pstmt.setInt(1, comment_id);
      pstmt.setString(2, mem_id);

      rs = pstmt.executeQuery();

      if (rs.next()) {
        return true;
      }
     } catch (SQLException e){
      e.printStackTrace();
    } finally {
      MyDBConnection.close(rs, pstmt, con);
    }
    return false;
  }//end of isOwner

  public void updateComment(String content, int comment_id) {
    String sql = "update comment set content=? where comment_id=?";

    try {
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);

      pstmt.setString(1, content);
      pstmt.setInt(2, comment_id);

      pstmt.executeUpdate();

    } catch (SQLException e){
      e.printStackTrace();
    } finally {
      MyDBConnection.close(rs, pstmt, con);
    }
  }//end of updateComment

public void deleteComment(int comment_id) {
    String sql = "delete from comment where comment_id=?";

    try {
      con = MyDBConnection.getConnection();
      pstmt = con.prepareStatement(sql);

      pstmt.setInt(1,comment_id);

      pstmt.executeUpdate();
    } catch (SQLException e){
      e.printStackTrace();
    } finally {
      MyDBConnection.close(rs, pstmt, con);
    }
}

//</editor-fold>

//<editor-fold desc="작업 편의용">
  //각 Select 에서 불러오는 값을 넣는 과정 메서드화
  private void SetResult(ResultSet rs,List<CommentDTO> comments) throws SQLException {
    while (rs.next()) {
      CommentDTO commentDTO = new CommentDTO();

      commentDTO.setComment_id(rs.getInt("comment_id"));
      commentDTO.setPost_id(rs.getInt("post_id"));
      commentDTO.setMem_id(rs.getString("mem_id"));
      commentDTO.setContent(rs.getString("content"));
      commentDTO.setCreateTime(rs.getString("createTime"));

      comments.add(commentDTO);
    }
  }//end of SetResult
//</editor-fold>
}//end of class
