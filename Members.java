package miniProj1;

import java.util.Date;
import lombok.Data;


@Data
public class Members {
	//회원관리 및 탈퇴회원 관리를 위한 VO

	private int no;		//콘솔창에서 보일 번호
	private int mno;       //회원 번호
	private String mid;    //회원 아이디
	private String mpw;    //회원 비밀번호
	private String mname;  //회원 이름
	private String mhp;    //회원 전화번호
	private String maddr;  //회원 주소
	private String msex;   //회원 성별
	private Date mregdate; //회원 탈퇴 일시

	
	
	public void mprint() {
		//	 	System.out.print("-아이디: " + mlist2.get(0).getMid());
		//		System.out.print("-비밀번호: " + mlist2.get(0).getMpw());
		//		System.out.print("-이름: " + mlist2.get(0).getMname());
		//		System.out.print("-전화번호 : " + mlist2.get(0).getMhp());
		//		System.out.print("-주소 : " + mlist2.get(0).getMaddr());
		//		System.out.println("-성별 : " + mlist2.get(0).getMsex());
//		System.out.printf(" %-7s%-6s%-17s%-5s%-5s%n", mid, mname, mhp, maddr, msex);
		System.out.printf("%-3d|%-6s|%-4s|%-15s|%-6s|%-5s%n",no, mid, mname, mhp, maddr, msex);
//		System.out.print("-아이디:" + mid);
//		System.out.print("  -이름:" + mname);
//		System.out.print("  -전화번호:" + mhp);
//		System.out.print("  -주소:" + maddr);
//		System.out.print("  -성별:" + msex);
	}



}

