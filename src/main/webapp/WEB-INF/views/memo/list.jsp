<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/ELFunctions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../css/style.css" rel="stylesheet">
<style>
* {
	font-family: gulim;
	font-size: 20px;
}
</style>
<script type="text/javascript">
	function read(memono){
		var url = "read";
		url = url + "?memono=" + memono;
		url = url + "&nowPage=${nowPage}";
		url = url + "&col=${col}";
		url = url + "&word=${word}";
		//띄어쓰기 주의 -_-) ? memo = (x) ?memo= (o)
		location.href = url; //자바스크립트에서 -get 방식- 링크 보내기
		
	}

</script>

</head>
<body>
	<DIV class='title'>메모 목록</DIV>
	<div class="search">
	<form method="post" action="list">
		<select name="col">
			<option value="title"
			<c:if test="${col=='title'}">selected</c:if>
			>제목</option>
			<option value="wdate"
			<c:if test="${col=='wdate'}">selected</c:if>
			>날짜</option>
			<option value="hit"
			<c:if test="${col=='hit'}">selected</c:if>
			>조회수</option>
			<option value="total"
			<c:if test="${col=='total'}">selected</c:if>
			>전체출력</option>
		</select>
	<input type="text" name="word" value=${word}>
	<input type="submit" value="검색">
	</form>
	</div>

	<Table>
		<TR>
			<TH>번호</TH>
			<TH>제목</TH>
			<TH>날짜</TH>
			<TH>조회수</TH>
		</TR>
		<c:choose>
		<c:when test="${empty list}">
		<TR>
			<TD colspan='4'>등록된 메모가 없습니다.</TD>
		</TR>
		</c:when>
		<c:otherwise>
		<c:forEach var="vo" items="${list}">
		<TR>
			<TD>${vo.memono}</TD>
			<TD><a href = "javascript:read('${vo.memono}')">${vo.title}</a>
			<c:if test="${util:newImg(vo.wdate)}"><img src="../images/new.gif"></c:if>
			</TD>
			<TD>${vo.wdate}</TD>
			<TD>${vo.viewcnt}</TD>
		</TR>
		</c:forEach>
		</c:otherwise>
		</c:choose>
		
	</Table>

	<DIV class='bottom'>
	${paging}
		<input type="button" value='등록'
			onclick="location.href='create'">
	</DIV>




</body>
</html>