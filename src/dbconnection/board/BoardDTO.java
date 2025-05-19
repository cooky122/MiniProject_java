package dbconnection.board;

public class BoardDTO {
    int board_id ;
    String mem_id;
    String board_title;
    String board_createdate;

    public BoardDTO(int board_id, String mem_id, String board_title, String board_createdate) {
        this.board_id = board_id;
        this.mem_id = mem_id;
        this.board_title = board_title;
        this.board_createdate = board_createdate;
    }

    /*public BoardDTO(String mem_id, String board_title, String board_createdate) {
        this.mem_id = mem_id;
        this.board_title = board_title;
        this.board_createdate = board_createdate;
    }*/

    public BoardDTO() {}

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

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_createdate() {
        return board_createdate;
    }

    public void setBoard_createdate(String board_createdate) {
        this.board_createdate = board_createdate;
    }

    @Override
    public String toString() {
        return  "게시판 번호 : " + board_id +
                "게시판 생성자 : " + mem_id +
                "게시판 이름 : " + board_title +
                "게시판 생성일자 : " + board_createdate;
    }
}

