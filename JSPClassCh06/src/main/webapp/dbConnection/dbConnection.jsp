<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.jspstudy.ch06.vo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String user = "scott";
	String pass = "tiger";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	// 1. 접속 드라이버 로딩
	Class.forName(driver);
	
	// 2. DB에 연결
	Connection conn = DriverManager.getConnection(url, user, pass);
	
	// 3. DB에 sql쿼리를 발행 해주는 객체
	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM jspbbs ORDER BY no DESC");
	
	// 4. DB에 쿼리를 발행하고 테이블에서 조회한 결과 집합을 받는다.
	ResultSet rs = pstmt.executeQuery();
	List<Board> bList = new ArrayList<>();
	
	while(rs.next()){
		// 한 행씩 데이터를 추출해서 Board 객체 담고 List에 담는다.
		Board b = new Board();
		b.setNo(rs.getInt(1));
		b.setTitle(rs.getString("title"));
		b.setWriter(rs.getString("Writer"));
		b.setRegDate(rs.getTimestamp("reg_date"));
		b.setReadCount(rs.getInt("read_count"));
		b.setFile1(rs.getString("file1"));
		
		bList.add(b);
	}
	
	// DB 작업에 사용한 자원을 해제 - 앞에서 가져온 역순으로 닫는다.
	if(rs != null) rs.close();
	if(pstmt != null) pstmt.close();
	if(conn != null) conn.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>🐭 게시글 리스트 🐭</H1>
	<table>
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach var="board" items="<%= bList %>">
		<tr>
			<td>${ board.no }</td>
			<td>${ board.title }</td>
			<td>${ board.writer }</td>
			<td>${ board.regDate }</td>
			<td>${ board.readCount }</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>