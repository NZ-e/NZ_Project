package miniProj1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BoardsDB {

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


	public void bInsert(Boards boards) {
		//게시물 등록
		Connection conn = getConnection();

		try {
			//SQL문 작성
			String sql = "" +
					"INSERT INTO boards_info (bno, bid, bpw, bname, btitle, bcontent, bdate) " +
					"VALUES (SEQ_BOARDSINFO_BNO.NEXTVAL, ?, ?, ?, ?, ?, sysdate)";

			//			List<Members> loginList = new ArrayList<>();
			//Statement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//			pstmt.setString(1, loginList.get(0).getMname());
			pstmt.setString(1, boards.getBid());
			pstmt.setString(2, boards.getBpw());
			pstmt.setString(3, boards.getBname());
			pstmt.setString(4, boards.getBtitle());
			pstmt.setString(5, boards.getBcontent());

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


	public void bUpdate(Boards boards) {
		//게시판 수정
		Connection conn = getConnection();
		try {
			//SQL문 작성
			String sql = new StringBuilder()
					.append("UPDATE boards_info SET ")
					.append("btitle=?, ")
					.append("bcontent=?")
					.append("WHERE bpw=?")
					.toString();

			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boards.getBtitle());
			pstmt.setString(2, boards.getBcontent());
			pstmt.setString(3, boards.getBpw());


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



	public void bDelete(Boards boards) {
		Connection conn = getConnection();
		try {

			String sql = "DELETE boards_info " +
					"WHERE bno = ?";

			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boards.getBno());

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


	public List<Boards> bSelectBoards(Boards boards) {
		//게시물 목록 가져오기
		Connection conn = getConnection();

		List<Boards> boardsList = new ArrayList<>(); 
		try {
			String sql ="" +
					"SELECT bno, bid, bpw , bname, btitle, bcontent, nvl(bhitcnt, 0) bhitcnt, bdate " +
					"FROM boards_info " +
					"ORDER BY bno DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//맞는 조건 검색
			ResultSet rs = pstmt.executeQuery();
			int no = 0;
			while(rs.next()) {
				boards = new Boards();
				boards.setNo(++no);
				boards.setBno(rs.getInt("bno"));
				boards.setBid(rs.getString("bid"));
				boards.setBpw(rs.getString("bpw"));
				boards.setBname(rs.getString("bname"));
				boards.setBtitle(rs.getString("btitle"));
				boards.setBcontent(rs.getString("bcontent"));
				boards.setBhitcnt(rs.getInt("bhitcnt"));
				boards.setBdate(rs.getDate("bdate"));

				boardsList.add(boards);

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
		} return boardsList;
	}
	
	

	public void page(Boards boards) {
		//페이징
		Connection conn = getConnection();
	}

	public boolean bhitcnt(Boards boards) {
		//조회수 올리는 프로시저 함수
		Connection conn = getConnection();

		try {
			// 프로시저 호출 SQL
			String sql = "{call pro_bhitcnt(?, ?)}";  // 프로시저 이름 호출

			// CallableStatement 생성
			CallableStatement cstmt = conn.prepareCall(sql);

			// CallableStatement 얻기 및 값 지정
			cstmt.setInt(1, boards.getBno());
			cstmt.registerOutParameter(2, Types.INTEGER);

			//프로시저 실행
			cstmt.execute();
			
			//2번째 파라미터 지정 함수가 정상적으로 실행되면 1을 반환. 정상적으로 실행이 됐는지 확인 
			if (cstmt.getInt(2) == 1) {
				return true;
			}

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
		return false;
	}

}
