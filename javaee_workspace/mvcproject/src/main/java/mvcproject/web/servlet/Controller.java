package mvcproject.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

	// DispatcherServlet 대신 요청을 처리하므로, request, response가 필요함
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	
	// 포워딩할 지 아니면 리다이렉트 할지
	public boolean isForward();
	
	// 5단계 업무를 수행할 DispatcherServlet에게 뷰의 페이지를 검색할 수 있는 검색어 반환
	public String getViewName();
}
