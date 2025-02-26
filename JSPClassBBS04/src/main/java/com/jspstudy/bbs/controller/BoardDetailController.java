package com.jspstudy.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspstudy.bbs.dao.BoardDao;
import com.jspstudy.bbs.vo.Board;

@WebServlet("/boardDetail")
public class BoardDetailController extends HttpServlet {

	// get 방식의 요청을 처리하는 메소드
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		/* 테이블에서 하나의 게시 글 정보를 읽어오기 위해서 boardList에서 
		 * 게시 글 상세보기 요청을 하면서 테이블에서 게시 글 하나를 유일하게 구분할 수 있는
		 * 게시 글 번호를 요청 파라미터로 보냈기 때문에 이 게시 글 번호를 요청 파라미터로 
		 * 부터 읽어 BoardDao를 통해서 no에 해당하는 게시 글 정보를 읽을 수 있다.
		 *
		 * 아래에서 no라는 요청 파라미터가 없다면 NumberFormatException 발생
		 **/
		String no = request.getParameter("no");	
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		if(no == null || no.equals("") || pageNum == null || pageNum.equals("")) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근이여유!!!!!');");
			out.println("history.back();");
			out.println("</script>");
			
			return;
		}
		
		
		// 현재 요청이 검색에서 또는 ㅣㅇㄹ반 리스트에서 넘어왔는지 판단
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		
		// BoardDao 객체 구하고 게시 글 번호(no)에 해당하는 게시 글을 읽어온다.
		BoardDao dao = new BoardDao();
		Board board = dao.getBoard(Integer.valueOf(no));

		// 요청을 처리한 결과 데이터를 HttpServletRequest의 속성에 저장한다.
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		if(searchOption) {
			request.setAttribute("keyword", keyword);
			request.setAttribute("type", type);
		}
		
		/* view 페이지로 제어를 이동해 요청에 대한 결과를 출력하기 위해
		 * HttpServletRequest 객체로 부터 RequestDispatcher 객체를 구하고
		 * /WEB-INF/board/boardDetail.jsp 페이지로 포워딩 한다. 
		 **/
		RequestDispatcher rd = 
				request.getRequestDispatcher("/WEB-INF/board/boardDetail.jsp");
		rd.forward(request, response);
	}
}
