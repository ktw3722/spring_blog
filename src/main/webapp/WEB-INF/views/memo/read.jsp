<%@ page language="java" contentType="text/html; charset=UTF-8" 
	  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" 
content="text/html; charset=UTF-8">

<title>Insert title here</title>
<link href="../css/style.css" rel="stylesheet">
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}
</style>


<script type = "text/javascript">
function updateM(){
	var url = "update";
	url += "?memono=${vo.memono}";
	url = url + "&nowPage=${param.nowPage}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	
	location.href = url;
	//혼파망...-_-);
}

function deleteM(){
	var url = "delete";
	url += "?memono=${vo.memono}";
	url = url + "&nowPage=${param.nowPage}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	location.href = url;
}
function listM(){
	var url = "list";
	url = url + "?nowPage=${param.nowPage}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	location.href = url;
}

</script>

</head>
<body>

	<DIV class="title">
		조회</DIV>

	<table>
		<TR>
			<TH>제목</TH>
			<TD>${vo.title}</TD>
		</TR>
		<TR>
			<TH>내용</TH>
			<TD>${vo.content}</TD>
		</TR>
		<TR>
			<TH>조회수</TH>
			<TD>${vo.viewcnt}</TD>
		</TR>
		<TR>
			<TH>등록일</TH>
			<TD>${vo.wdate}</TD>
		</TR>
	</table>

	<DIV class="bottom">
		<input type='button' value='등록'
		onclick="location.href='./create'">
			
	 	<input type='button' value='목록' 
	 	onclick="listM()">
	 	
	 	<input type='button' value='수정' 
	 	onclick="updateM()">
	 	<!-- on으롷 시작하는 함수는 그냥 자바스크립트 취급이라서
	 	< % % > 안써도 됨
	 	 -->
	 	 
	 	<input type='button' value='삭제' 
	 	onclick="deleteM()"> 
	 	 
	</DIV>

</body>
</html>

