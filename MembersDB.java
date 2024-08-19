package miniProj1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class MembersDB {

	public Connection getConnection() {
		//DB 연결 함수
		Connection conn = null;
		try {

			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe", 
					"miniproj1", 
					"1004");
		}catch (Exception e) {
			e.printStackTrace();
		}return conn;
	}


	public void mInsert(Members members) {
		//DB 데이터 연결 함수 호출
		Connection conn = getConnection();

		//DB에서 시퀀스를 만들어서 번호가 1씩 증가하도록 할 것.
		try {
			//SQL문 작성
			String sql = "" +
					"INSERT INTO members_info (mno, mid, mpw, mname, mhp, maddr, msex, deleteyn) " +
					"VALUES (SEQ_MEMBERSINFO_MNO.NEXTVAL, ?, ?, ?, ?, ?, ?, 'N')";

			//Statement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, members.getMid());
			pstmt.setString(2, members.getMpw());
			pstmt.setString(3, members.getMname());
			pstmt.setString(4, members.getMhp());
			pstmt.setString(5, members.getMaddr());
			pstmt.setString(6, members.getMsex());

			//SQL문 실행
			pstmt.executeUpdate();

			//PreparedStatement 닫기
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try { 
					//연결 끊기
					conn.close(); 
				} catch (SQLException e) {}
			}
		}

	}


	public void mUpdate(Members members) {
		Connection conn = getConnection();
		try {
			//SQL문 작성
			String sql = new StringBuilder()
					.append("UPDATE members_info SET ")
					.append("mname=?, ")
					.append("mhp=?, ")
					.append("maddr=? ")
					.append("WHERE mid=?")
					.toString();

			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, members.getMname());
			pstmt.setString(2, members.getMhp());
			pstmt.setString(3, members.getMaddr());
			pstmt.setString(4, members.getMid());
			
			//SQL문 실행
			pstmt.executeUpdate();
			
			//PreparedStatement 닫기
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try { 
					//연결 끊기
					conn.close(); 
				} catch (SQLException e) {}
			}
		}

	}

	public void mDelete(Members members) {
		//삭제함수를 호출하면 삭제가 아니라 삭제테이블로 옮겨지게 해야함
		Connection conn = getConnection();
		try {
//			String sql = "DELETE members_info " +
//						 "WHERE mid = ?";
			
			String sql = "UPDATE members_info SET deleteyn='Y' " +
						"WHERE mid = ?";
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, members.getMid());
			
			//SQL문 실행
			pstmt.executeUpdate();
			
			/*sql = "" +
					"INSERT INTO members_delete (mno, mid, mpw, mname, mhp, maddr, msex, mregdate) " +
					"VALUES (SEQ_MEMBERS_DELETE_MNO.NEXTVAL, ?, ?, ?, ?, ?, ?, sysdate)";

			//Statement 얻기 및 값 지정
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, members.getMid());
			pstmt.setString(2, members.getMpw());
			pstmt.setString(3, members.getMname());
			pstmt.setString(4, members.getMhp());
			pstmt.setString(5, members.getMaddr());
			pstmt.setString(6, members.getMsex());

			//SQL문 실행
			pstmt.executeUpdate();	*/
			
			//PreparedStatement 닫기
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try { 
					//연결 끊기
					conn.close(); 
				} catch (SQLException e) {}
			}
		}
	}


	public List<Members> mSelectMembers(Members members) {
		//로그인, 내정보 보기, 모든 회원정보 불러오기
		Connection conn = getConnection();
		List<Members> mlist = new ArrayList<>(); 
		try {
			String sql =
					"SELECT mid, mpw, mname, mhp, maddr, msex " +
					"FROM members_info "; 

			PreparedStatement pstmt = null;

			//ID와 PW가 null이 아니면 로그인
			//null이 아니면 내 정보 데이터를 찾음
			if(members.getMid() != null && members.getMpw() != null) {
				sql += "WHERE mid = ? AND mpw = ? AND deleteyn='N'";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, members.getMid());
				pstmt.setString(2, members.getMpw());

			//회원가입시 아이디 중복체크
			}else if(members.getMid() != null){
				sql += "WHERE mid = ? AND deleteyn='N'";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, members.getMid());
				
			// 개인유저와 관리자를 구분하기 위해 ID와 PW값이 있냐 없냐로 구분
			}else {
				sql += "WHERE deleteyn='N'";
				pstmt = conn.prepareStatement(sql);
			}

			//맞는 조건 검색
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				members = new Members();
				members.setMid(rs.getString("mid"));
				members.setMpw(rs.getString("mpw"));
				members.setMname(rs.getString("mname"));
				members.setMhp(rs.getString("mhp"));
				members.setMaddr(rs.getString("maddr"));
				members.setMsex(rs.getString("msex"));
				
				mlist.add(members);
				
			}
			rs.close();
			
			//PreparedStatement 닫기
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try { 
					//연결 끊기
					conn.close(); 
				} catch (SQLException e) {}
			}
		} return mlist;
	}
























}
