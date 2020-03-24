package com.reply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BbsDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언한다.
	// 2. 정적 멤버로 선언한다. -static으로 선언

	private static BbsDAO instance = new BbsDAO();// static:메서드 영역에 만들어진다아

	// 3. 기본생성자는 외부에서 접근이 되지 않도록 해야한다. -private으로 생성자 생성
	// 외부에서 new를 사용하지 못하게 하는 접근 기법.
	private BbsDAO() {
	}

	// 4. 생성자 대신에 싱글톤 객체를 return 해주는 getInstance() 메서드를 만들어 주자.
	public static BbsDAO getInstance() {// static에 있는 instance를 받아줘야하기때문에 static
		if (instance == null) {// 객체생성했기때문에 null일리가 없지만 혹시나모르니까아...
			instance = new BbsDAO();
		}
		return instance; // 참조변수 리턴!
	}

	public Connection openConn() {

		try {
			// 1. JNDI 서버 객체 생성
			InitialContext ic = new InitialContext();

			// 2.lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.

			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");

			// 3.DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}// openConn() end

	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//closeConn() end
	
	//jsp_bbs 테이블의 전체 게시물을 조회하는 메서드
	public List<BbsDTO> getBbsList(){
		List<BbsDTO> list =new ArrayList<BbsDTO>();
		
		try {
			openConn();
			sql="select * from jsp_bbs order by board_group desc, board_step asc";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BbsDTO dto = new BbsDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}//getBbsList() end;
	
	
}
