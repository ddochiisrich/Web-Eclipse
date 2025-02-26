package com.jspstudy.ch06.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jspstudy.ch06.vo.Board;

public class BoardDao01 {
	
	private static final String USER = "scott";
	private static final String PASS = "tiger";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	//JDBC DB 프로그램에 필요한 객체
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public BoardDao01() {
		// 1. 접속 드라이버 로딩
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 하나하나의 기능 - 메서드(함수)
	// DB 테이블에서 게시 글 리스트를 읽어와서 반환하는 메서드
	public List<Board> boardList() {
		String sqlBoardList = "SELECT * FROM jspbbs ORDER BY no DESC";
		List<Board> bList = null;
		
		try {
			
	// 2. DB에 연결
			conn = DriverManager.getConnection(URL,USER,PASS);
					
	// 3. DB에 SQL 쿼리를 발행 해주는 객체
			pstmt = conn.prepareStatement(sqlBoardList);
					
	// 4. DB에 쿼리를 발행하고 테이블에서 조회한 결과 집합을 받는다.
			rs = pstmt.executeQuery();
					
			bList = new ArrayList<>();
					
			while(rs.next()){
				// 한 행씩 데이터를 추출해서 Board 객체 담고 List 에 담는다.
				Board b = new Board();
				b.setNo(rs.getInt(1));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("Writer"));
				b.setRegDate(rs.getTimestamp("reg_date"));
				b.setReadCount(rs.getInt("read_count"));
				b.setFile1(rs.getString("file1"));
						
				bList.add(b);
			}

		} catch(SQLException e) {
			e.printStackTrace();
					
		}finally {
			try {
				// DB 작업에 사용한 자원을 해제 - 앞에서 가져온 역순으로 닫는다.
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return bList;
	} // end boardList()
} // end class
	

