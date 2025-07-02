<%@ page contentType="text/html; charset=UTF-8"%>
<% 
	// 이 main.jsp가 Servlet으로 변경되었을 때의 service() 메서드 영역 
	String bg = request.getParameter("bg");
	out.print(bg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(()=>{
		$("button").click(()=>{
			// select 박스의 값 얻기
			location.href="/color/main.jsp?bg=" +$("select").val(); // 링크는 Get방식의 요청이다
		});
	});
</script>
</head>
<body bgcolor="<%= bg %>">

	<select>
		<option <% if(bg.equals("red")){ %>selected<%} %> value="red">red</option>
		<option <% if(bg.equals("blue")){ %>selected<%} %> value="blue">blue</option>
		<option <% if(bg.equals("orange")){ %>selected<%} %> value="orange">orange</option>
		<option <% if(bg.equals("purple")){ %>selected<%} %> value="purple">purple</option>
	</select>
	
	<button>배경색 바꾸기</button>
</body>
</html>