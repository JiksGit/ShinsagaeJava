<%@page import="com.sinse.notice.model.Notice"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%!// !를 선언하면, 선언부라 하여 멤버변수와 멤버메서드를 작성할 수 있다.

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/shop";
	String user = "shop";
	String pwd = "1234";

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public void connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}%>
<% 
	connect(); 

	// 클라이언트가 전송한 notice_id 값을, 요청 객체로부터 꺼내서 사용해보자
	// jsp에서는 필수적인 기능들을 이미 생성하여 변수명까지도 지정해 버리는데, 이러한 객체를 내장객체
	String notice_id = request.getParameter("notice_id");
	
	StringBuffer sql = new StringBuffer();
	sql.append("select * from notice where notice_id = " + notice_id); // 유저가 선택한 바로 그 게시물의 pk
	
	pstmt=con.prepareStatement(sql.toString());
	rs=pstmt.executeQuery();
	
	Notice notice = null;
	if(rs.next()){
		while (rs.next()) {
			notice = new Notice();
			notice.setNotice_id(rs.getInt("notice_id"));
			notice.setTitle(rs.getString("title"));
			notice.setWriter(rs.getString("writer"));
			notice.setContent(rs.getString("content"));
			notice.setRegdate(rs.getString("regdate"));
			notice.setHit(rs.getInt("hit"));
		}
	}
	
	out.print(sql.toString());
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset = "UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

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

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>

<!-- summernote link -->
<!-- CDN (Content Delivery Network -->
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ 
	  $('#summernote').summernote();
	
	  $("input[type='button']").click(()=>{
		  $("form").submit();
	  });
	  
	  
	  
	});
</script>


</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form method = "post" action = "/notice/write">
    <label for="fname">제목</label>
    <input type="text" id="fname" name="title" value="<%=notice.getTitle() %>">

    <label for="lname">작성자</label>
    <input type="text" id="lname" name="writer" value="<%=notice.getWriter() %>">

    <label for="summernote">내용</label>
    <textarea id="summernote" name="content" value="<%=notice.getContent() %>" style="height:200px"></textarea>

    <input type="button" value="수정">
    <input type="button" value="삭제">
    <input type="button" value="목록">
  </form>
</div>

</body>
</html>
<%
	con.close();
%>