<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>게시 글 수정 폼</title>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet" >
    <script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/formcheck.js"></script> 
  </head>
  <body>
  	<div class="container">
  		<%@ include file="../pages/header.jsp" %>
		<!-- content -->
		<div class="row my-5" id="global-content">
			<div class="col">
				<div class="row text-center">
					<div class="col">
						<h2 class="fs-3 fw-bold">게시 글 수정하기</h2>
					</div>
				</div>  
				<form name="updateForm" action="updateProcess" id="updateForm" 
					class="row g-3 border-primary" method="post"
					${ not empty board.file1 ?  "" : "enctype='multipart/form-data'"}>
					<%--
						no는 DB에서 게시 글을 수정하기 위해 필요하고 pageNum은 게시 글이 
						수정된 후에 이전에 사용자가 머물렀던 게시 글 리스트의 동일한 페이지로
						보내기 위해 필요한 정보이다.  
					--%>
					<input type="hidden" name="no" value="${board.no}">
					<input type="hidden" name="pageNum" value="${ pageNum }" />
					
					<%-- 
						검색 요청일 경우 다시 keyword에 해당하는 검색 리스트로
						돌려보내기 위해서 아래의 파라미터가 필요하다.
					 --%>
					<c:if test="${ searchOption }">
						<input type="hidden" name="type" value="${ type }" />
						<input type="hidden" name="keyword" value="${ keyword }" />
					</c:if>
				
				  	<div class="col-4 offset-md-2">
					    <label for="writer" class="form-label">글쓴이</label>
					    <input type="text" class="form-control" name="writer"  id="writer" 
					    	placeholder="작성자를 입력해 주세요" value="${ board.writer }">
			  		</div>
			  		<div class="col-4 ">
					    <label for="pass" class="form-label">비밀번호</label>
					    <input type="password" class="form-control" name="pass"  
					    	id="pass">
			  		</div>
			  		<div class="col-8 offset-md-2">
					    <label for="title" class="form-label">제 목</label>
					    <input type="text" class="form-control" name="title"  
					    	id="title" value="${board.title}">
			  		</div>
					<div class="col-8 offset-md-2">
					    <label for="content" class="form-label">내 용</label>
					    <textarea class="form-control" name="content" id="content" 
					    	rows="10">${ board.content }</textarea>
			  		</div>
			  		<%-- 첨부 파일이 비어 있으면 파일 선택 상자를 표시함 --%>
			  		<c:if test="${empty board.file1}">	
			  		<div class="col-8 offset-md-2">
					    <label for="file1" class="form-label">파 일</label>
					    <input type="file" class="form-control" name="file1"  id="file1">
			  		</div>
			  		</c:if>
			  		<div class="col-8 offset-md-2 text-center mt-5">
					   <input type="submit" value="수정하기" class="btn btn-primary"/>
					   	<%-- 
							일반 게시 글 리스트에서 온 요청이면 일반 게시 글 리스트로 돌려보낸다. 
						--%>
						<c:if test="${ not searchOption }">	
						&nbsp;&nbsp;<input class="btn btn-primary" type="button" value="목록보기" 
								onclick="location.href='boardList?pageNum=${pageNum}'"/>
						</c:if>
						<%-- 
							검색 리스트에서 온 요청이면 검색 리스트의 동일한 페이지로 돌려보낸다. 
						--%>
						<c:if test="${ searchOption }">	
						&nbsp;&nbsp;<input class="btn btn-primary" type="button" value="목록보기" 
								onclick="location.href='boardList?pageNum=${pageNum}&type=${ type }&keyword=${ keyword }'"/>
						</c:if>	
			  		</div>	
				</form>
			</div>
		</div>    
  		<%@ include file="../pages/footer.jsp" %>
	</div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
  </body>
</html>    
    