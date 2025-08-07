<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">

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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">
$(()=>{
	
	$("input[type='button']").click(()=>{
		$("form").attr({
			action:"/admin/notice/regist",
			method:"POST", // 머리에 데이터를 실어 나르게 됨, 따라서 편지 봉투에 나르는 격, 문제1) 노출, 문제2) 용량	
		});
		$("form").submit(); //전송
	});
});

</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form action="/action_page.php">
    <label for="fname">Title</label>
    <input type="text" id="fname" name="title" placeholder="제목 입력">

    <label for="lname">Writer</label>
    <input type="text" id="lname" name="writer" placeholder="작성자 입력">

    <label for="subject">Subject</label>
    <textarea id="content" name="content" placeholder="Write something.." style="height:200px"></textarea>

    <input type="button" value="Submit">
  </form>
</div>

</body>
</html>
