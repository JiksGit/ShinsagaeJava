package com.sinse.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sinse.mvcapp.color.model.BloodManager;
import com.sinse.mvcapp.color.model.ColorManager;

/*
 * MVC 패턴에 의해 디자인과 로직을 분리시키려면, 중간에 중재자가 나서야 하므로, 코드에서 분리
 * 왜 서블릿으로 정의하고 있나? 
 * 1) JSP는 View로 사용할 것이므로...
 * 2) 웹기반의 컨트롤러는 클라이언트의 요청을 받을 수 있어야 하므로,
 * */
public class BloodController implements Controller{
	
	BloodManager bloodManager = new BloodManager();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청을 받는다.
		String blood = request.getParameter("blood");
		
		// 2) 분석 (생략 -> 왜? 어차피 이 컨트롤러는 혈액형에 대한 처리이므로)
		// 3) 일시킨다
		String result = bloodManager.getAdvice(blood);
		// 4) 뷰로 가져갈 것이 있다면 저장
		HttpSession session = request.getSession();
		session.setAttribute("msg", result);
	}
	
	public String getViewPage() {
		return "/blood/result/view";
	}
}












