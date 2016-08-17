<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function rcheck(tarea){
	if('${sessionScope.id}'==""){
	if(confirm("로그인후 댓글를 쓰세요")){
	var url = "../member/login";
	url = url + "?imgno=${dto.imgno}";
	url = url + "&nowPage=${param.nowPage}";
	url = url + "&nPage=${nPage}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&imgflag=../image/read";
	location.href=url;
	}else{
	tarea.blur();
	}
	}
	}
	 
	function input(f){
	if('${sessionScope.id}'==""){
	if(confirm("로그인후 댓글를 쓰세요")){
	var url = "../member/login";
	url = url + "?imgno=${dto.imgno}";
	url = url + "&nowPage=${param.nowPage}";
	url = url + "&nPage=${nPage}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&imgflag=../image/read";
	location.href=url;
	return false;
	}else{
	 
	return false;
	}
	}else if(f.content.value==""){
	alert("댓글 내용을 입력하세요.");
	f.content.focus();
	return false;
	}
	}
	function rupdate(rnum,rcontent){
	var f = document.rform;
	f.content.value = rcontent;
	f.rnum.value = rnum;
	f.rsubmit.value="수정";
	f.action="./rupdate"
	}
	function rdelete(rnum){
	if(confirm("정말삭제 하겠습니까?")){ 
	var url = "./rdelete";
	url = url + "?rnum="+rnum;
	url = url + "&imgno=${dto.imgno}";
	url = url + "&nowPage=${param.nowPage}";
	url = url + "&nPage=${nPage}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	location.href=url; 
	}
	}
</script>
<style type="text/css"> 
.rcreate{
  font-size: 20px;
  font-weight:bold;
  text-align: left;
  border-style: solid;   /* 실선 */
  border-width: 1px;     /* 선 두께 */
  border-color: #AAAAAA; /* 선 색깔 */
  color: #000000;        /* 글자 색깔 */
  width: 35%;            /* 화면의 30% */ 
  padding: 10px;         /* 위 오른쪽 아래 왼쪽: 시간 방향 적용 */
  
  /* padding: 50px 10px;  50px: 위 아래, 10px: 좌우 */
  /* padding-top: 30px;  상단만 간격을 30px 지정   */
  
  margin: 20px auto; /* 가운데 정렬 기능, 20px: 위 아래, auto: 오른쪽 왼쪽*/
}
.rlist{
  line-height:1.2em;
  font-size: 15px;
  font-weight:bold;
  text-align: left;
  border-style: solid;   /* 실선 */
  border-width: 1px;     /* 선 두께 */
  border-color: #AAAAAA; /* 선 색깔 */
  color: #000000;        /* 글자 색깔 */
  width: 35%;            /* 화면의 30% */ 
  padding: 10px;         /* 위 오른쪽 아래 왼쪽: 시간 방향 적용 */
  
  /* padding: 50px 10px;  50px: 위 아래, 10px: 좌우 */
  /* padding-top: 30px;  상단만 간격을 30px 지정   */
  
  margin: 20px auto; /* 가운데 정렬 기능, 20px: 위 아래, auto: 오른쪽 왼쪽*/
}
hr{
  text-align: center;
  border-style: solid;   /* 실선 */
  border-width: 1px;     /* 선 두께 */
  border-color: #AAAAAA; /* 선 색깔 */
  width: 45%;            /* 화면의 30% */ 
}
 
 
</style>  
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>
	<!-- *********************************************** -->

	<DIV class="title">사진 목록
	</DIV>

	<DIV class = "content">
	<TABLE class='table'>
		<TR>
			<TH colspan="5"><img src="../img/${dto.filename}" width = "700px" height="550px"></TH>
		</TR>
		
		
		<TR>
			<c:choose>
				<c:when test="${not empty dto2.pre_file2}">
					<TD align="center"><A href='./read?imgno=${dto2.pre_imgno2}&col=${param.col}&word=${param.word}&nowPage=${param.nowPage}'><img src="../img/${dto2.pre_file2}" width="150px" height ="150px"><br>${dto2.pre_file2}</a></TD>
				</c:when>
				<c:otherwise>
					<TD align="center"><img src="../img/imgfornull.jpg" width="150px" height ="150px"></TD> 
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${not empty dto2.pre_file1}">
					<TD align="center"><A href='./read?imgno=${dto2.pre_imgno1}&col=${param.col}&word=${param.word}&nowPage=${param.nowPage}'><img src="../img/${dto2.pre_file1}" width="150px" height ="150px"><br>${dto2.pre_file1}</a></TD>
				</c:when>
				<c:otherwise>
					<TD align="center"><img src="../img/imgfornull.jpg" width="150px" height ="150px"></TD> 
				</c:otherwise>
			</c:choose>
		
		<TD align="center" style="border-style: solid; border-width: 3px;  border-color: #de2125;"><img src="../img/${dto.filename}" width="150px" height ="150px"><br>${dto.filename}</TD>
			
			<c:choose>
				<c:when test="${not empty dto2.nex_file1}">
					<TD align="center"><A href='./read?imgno=${dto2.nex_imgno1}&col=${param.col}&word=${param.word}&nowPage=${param.nowPage}'><img src="../img/${dto2.nex_file1}" width="150px" height ="150px"><br>${dto2.nex_file1}</a></TD>
				</c:when>
				
				<c:otherwise>
					<TD align="center"><img src="../img/imgfornull.jpg" width="150px" height ="150px"></TD> 
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${not empty dto2.nex_file2}">
					<TD align="center"><A href='./read?imgno=${dto2.nex_imgno2}&col=${param.col}&word=${param.word}&nowPage=${param.nowPage}'><img src="../img/${dto2.nex_file2}" width="150px" height ="150px"><br>${dto2.nex_file2}</a></TD>
				</c:when>
				
				<c:otherwise>
					<TD align="center"><img src="../img/imgfornull.jpg" width="150px" height ="150px"></TD> 
				</c:otherwise>
			</c:choose>
	



		</TR>
	</TABLE>
	</DIV>
	<DIV class='bottom'>
		<input type='button' value='뒤로가기' onclick="history.back()">
		<input type='button' value='목록' onclick="location.href='list?imgno=${dto.imgno}&col=${param.col}&word=${param.word}&nowPage=${param.nowPage}'">
		<input type='button' value='답변' onclick="location.href='reply?imgno=${dto.imgno}&col=${param.col}&word=${param.word}&nowPage=${param.nowPage}'">
		<input type='button' value='수정' onclick="location.href='update?imgno=${dto.imgno}&oldfile=${dto.filename}&col=${param.col}&word=${param.word}&nowPage=${param.nowPage}'">
		<input type='button' value='삭제' onclick="location.href='delete?imgno=${dto.imgno}&oldfile=${dto.filename}&col=${param.col}&word=${param.word}&nowPage=${param.nowPage}'">
	</DIV>
<hr>
  <c:forEach var="rdto" items="${rlist}">
  <div class="rlist">
   ${rdto.id}<br/>
   <p>${rdto.content}</p>
   ${rdto.regdate}
   <c:if test="${sessionScope.id==rdto.id }">
   <span style="float: right;">
   <a href="javascript:rupdate('${rdto.rnum}','${rdto.content }')">
   수정</a>|<a href="javascript:rdelete('${rdto.rnum}')">삭제</a>
   </span>
   </c:if>
  </div>
  </c:forEach>
  <div class="rcreate">
  <form name="rform" action="./rcreate" method="post" onsubmit="return input(this)">
  <textarea rows="3" cols="28" name="content" onclick="rcheck(this)"></textarea>
  <input type="submit" name="rsubmit" value="등록">
  <input type="hidden" name="imgno" value="${dto.imgno}">
  <input type="hidden" name="id" value="${sessionScope.id}">
  <input type="hidden" name="nowPage" value="${param.nowPage}">
  <input type="hidden" name="nPage" value="${nPage}">
  <input type="hidden" name="col" value="${param.col}">
  <input type="hidden" name="word" value="${param.word}">
  <input type="hidden" name="rnum" value="0">
  
  
  </form>
  </div>
  <div class="bottom">
  ${paging}
  </div>

	<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html>