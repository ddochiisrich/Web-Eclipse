<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시글 리스트</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<!-- Header -->
		<div class="row">
			<div class="col">여기는 메뉴 부분 다음에 추가함</div>
		</div>
		<!-- Content -->
		<div class="row my-5 text-center">
			<div class="col">
				<h2 class="fs-3 fw-bold">게시글 리스트</h2>
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
				<input type="text" name="keyword" class="form-control" id="keyword">
			</div>
			<div class="col-auto">
				<input type="submit" value="검색" class="btn btn-primary">
			</div>
		</form>
		<div class="row">
			<div class="col text-end">
				<a href="writeForm" class="btn btn-outline-success">글쓰기</a>
			</div>
		</div>
		<div class="row my-3">
			<div class="col">
				<table class="table table-hover">
					<thead>
						<tr class="table-dark">
							<th>NO</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody class="text-secondary">
						<c:if test="${ not empty bList }">
							<c:forEach var="b" items="${bList}">
								<tr>
									<td>${ b.no }</td>
									<td><a href="boardDetail?no=${b.no}"
										class="text-decoration-none link-secondary">${ b.title }</a></td>
									<td>${ b.writer }</td>
									<td>${ b.regDate }</td>
									<td>${ b.readCount }</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty bList }">
							<tr>
								<td colspan="5" class="text-center">게시글이 존재하지 않습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<!-- footer -->
		<div class="row">
			<div class="col">여기는 푸터 부분 다음에 추가함</div>
		</div>

	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>