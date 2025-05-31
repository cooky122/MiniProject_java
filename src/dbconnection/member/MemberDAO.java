package dbconnection.member;

import dbconnection.MyDBConnection;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public boolean logIn (String id, String pw) {
        String sql = "select mem_pw from member where mem_id=?";
        boolean result = false;

        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,id);

            rs = pstmt.executeQuery();

            rs.next();

            if(pw.equals(rs.getString("mem_pw"))){
                result = true;
            } else {
                result = false;
            }


        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
        return result;
    }//end of logIn

    public boolean isAdmin(String id) {
        String sql = "select grade from member where mem_id=?";
        boolean result = false;

        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,id);

            rs = pstmt.executeQuery();

            rs.next();

            if("매니저".equals(rs.getString("grade"))){
                result = true;
            } else {
                result = false;
            }


        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
        return result;
    }

    public String findNameAndGradeById(String id){
        String sql = "select name,grade from member where mem_id=?";
        String result = "";

        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,id);

            rs = pstmt.executeQuery();

            rs.next();
            result = rs.getString("name");
            result += " / " + rs.getString("grade");
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }

        return result;
    }//end of findNameById

    public List<MemberDTO> findAllMember(){
        List<MemberDTO> member = new ArrayList<MemberDTO>();

        String sql = "select * from member";

        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                MemberDTO memberDTO = new MemberDTO();

                memberDTO.mem_id = rs.getString("mem_id");
                memberDTO.mem_email = rs.getString("mem_email");
                memberDTO.mem_pw = rs.getString("mem_pw");
                memberDTO.name = rs.getString("name");
                memberDTO.grade = rs.getString("grade");
                memberDTO.nickname = rs.getString("nickname");

                member.add(memberDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
        return member;
    }//end of findAllMember

    public MemberDTO findMemberById(String id){
        MemberDTO member = null;

        String sql = "select * from member where mem_id=?";

        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1,id);
            rs = pstmt.executeQuery();

            if(rs.next()){
                member = new MemberDTO();

                member.mem_id = rs.getString("mem_id");
                member.mem_email = rs.getString("mem_email");
                member.nickname = rs.getString("nickname");
                member.grade = rs.getString("grade");
                member.name = rs.getString("name");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
        return member;

    }

    public void deleteMember(String id){
        List<MemberDTO> member = new ArrayList<MemberDTO>();

        String sql = "delete from member where mem_id=?";

        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1,id);

            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }//end of deleteMember

}
