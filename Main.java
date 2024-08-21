package miniProj1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static Scanner scanner = new Scanner(System.in);
	


	public static void main(String[] args) throws Exception {

		BoardService bs = new BoardService();
		MemberService ms = new MemberService();


		while (true) {
			System.out.println("-------------------------------");
			System.out.println("	미니 프로젝트 1차");
			System.out.println("-------------------------------");
			System.out.println("[[[메뉴를 선택해주세요.]]]");
			System.out.println("1. 회원 가입");
			System.out.println("2. 로그인");
			System.out.println("3. 아이디 찾기");
			System.out.println("4. 비밀번호 초기화");
			System.out.println("5. 종료");
			System.out.println();
			
			System.out.print(">>>원하시는 번호를 입력해주세요: ");
			String cmd = scanner.nextLine();
			System.out.println();
			
			switch (cmd) {
			case "5":
				System.out.println("프로그램 종료");
				System.exit(0);
				break;
			case "1": //회원 가입
				ms.signup();
				break;
			case "2": //로그인
				ms.login();
				break;
			case "3": //아이디 찾기
				ms.searchId();
				break;
			case "4": //비밀번호 초기화
				ms.resetPw();
				break;
			}
		}
	}

}
