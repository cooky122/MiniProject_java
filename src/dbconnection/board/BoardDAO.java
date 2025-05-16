package dbconnection.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardDAO {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public void insertBoard(BoardDTO boardDTO){
        String sql = "insert into board (board_id, mem_id, board_title, board_createdate) values
    }

}
