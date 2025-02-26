package com.jspstudy.ch04.exam.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/studentInform")
public class FormData extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String phoneNum1 = request.getParameter("phone1");
		String phoneNum2 = request.getParameter("phone2");
		String phoneNum3 = request.getParameter("phone3");
		String si = request.getParameter("si");
		String sm = request.getParameter("sm");
		String solution = request.getParameter("solution");
		String subject = request.getParameter("subject");
		
		request.setAttribute("name", name);
		request.setAttribute("gender", gender);
		request.setAttribute("phone1", phoneNum1);
		request.setAttribute("phone2", phoneNum2);
		request.setAttribute("phone3", phoneNum3);
		request.setAttribute("si", si);
		request.setAttribute("sm", sm);
		request.setAttribute("solution", solution);
		request.setAttribute("subject", subject);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/formDataView02.jsp");
		rd.forward(request, response);
		
	}
	
}
