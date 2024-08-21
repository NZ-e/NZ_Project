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
			System.out.println("번호     작성자          제목        조회수        작성일시   ");	
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
			
//				for (Boards board : boardsList) {
//					if (Integer.parseInt(cmd) == board.getNo()) {
//						bDB.bhitcnt(boards);
//						break;
//					}
//				}
				Boards board = new Boards();
				board.setBno(Integer.parseInt(cmd));
				if (bDB.bhitcnt(board)) {
					bDB.bSelectBoards(board);
					
					for (Boards board2 : boardsList) {
						if (Integer.parseInt(cmd) == board2.getBno()) {
							detailView(board2, members.getMid());
							break;
						}
					}
				} else {
					System.out.println("게시물 번호가 존재하지 않습니다");
				}
				//------
				continue;

			case "2": //게시물 등록
				insert(members);
				break;

			case "3":
				//이전화면으로 가기(OOO님 반갑습니다! 화면)
				bool = false;
				break;	

			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
				System.out.println();
				break;
			}

		}
	}


	

	public void detailView(Boards boards, String mid) {
		//게시물 상세보기
		System.out.println("[[[게시물 상세보기]]]");
		boards.detailPrint();
		System.out.println();

		boolean bool = true;
		while(bool) {
				//게시물 등록한 아이다와 로그인아이디가 같으면
				if(mid.equals(boards.getBid())) {
					System.out.println("1. 삭제 ");
					System.out.println("2. 수정");
					System.out.println("3. 이전메뉴로");
					
					System.out.println();
					System.out.print(">>>원하시는 번호를 입력해주세요: ");
					cmd = Main.scanner.nextLine();
					switch(cmd) {
					case "1":
						//삭제 
						bool = delete(boards, mid);
						break;
					case "2":
						//수정
						modify(boards, mid);
						bool = false;
						break;
					case "3":
						//게시물 목록 화면으로 돌아가기
						bool = false;
						break;
					default:
						System.out.println("번호를 잘못 입력하셨습니다");
						break;
					}
					//로그인 아이디가 관리자면
				} else if(mid.equals("admin")) {
					System.out.println("1. 삭제 ");
					System.out.println("2. 이전메뉴로");
					
					System.out.println();
					System.out.print(">>>원하시는 번호를 입력해주세요: ");
					cmd = Main.scanner.nextLine();
					switch(cmd) {
					case "1":
						//삭제 
						bool = delete(boards, mid);
						break;
					case "2":
						//게시물 목록 화면으로 돌아가기
						bool = false;
						break;
					default:
						System.out.println("번호를 잘못 입력하셨습니다");
						break;
					}
				}
				//내가 쓴 게시물이 아니고 관리자도 아니면
				else {
					System.out.println("1. 이전메뉴로");
					System.out.println();
					System.out.print(">>>원하시는 번호를 입력해주세요: ");
					cmd = Main.scanner.nextLine();
					
					switch (cmd) {
					case "1":
						//게시물 목록 화면으로 돌아가기
						bool = false;
						break;
		
					default:
						System.out.println("번호를 잘못 입력하셨습니다");
						System.out.println();
						break;
					}
					
				}
			}
		
//		} else if(boards.getBid() != null) {
//			System.out.println("1. 이전메뉴로");
//			System.out.println();
//			System.out.print(">>>원하시는 번호를 입력해주세요: ");
//			cmd = Main.scanner.nextLine();
//			
//			switch (cmd) {
//			case "1":
//				//게시물 목록 화면으로 돌아가기
//				break;
//
//			default:
//				System.out.println("번호를 잘못 입력하셨습니다");
//				System.out.println();
//				break;
//			}
//			
//		} else {
//			System.out.println("번호를 잘못 입력하셨습니다??");
//			System.out.println();
//		}
		
	}

	public boolean modify(Boards boards, String mid) {
		//게시판 수정
		System.out.print(">>>비밀번호를 입력해주세요: ");
		cmd = Main.scanner.nextLine();
		System.out.println();

		if (cmd.equals(boards.getBpw())) {
			System.out.print("-제목 : ");
			String btitle = Main.scanner.nextLine();
			System.out.print("-내용 : ");
			String bcontent = Main.scanner.nextLine();
			System.out.println();

			boards.setBtitle(btitle);
			boards.setBcontent(bcontent);
			boards.setBpw(cmd);
			bDB.bUpdate(boards);
			bDB.bSelectBoards(boards);
			System.out.println("수정이 완료되었습니다.");
			System.out.println();
			

			return false;
		} else {
			System.out.println("비밀번호가 일치하지 않습니다. 다시 선택해주세요.");
			System.out.println();
			return false;
		}
	}


	public boolean delete(Boards boards, String mid) {
		//관리자인지 일바회원인지 구분해서 삭제
		
		//admin(관리자)이면 비밀번호를 입력하지 않고 삭제
		if (mid.equals("admin")) {
			bDB.bDelete(boards);
			System.out.println("게시물이 삭제 되었습니다.");
			System.out.println();
			return false;

			//일반 회원이라면 게시물 등록시 입력했던 비밀번호 입력 후 삭제	
		}else {
			System.out.print("-비밀번호를 입력해주세요: : ");
			String bpw = Main.scanner.nextLine();
			System.out.println();

			if (bpw.equals(boards.getBpw())) {
				bDB.bDelete(boards);
				System.out.println("게시물이 삭제 되었습니다.");
				System.out.println();
				return false;

			} else {
				System.out.println("비밀번호가 일치하지 않습니다. 다시 선택해주세요.");
				System.out.println();
				return true;
			}
		}

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
			//게시물 등록 시 로그인한 아이디와 이름을 넣어줌
			boards = new Boards();
			boards.setBid(members.getMid());
			boards.setBpw(bpw);
			boards.setBname(members.getMname());
			boards.setBtitle(btitle);
			boards.setBcontent(bcontent);

			bDB.bInsert(boards);
			System.out.println("신규 게시물이 등록되었습니다.");
			System.out.println();
			break;

		case"2":
			//게시물 목록 화면으로 돌아가기
			break;

		}

	}


}
