package mvcproject.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.notice.domain.Notice;
import mvcproject.notice.repository.NoticeDAO;
import mvcproject.web.servlet.Controller;

public class RegistController implements Controller{

	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		// 3단계 일시키기
		noticeDAO.insert(notice);
		
		// 4단계 생략
	}
	
	public boolean isForward() {
		return false; // 포워딩 하지 마시오, 브라우저로 하여금 새로 들어오게 함
	}

	@Override
	public String getViewName() {
		return "/notice/regist/view"; // 목록 보여주기
	}
	
	

}
