package dbconnection.board;

import dbconnection.MyDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public void insertBoard(BoardDTO boardDTO) {
        String sql = "insert into board (mem_id, board_title, board_createdate)" + "values(?,?,?)";

        try {
            //db연결
            con = MyDBConnection.getConnection();

            //sql 전달
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, boardDTO.getMem_id());
            pstmt.setString(2, boardDTO.getBoard_title());
            pstmt.setString(3, boardDTO.getBoard_createdate());

            pstmt.executeUpdate();

            System.out.println("게시판 등록 성공"); //추후 삭제

        } catch (SQLException e) {
            System.out.println("게시판 등록 실패");
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }//end of insert


    public List<BoardDTO> findAllBoards(){
        List<BoardDTO> boards = new ArrayList<BoardDTO>();
//        String sql = "select * from board order by board_id desc";
        String sql = "select * from board";
        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                BoardDTO board = new BoardDTO();
                board.setBoard_id(rs.getInt("board_id"));
                board.setMem_id(rs.getString("mem_id"));
                board.setBoard_title(rs.getString("board_title"));
                board.setBoard_createdate(rs.getTimestamp("board_createdate").toString());

                boards.add(board);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
        return boards;
    }// end of findAllBoards


    public BoardDTO findBoardsById (int board_id){
        String sql = "select * from board where board_id = ?";
        BoardDTO board = null;

        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_id);
            rs = pstmt.executeQuery();

            if(rs.next()){
                board = new BoardDTO();
                board.setBoard_id(rs.getInt("board_id"));
                board.setMem_id(rs.getString("mem_id"));
                board.setBoard_title(rs.getString("board_title"));
                board.setBoard_createdate(rs.getTimestamp("board_createdate").toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }
        return board;
    }//end of findBoardById

    public List<BoardDTO> findBoardId(){
        List<BoardDTO> boards = new ArrayList<BoardDTO>();
        String sql = "select  board_id, board_title from board";

        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                BoardDTO board = new BoardDTO();
                board.setBoard_id(rs.getInt("board_id"));
                board.setBoard_title(rs.getString("board_title"));

                boards.add(board);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
        return boards;
    }// end of


    public void updateBoard(int board_id, String new_title){
        String sql = "update board set board_title =? where board_id=?";

        try{
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, new_title);
            pstmt.setInt(2, board_id);

            int result = pstmt.executeUpdate();
            if(result > 0){
                System.out.println("제목이 수정되었습니다");
            }else{
                System.out.println("해당 게시판이 없습니다");
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }//end of updateBoard


    public void deleteBoard(int board_id) {
        String sql = "delete from board where board_id = ?";

        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_id);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("게시판이 삭제되었습니다.");
            } else {
                System.out.println("해당 게시판이 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }// end of deleteBoard
}
