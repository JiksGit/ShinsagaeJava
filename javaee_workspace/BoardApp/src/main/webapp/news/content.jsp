<%@page import="mvcproject.notice.domain.Comment"%>
<%@page import="java.util.List"%>
<%@page import="mvcproject.notice.repository.CommentDAO"%>
<%@page import="mvcproject.notice.repository.NewsDAO"%>
<%@page import="mvcproject.notice.domain.News"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	NewsDAO newsDAO = new NewsDAO();
	CommentDAO commentDAO = new CommentDAO();
%>
<%
	//내장 객체
	String news_id = request.getParameter("news_id");
	out.print("넘겨받은 파라미터는 "+ news_id);
	News news = newsDAO.select(Integer.parseInt(news_id));
	List<Comment> commentList = commentDAO.selectByNewsId(Integer.parseInt(news_id));
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">

<style>

body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}


input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>

<%@ include file="/inc/head_link.jsp" %>
<script type="text/javascript">
$(()=>{
	//서머노트 연동
	$("#content").summernote({
		height: 250
	});
	$("#content").summernote("code","<%=news.getContent()%>");
	
	$("#bt_news_edit").click(()=>{
		$("form").attr({
			action:"/news/update",
			method:"POST", // 머리에 데이터를 실어 나르게 됨, 따라서 편지 봉투에 나르는 격, 문제1) 노출, 문제2) 용량	
		});
		$("form").submit(); //전송
	});
	$("#bt_news_del").click(()=>{
	});
	$("#bt_news_list").click(()=>{
		location.href="/news/list.jsp";
	});
	$("#bt_comment_regist").click(()=>{
		$("#comment_form").attr({
			method:"post", //HTTP프로토콜 통신에 사용되는 데이터 구성(Payload) body에 탑재됨
			action:"/comment/regist"
		});	
		$("#comment_form").submit();
	});
});

</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form action="/action_page.php">
    <label for="fname">Title</label>
    <input type="hidden" name="news_id" value="<%=news.getNews_id()%>">
    <input type="text" id="fname" name="title" value="<%=news.getTitle()%>">

    <label for="lname">Writer</label>
    <input type="text" id="lname" name="writer"  value="<%=news.getWriter()%>">

    <label for="subject">Subject</label>
    <textarea id="content" name="content"  value="<%=news.getContent()%>" style="height:200px"></textarea>

    <input type="button" value="수정" id="bt_news_edit">
    <input type="button" value="삭제" id="bt_news_del">
    <input type="button" value="목록" id="bt_news_list">
  </form>
  
  <div id="comment_header">
  	<form id="comment_form">
	  	<input type="hidden" name="news_id" value="<%=news.getNews_id()%>">
	  	<input type="text" style="width:73%" name="msg">
	  	<input type="text" style="width:20%" name="user">
	  	<input type="button" value="등록" id="bt_comment_regist">
  	</form>
  </div>
    <div id="comment_content">
    	<table>
   			<% for( Comment comment : commentList) {%>
		   		<tr>
		   			<th><%= comment.getMsg() %></th>
		   			<th><%= comment.getUser() %></th>
		   			<th><%= comment.getMsgdate() %></th>
		   		</tr>
    		<% } %>
    	</table>
    </div>
</div>

</body>
</html>
