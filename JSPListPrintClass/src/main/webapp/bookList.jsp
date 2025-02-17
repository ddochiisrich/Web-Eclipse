<%-- 
	스크립틀릿과 표현식을 이용해 도서 리스트 출력하기
	table 태그를 사용할 것 
--%>

<%@ page import = "com.jspstudy.ch03.vo.Book" %>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    
<%
    List<Book> bookData = (List<Book>) request.getAttribute("bookData");
%>   

<style>

body{
	width:550px;
	margin: 0 auto;
	padding : 10px;
	text-align: center; 
}

img{
	margin-right:15px;	
}

a{
	margin:20px;
}

</style> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>스크립틀릿과 표현식을 이용해 도서 리스트 출력</title>
</head>
<body align="center">
<div>
<h1 align="center">도서 리스트</h1>

<hr color="skyblue" width="550px">
	
<%
	for(Book bookGet : bookData){
%> 
	
	<table width="550px" height="150px" >
		<tr>
			<th><img src="<%= bookGet.getImg() %>"></th>
			<td><h4>[도서] <%= bookGet.getTitle() %></h4>
			<p><%= bookGet.getAuthor() %> 저</p>
			<p><%= bookGet.getPrice() %></p>
			<p>도착 예상일 : 지금 주문하면 <%= bookGet.getDelivery() %></p></td>
		</tr>
	</table>
	
	<hr width="550px">
	
	
<%
	}
%>	
</div>

<a href="bookData.jsp">도서 리스트</a> <a href="lottoNum.jsp">로또 당첨 번호 리스트</a>
</body>
</html>