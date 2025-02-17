<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jspstudy.ch06.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="ko" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Board List!!</title>

<style>

</style>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<!-- header start -->
		<%@ include file="../pages/header.jsp"%>
		<!-- header end -->

		<!-- content start -->
		<div class="row my-5" id="global-content">
			<div class="col">
				<div class="row">
					<div class="col">
						<H1 class="text-center fw-bold fs-3">🧛🏻 Board List 🧛🏻</H1>
					</div>
				</div>
				<form name="searchForm" id="searchForm" action="#"
					class="row justify-content-center my-3">
					<div class="col-auto">
						<select name="type" class="form-select">
							<option value="title">제목</option>
							<option value="writer">작성자</option>
							<option value="content">내용</option>
						</select>
					</div>
					<div class="col-4">
						<input type="text" name="keyward" id="keyward"
							class="form-control">
					</div>
					<div class="col-auto">
						<input type="submit" value="SEARCH" class="btn btn-danger">
					</div>
				</form>
				<div class="row">
					<div class="col text-end">
						<a href="writeForm" class="btn btn-outline-danger">POST</a>
					</div>
				</div>
				<div class="row my-3">
					<div class="col">
						<table class="table table-hover table-striped">
							<thead>
								<tr class="table-danger">
									<th>NO</th>
									<th>TITLE</th>
									<th>WRITER</th>
									<th>REG DATE</th>
									<th>VIEWS</th>
								</tr>
							</thead>
							<!-- 게시글이 있는 경우 -->
							<tbody>
								<c:if test="${ not empty bList }">
									<c:forEach var="board" items="${ bList }">
										<tr>
											<td>${ board.no }</td>
											<td><a href="boardDetail?no=${ board.no }" class="link-underline link-underline-opacity-0 link-light">${ board.title }</a></td>
											<td>${ board.writer }</td>
											<td><fmt:formatDate value="${ board.regDate }"
											pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${ board.readCount }</td>
										</tr>
									</c:forEach>
								</c:if>
								<!-- 게시글이 없는 경우 -->
								<c:if test="${ empty bList }">
									<tr>
										<td colspan="5">게시글이 존재하지 않습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- footer start -->
		<%@ include file="../pages/footer.jsp"%>
		<!-- footer end -->
		<script src="bootstrap/bootstrap.bundle.min.js"></script>
	</div>
</body>
</html>