package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;
import com.board.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/updateProcess")
public class BoardUpdateController extends HttpServlet {

	private static String uploadDir;
	private static File parentFile;
	
	@Override
	public void init() {
		uploadDir = getServletContext().getInitParameter("uploadDir");
		String realPath = getServletContext().getRealPath(uploadDir);
		parentFile = new File(realPath);
		
		if(!(parentFile.exists() && parentFile.isDirectory()) ) {
			parentFile.mkdir();
		}
		System.out.println("init = " + parentFile);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String contentType = request.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);
		
		String pass = null;
		String title = null;
		String writer = null;
		String content = null;
		String sNo = null;
		String pageNum = null;
		String fileName = null;
		String type  = null;
		String keyword = null;
		int no = 0;
		
		if(contentType.contains("multipart/form-data")) {
			String realPath = request.getServletContext().getRealPath(uploadDir);
			int maxFileSize = 100 * 1024 * 1024;
			String encoding = "UTF-8";
			
			MultipartRequest multi = new MultipartRequest(request, realPath, maxFileSize, encoding, new DefaultFileRenamePolicy());
			
			sNo = multi.getParameter("no");
			pass = multi.getParameter("pass");
			title = multi.getParameter("title");
			writer = multi.getParameter("writer");
			content = multi.getParameter("content");
			pageNum = multi.getParameter("pageNum");
			type = multi.getParameter("type");
			keyword = multi.getParameter("keyword");
			
			fileName = multi.getFilesystemName("file1");
			System.out.println("업로드 된 파일명 : " + fileName);
			System.out.println("원본 파일명 : " + multi.getOriginalFileName("file1"));
			
			if(fileName == null) {
				System.out.println("파일이 업로드되지않았음.");
			}
		} else {
		
			request.setCharacterEncoding("UTF-8");
			
			sNo = request.getParameter("no");
			pass = request.getParameter("pass");
			title = request.getParameter("title");
			writer = request.getParameter("writer");
			content = request.getParameter("content");
			pageNum = request.getParameter("pageNum");	
			type = request.getParameter("type");
			keyword = request.getParameter("keyword");
		}
		
		if(sNo == null || sNo.equals("") || pageNum == null || pageNum == "") {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("history.back();");
			out.println("</script>");
			return;
		}
		
		no = Integer.parseInt(sNo);
		
		BoardDao dao = new BoardDao();
		
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		String url = "boardList?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "UTF-8");
			url += "&type=" +type+ "&keyword=" +keyword;
		}
		System.out.println("keyword : " + keyword);
		System.out.println("url : " + url);
		System.out.println(type);
		System.out.println(keyword);
		
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		if(!isPassCheck) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append(" alert('비밀번호가 맞지 않습니다.');");
			sb.append(" history.back();");
			sb.append("</script>");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
				out.println(sb.toString());
			return;
			
		}
		
		Board board = new Board();
		
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setNo(no);
		board.setContent(content);
		board.setFile1(fileName);
		
		dao.updateBoard(board);
		
		
		response.sendRedirect(url);
		
	}

	
	
}
