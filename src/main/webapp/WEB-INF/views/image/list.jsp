<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/ELFunctions" %>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type = "text/javascript">
function read(imgno){
	var url = "read";
	url = url+"?imgno=${imgno}";
	url = url+"&col=${col}";
	url = url+"&word=${word}";
	url = url+"&nowPage=${nowPage}";
	
	loction.href = url;
}
</script>

</head> 
<!-- *********************************************** -->
<body>
<!-- *********************************************** -->
<DIV class="title">이미지 목록</DIV>
<br>

<div class="search"> 
<FORM method='POST' action='list'>
	<select name="col">
		<option value = "wname"
		<c:if test="${col=='wname'}">selected</c:if>	
		>작성자</option>
		<option value = "title"
		<c:if test="${col=='title'}">selected</c:if>
		>제목</option>
		<option value = "content"
		<c:if test="${col=='content'}">selected</c:if>
		>내용</option>
		<option value = "total"
		<c:if test="${col=='total'}">selected</c:if>
		>전체 출력</option>
	</select>
	<input type = "text" name = "word" value="${word}">
	<input type = "submit" value = "검색">
</form>
</div>
<br>


<c:forEach items="${list}" var="dto">

<TABLE border='1' align='center' width='70%'>
   <TR>
   	<TD rowspan = '8' width = 20% align="center">
   	<Img src = '../img/${dto.filename}' width='100%'></TD>
   	<TH width='20%'>제목</TH>
   	<TD width = '60%'>
   	
   	<c:forEach begin="1" end="${dto.indent}">
   		&nbsp;&nbsp;   		
   	</c:forEach>
   		<c:if test="${dto.indent>0}">
   			[답변]
   		</c:if>
      <c:set var="rcount" value="${util:imgcount(dto.imgno,rdao) }"/>    
   	<a href='read?imgno=${dto.imgno}&col=${col}&word=${word}&nowPage=${nowPage}'>${dto.title}
   	<c:if test="${rcount>0 }">
            <span style="color:red;">(${rcount})</span>
          </c:if></a></TD>
   </TR>
   <TR>
    <TH>작성자</TH>
    <TD>${dto.wname}</TD>
   </TR>
   <TR>
    <TH>내용</TH>
    <TD>${dto.content}</TD>
   </TR>
   <TR>
    <TH>이미지파일명</TH>
    <TD>${dto.filename}</TD>
   </TR>
   <TR>
    <TH>grpno</TH>
    <TD>${dto.grpno}</TD>
   </TR>
   <TR> 
    <TH>indent</TH>
    <TD>${dto.indent}</TD>
   </TR>
   <TR>
    <TH>ansnum</TH>
    <TD>${dto.ansnum}</TD>
   </TR> 
   <TR>
    <TH>imgno</TH>
    <TD>${dto.imgno}</TD>
   </TR>
  
  
  </TABLE>
  <BR>
</c:forEach>

  
  <DIV class='bottom'>
  	${paging}
  	<br>
    <input type='button' value='등록' onclick = "location.href = 'create'">
  </DIV>

 
 
<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html> 