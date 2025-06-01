package dbconnection;

import dbconnection.Comment.CommentDTO;
import dbconnection.board.BoardMain;
import dbconnection.member.MemberDAO;
import dbconnection.member.MemberDTO;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Scanner sc = new Scanner(System.in);
    private static MemberDAO memDao = new MemberDAO();
    public static String ID;


//
    public static void main(String[] args) {
        while (true) {
            System.out.println("===로그인===");
            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("\nPW: ");
            String pw = sc.nextLine();
            if(memDao.logIn(id, pw)){
                ID = id;
                System.out.println(memDao.findNameAndGradeById(id) + "님 카페에 오신걸 환영합니다.");
                if(memDao.isAdmin(id)) {
                    System.out.println("유저 관리 페이지");
                    while (true) {
                        System.out.println("1. 유저 목록 조회 / 2. 유저 강퇴 / 3. 유저 검색 / 4. 보드로 이동");
                        String ans = sc.nextLine();

                        switch (ans) {  //유저 목록 조회
                            case "1":
                                printAll(memDao.findAllMember());
                                break;
                            case "2":
                                System.out.println("강퇴 하고자 하는 유저의 ID 를 입력해주세요");
                                String dropID = sc.nextLine();
                                System.out.println("정말로 강퇴하시겠습니까?");
                                System.out.println("1. 예 / 2. 아니오");
                                System.out.print(">>");
                                ans = sc.nextLine();
                                if (ans.equals("1")) {
                                    if (dropID.equals(ID)) {
                                        System.out.println("본인을 강퇴하면 프로그램이 종료됩니다. 정말로 강퇴하시겠습니까?");
                                        System.out.println("1.예 / 2. 아니오");
                                        System.out.print(">>");
                                        ans = sc.nextLine();
                                        if (ans.equals("1")) {
                                            memDao.deleteMember(dropID);
                                            System.out.println("관리자에 의해 카페 이용이 정지되었습니다.");
                                            System.exit(0);
                                        }
                                    }
                                    memDao.deleteMember(dropID);
                                    System.out.println("강퇴 완료. 첫 페이지로 돌아갑니다.");
                                } else {
                                    System.out.println("강퇴를 취소하였습니다.");
                                }
                                break;
                            case "3":
                                System.out.println("검색을 원하는 유저의 ID를 입력해 주십시오");
                                id = sc.nextLine();
                                System.out.println(memDao.findMemberById(id));
                                break;
                            case "4":
                                BoardMain.BoardStart(ID);
                                break;

                            default:
                                return;
                        }
                    }

                }

                BoardMain.BoardStart(ID);
                break;
            }else {
                System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
            }
        }
    }//end of main

    public static void printAll(List<MemberDTO> list) {
        System.out.println("========== 유저 목록 ==========");
        for (MemberDTO member : list ) {
            System.out.println(member);
            System.out.println("--------------------------------");
        }
    }//end of printAll
}