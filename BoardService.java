package miniProj1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BoardService {
	public static List<Boards> blist = new ArrayList<>(); 
	
	public void list() {
		System.out.println("목록");
		System.out.println("번호 | 작성자 |  제목  | 조회수 |   작성일시   ");
		for (Boards board : blist) {
			board.print();
		}
		blist.stream().forEach(board -> board.print());
		blist.stream().forEach(Boards::print);

		System.out.println();
		System.out.println("1. 게시물 상세보기");
		System.out.println("2. 이전 화면으로");
		System.out.println();
		System.out.print(">>>원하시는 번호를 입력해주세요: ");
		String cmd3 = Main.scanner.nextLine();
		System.out.println();

		switch (cmd3) {
		case "1":
			System.out.println("게시물 상세보기 함수호출");
			System.out.println();
			break;

		case "2":
			//이전화면으로 가기(OOO님 반갑습니다! 화면)
			break;
		}


	}

	public void view() {
		System.out.print("상세보기를 원하는 게시물번호?");
		String strNo = Main.scanner.nextLine();
		try {
			final int no = Integer.parseInt(strNo);
			//for (Board board : list) {
			//if (board.getNo() == no) {
			////게시물 상세 정보 출력 
			//detailView(board);
			//return;
			//}
			//}
			//System.out.println("게시물 번호가 존재하지 않습니다");

			blist.stream()
			.filter(boards -> boards.getBno() == no)
			.findFirst()
			.ifPresentOrElse(boards -> detailView(boards), 
					() -> System.out.println("게시물 번호가 존재하지 않습니다"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void detailView(Boards boards) {
		System.out.println("게시물 상세보기");
		boards.detailView();

		while(true) {
			System.out.println("1. 삭제 ");
			System.out.println("2. 수정");
			System.out.println("3. 이전메뉴로");
			System.out.println("원하는 메뉴 ?");
			String menuNo = Main.scanner.nextLine();
			switch(menuNo) {
			case "1":
				//삭제 
				delete(boards);
				return;
			case "2":
				//수정
				System.out.println("수정작업 구현중..");
				break;
			case "3":
				//이전으로
				return;
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다");
				break;
			}
		}

	}

	public void delete(Boards boards) {
		blist.remove(boards);
	}

	public void insert(String title, String content) throws Exception {
		//		Boards_Main.list.add(new Boards(title, content));
		System.out.println("신규 게시물이 등록되었습니다 ^.^");
	}

	public void insertForm() throws Exception {
		System.out.println("등록화면");
		String title;
		String content;
		String menu;
		System.out.print("제목 : ");
		title = Main.scanner.nextLine();
		System.out.print("내용 : ");
		content = Main.scanner.nextLine();

		while(true) {
			System.out.println("1. 저장 ");
			System.out.println("2. 취소하고 이전메뉴로");
			System.out.println("원하는 메뉴 ?");
			String menuNo = Main.scanner.nextLine();
			switch(menuNo) {
			case "1":
				//등록 
				insert(title, content);
				return;
			case "2":
				//이전으로
				return;
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다");
				break;
			}
		}
	}
}
