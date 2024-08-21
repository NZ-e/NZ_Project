package miniProj1;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Boards{
//게시물 등록을 위한 VO
	
	//날짜와 시간을 특정 형식의 문자열로 변환하거나, 반대로 문자열을 날짜와 시간으로 변환
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
	

	private int no;		//콘솔창에서 보일 번호
	private int bno;          //게시물 번호
	private String bid;     //작성자(회원)아이디
	private String bpw;     //작성자(회원)비밀번호
	private String bname;     //작성자(회원)이름
	private String btitle;    //제목
	private String bcontent;  //내용
	private int bhitcnt;      //조회수
	private Date bdate;       //작성일



	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBid() {
		return bid;
	}
	
	public void setBid(String bid) {
		this.bid = bid;
	}
	
	public String getBpw() {
		return bpw;
	}
	
	public void setBpw(String bpw) {
		this.bpw = bpw;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBtitle() {
		return btitle;
	}
	
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public int getBhitcnt() {
		return bhitcnt;
	}
	public void setBhitcnt(int bhitcnt) {
		this.bhitcnt = bhitcnt;
	}


	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}


//	System.out.println("  이름       제목              조회수        작성일");
	public void print() {
		//게시물 목록 출력
		//조회수 포함된 printf
		String toDay = sdf1.format(System.currentTimeMillis());
		String dbDay = sdf1.format(bdate);
		String printDay = toDay.equals(dbDay) ? sdf2.format(bdate) : sdf1.format(bdate);
		
		System.out.printf("%-5d|%-8s|%-15s|%-5d|%s\n", bno, bname, btitle, bhitcnt, printDay);
	
	}

	public void detailPrint() {
		//게시물 상세보기 출력
		System.out.println("게시물 번호 : " + bno);
		System.out.println("게시물 제목 : " + btitle);
		System.out.println("게시물 내용 : " + bcontent);
		System.out.println("조회 수 : " + bhitcnt);
		System.out.println("작성일시 : " + sdf.format(bdate));
		System.out.println();
	}
  

	
}
