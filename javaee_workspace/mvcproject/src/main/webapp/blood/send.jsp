<%@ page contentType="text/html; charset=UTF-8"%>

<%
// jsp 파일 하나로 모든 것을 처리하는 방법
/* String msg = null;

out.print(msg);
} */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/blood.do" method="post">
		<select name="blood">
			<option value="">혈액형 선택</option>
			<option value="A">A</option>
			<option value="B">B</option>
			<option value="O">O</option>
			<option value="AB">AB</option>
		</select>
		<button>전송</button>
	</form>
</body>
</html>
