<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/style.css" rel="stylesheet">
<style type="text/css">
* {
	font-family: gulim;
	font-size: 24px;
}
</style>
<script type="text/javascript">
function listT(){
	var url = "list";
	url = url + "?nowPage=${param.nowPage}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	location.href = url;
}
</script>
</head>
<body>
	<DIV class="title">삭제 확인</DIV>

	<Form name='frm' method='POST' action='delete'>
		<input type='hidden' name='memono' size='30' value='${param.memono}'>

		<DIV class="content">
			삭제를 하면 복구 될 수 없습니다.<br>
			<br> 삭제하시려면 삭제 처리 버튼을 클릭하세요.<br>
			<br> 취소는 '목록' 버튼을 누르세요. <br>
			<br> <input type='submit' value='삭제 처리'> <input
				type='button' value='목록' onclick="listT()">
		</DIV>
	</Form>

</body>
</html>
