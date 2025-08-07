package com.sinse.web618;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudyServlet extends HttpServlet{

	public void init() throws ServletException {
		System.out.println("init() 호출");
	}
	
	/* 서블릿의 service() 메서드에서, 먼저 요청을 받아, 클라이언트의 HTTP 요청 방식이 Get이면
	   doGet() 메서드가, Post이면 doPost(), Put doPut(), Delete 이면 doDelete() 호출
	   따라서 개발자는 실제적인 로직을 doXXX형을 오버라이드해서 처리
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int x = 3;
		x = 7;
		
		// 요청을 마무리 짓는 단계에서 고객이 보여야할 응답 정보를 구성하자
		PrintWriter out = response.getWriter();
		
		out.print("<h1>결과입니다" + x + "</h1>");
		
	}
	
	public void destroy() {
		
	}
}
