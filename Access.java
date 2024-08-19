package miniProj1;

import java.util.Date;

public class Access {
//로그인/ 로그아웃 정보를 입력할 VO

    private int ano;        //접속 번호
	private String aid;     //회원 아이디
	private String aname;   //회원 이름
	private Date alogin;  	//로그인 일시
	private Date alogout;   //로그아웃 일시
	
	
	private Access(int ano, String aid, String aname, Date alogin, Date alogout) {
		this.ano = ano;
		this.aid = aid;
		this.aname = aname;
		this.alogin = alogin;
		this.alogout = alogout;
	}


	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}


	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}


	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}


	public Date getAlogin() {
		return alogin;
	}
	public void setAlogin(Date alogin) {
		this.alogin = alogin;
	}


	public Date getAlogout() {
		return alogout;
	}
	public void setAlogout(Date alogout) {
		this.alogout = alogout;
	}
	
	
}
