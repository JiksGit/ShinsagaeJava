<%@ page contentType="text/html; charset=UTF-8"%>
<%
	/*웹요청 처리 흐름에서, 데이터를 저장할 수 있는 객체의 종류에는 다음과 같은 것들이 있다
	1) HttpServletRequest : 클라이언트의 요청 처리 끝날때까지
	2) HttpSession : 세션의 유지 시간까지
	3) ServletContext : 애플리케이션이 실행되는 동안 유지 (톰캣켤때, 톰캣끌 떄)
	*/
	request.setAttribute("addr","주소");
	session.setAttribute("name", "직");
	application.setAttribute("nick", "닉");
%>