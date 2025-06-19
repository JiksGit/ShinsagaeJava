package com.sinse.web618;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet{
	// 웹 브라우저가 Get 방식으로 요청을 시도할 때 동작하는 메서드인 doGet() 재정의
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("my site's build is successful !");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}