package com.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;
import com.board.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/writeProcess")
public class BoardWriteController extends HttpServlet{

	private static String uploadDir;
	private static File parentFile;
	
	public void init() {
		uploadDir = getServletContext().getInitParameter("uploadDir");
		
		String realPath = getServletContext().getRealPath(uploadDir);
		parentFile = new File(realPath);
		
		if(! (parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}
		System.out.println("init - " + parentFile);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String realPath = request.getServletContext().getRealPath(uploadDir);
		
		int maxFileSize = 100 * 1024 * 1024;
		
		String encoding = "UTF-8";
		
		MultipartRequest multi = new MultipartRequest(request, realPath, maxFileSize, encoding, new DefaultFileRenamePolicy());
		
		
		String title = multi.getParameter("title");
		String writer = multi.getParameter("writer");
		String pass = multi.getParameter("pass");
		String content = multi.getParameter("content");
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setPass(pass);
		board.setContent(content);
		
		String fileName = multi.getFilesystemName("file1");
		System.out.println("업로드 된 파일명 : " + fileName);
		System.out.println("원본 파일명 : " + multi.getOriginalFileName("file1"));
		
		board.setFile1(fileName);
		
		if(board.getFile1() == null) {
			System.out.println("파일이 업로드 되지 않았음");
		}
		
		BoardDao dao = new BoardDao();
		dao.insertBoard(board);
		
		response.sendRedirect("boardList");
	}

	
	
}
