package miniProj1;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;


@Data
public class Members {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
	private Date mlogin; //로그인 일시
	private Date mlogout; //로그아웃 일시

	
	
	public void mprint() {
		System.out.printf("%-8s|%-7s|%-15s|%-6s|%-5s|%-20s|%-20s%n", mid, mname, mhp, maddr, msex, sdf.format(mlogin), sdf.format(mlogout));

	}
	public void mAllprint() {
		// 전체 회원 목록 조회
		System.out.printf("%-4d|%-8s|%-7s|%-15s|%-6s|%-5s%n", mno, mid, mname, mhp, maddr, msex);
	}



}

