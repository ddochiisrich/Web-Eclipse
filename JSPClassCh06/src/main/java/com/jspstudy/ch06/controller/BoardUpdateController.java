package com.jspstudy.ch06.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.ch06.dao.BoardDao;
import com.jspstudy.ch06.vo.Board;

@WebServlet("/updateProcess")
public class BoardUpdateController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 문자셋 처리
		request.setCharacterEncoding("UTF-8");
		
		// 입력처리
		String sNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		
		// 수정할 수 있는 자격 검증
		BoardDao dao = new BoardDao();
		boolean isPassCheck = dao.isPassCheck(Integer.parseInt(sNo), pass);
		
		// 비밀번호가 틀린경우
		if(!isPassCheck) { // 직접 자바스크립트로 응답 - 비밀번호가 틀리다고 알림창을 띄우고 이전으로 돌려보낸다
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('wrong password!!')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return;
		}
		
		// 비밀번호가 맞으면
		String title = request.getParameter("title");
		System.out.println(title);
		String writer = request.getParameter("writer");
		System.out.println(writer);
		String content = request.getParameter("content");
		System.out.println(content);
		Board b = new Board();
		b.setNo(Integer.parseInt(sNo));
		b.setTitle(title);
		b.setWriter(writer);
		b.setContent(content);
		b.setPass(pass);
		
		// DAO를 이용해서 게시글 정보를 DB에 저장
		dao.updateBoard(b);
		
		// 게시글 리스트로 Redirect 시킨다 - 자원이 이동되었다고 응답을 하면서 주소를 알려준다.
		response.sendRedirect("boardList");
		
	}

	
	
}
