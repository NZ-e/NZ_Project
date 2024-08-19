package miniProj1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BoardService {

	Boards boards = null; 
	BoardsDB bDB = new BoardsDB();
	String cmd = null;

	public void list(Members members) {
		//로그인 후 2번을 누르면 나오는 게시물 목록 화면
		boolean bool = true;
		
		while (bool) {
			System.out.println("[[[게시물 목록]]]");
			System.out.println("번호    작성자      제목      조회수       작성일시   ");	

			boards = new Boards();
			boards.setBid(members.getMid());

			List<Boards> boardsList = bDB.bSelectBoards(boards);
			boardsList.stream().forEach(Boards::print);
			System.out.println();


			System.out.println("1. 게시물 상세보기");
			System.out.println("2. 게시물 등록");
			System.out.println("3. 이전 화면으로");
			System.out.println();
			System.out.print(">>>원하시는 번호를 입력해주세요: ");
			cmd = Main.scanner.nextLine();
			System.out.println();



			switch (cmd) {
			case "1": //게시물 상세보기
				System.out.print(">>>게시물 번호를 입력해주세요: ");
				cmd = Main.scanner.nextLine();
				System.out.println();

				for (Boards board : boardsList) {
					if (Integer.parseInt(cmd) == board.getNo()) {
						detailView(board, members.getMid());
						break;
					}
				}
				continue;

			case "2": //게시물 등록
				insert(members);
				break;

			case "3":
				//이전화면으로 가기(OOO님 반갑습니다! 화면)
				bool = false;
				break;	

			}

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

			//			blist.stream()
			//			.filter(boards -> boards.getBno() == no)
			//			.findFirst()
			//			.ifPresentOrElse(boards -> detailView(boards), 
			//					() -> System.out.println("게시물 번호가 존재하지 않습니다"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void detailView(Boards boards, String mid) {
		//게시물 상세보기
		System.out.println("[[[게시물 상세보기]]]");
		boards.detailPrint();
		System.out.println();

		boolean bool = true;
		while(bool) {
			System.out.println("1. 삭제 ");
			System.out.println("2. 수정");
			System.out.println("3. 이전메뉴로");
			System.out.println();
			System.out.print(">>>원하시는 번호를 입력해주세요: ");
			cmd = Main.scanner.nextLine();
			switch(cmd) {
			case "1":
				//삭제 
				if (mid.equals("admin")) {
					bDB.bDelete(boards);
					bool = false;
					break;
					
				}else {
					System.out.print("-비밀번호를 입력해주세요: : ");
					String bpw = Main.scanner.nextLine();
					System.out.println();

					//관리자가 아닌경우 비밀번호 일치여부 확인 후 삭제
					if (bpw.equals(boards.getBpw())) {
						bDB.bDelete(boards);
						bool = false;
						break;
					} else {
						System.out.println("비밀번호가 일치하지 않습니다. 다시 선택해주세요.");
						continue;
					}
				}

			case "2":
				//수정
				System.out.println("수정함수 호출");
				break;
			case "3":
				//이전으로
				bool = false;
				break;
			default:
				System.out.println("번호를 잘못 입력하셨습니다");
				break;
			}
		}

	}

	public void delete(Boards boards) {
		//		blist.remove(boards);
	}

	public void insert(Members members) {
		//게시물 등록
		System.out.println("[[[게시물 등록]]]");
		System.out.print("-제목 : ");
		String btitle = Main.scanner.nextLine();
		System.out.print("-내용 : ");
		String bcontent = Main.scanner.nextLine();
		System.out.print("-비밀번호를 입력해주세요: : ");
		String bpw = Main.scanner.nextLine();
		System.out.println();



		System.out.println("1. 등록 ");
		System.out.println("2. 취소하고 이전메뉴로");
		System.out.println();
		System.out.print(">>>원하시는 번호를 입력해주세요: ");
		cmd = Main.scanner.nextLine();
		System.out.println();

		switch (cmd) {
		case "1":

			boards = new Boards();
			boards.setBid(members.getMid());
			boards.setBname(members.getMname());
			boards.setBtitle(btitle);
			boards.setBcontent(bcontent);
			boards.setBpw(bpw);

			bDB.bInsert(boards);
			System.out.println("신규 게시물이 등록되었습니다.");
			System.out.println();
			break;

		case"2":
			//이전으로 돌아가기
			break;

		}

	}


}
