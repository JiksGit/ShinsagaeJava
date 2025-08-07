package mvcproject.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvcproject.notice.domain.Notice;
import mvcproject.notice.repository.NoticeDAO;
import mvcproject.web.servlet.Controller;

public class EditController implements Controller{

	NoticeDAO noticeDAO = new NoticeDAO();
	Logger logger = LoggerFactory.getLogger(getClass());
	
	String notice_id = "";
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		notice_id = req.getParameter("notice_id");
		
		String title = req.getParameter("title");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		
		Notice notice = new Notice();
		
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		req.setAttribute("notice", notice);
		
		noticeDAO.update(notice);
	}

	@Override
	public boolean isForward() {
		return true;
		//return false; // redirect가 맞지만, 매핑 파일 자체가 변수가 올 수 없으므로, 포워딩으로 처리함
	}

	@Override
	public String getViewName() {
		return "/notice/edit/view";
		/*
		 * String str = "/notice/detail.do?notice_id=" + notice_id;
		 * logger.debug("str!!!!!!!!!!!!!!!! : " + str); 
		 * return str;
		 */
	}

}
