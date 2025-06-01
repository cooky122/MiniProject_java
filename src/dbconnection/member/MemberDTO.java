package dbconnection.member;

public class MemberDTO {
    String mem_id;
    String mem_pw;
    String mem_email;
    String nickname;
    String name;
    String last_login;
    String grade;
    String join_date;
    String visit_count;
    String post_count;
    String comment_count;
    String profile_img_url;


    public MemberDTO(String mem_id, String mem_pw, String mem_email, String nickname, String name, String last_login, String grade, String join_date, String visit_count, String post_count, String comment_count, String profile_img_url) {
        this.mem_id = mem_id;
        this.mem_pw = mem_pw;
        this.mem_email = mem_email;
        this.nickname = nickname;
        this.name = name;
        this.last_login = last_login;
        this.grade = grade;
        this.join_date = join_date;
        this.visit_count = visit_count;
        this.post_count = post_count;
        this.comment_count = comment_count;
        this.profile_img_url = profile_img_url;
    }

    public MemberDTO() {}

    @Override
    public String toString() {
        return "회원ID: " + mem_id + " / 닉네임: " + nickname + " / 등급: " + grade +
                "\n이메일: " + mem_email + " / 이름: " + name ;
    }
}
