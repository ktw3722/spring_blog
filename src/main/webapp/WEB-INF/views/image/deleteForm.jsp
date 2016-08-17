<%@ page contentType="text/html; charset=UTF-8" %> 

 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head> 

 
<DIV class="title">이미지삭제</DIV>
 
<FORM name='frm' method='POST' action='delete'>
  <input type="hidden" name="imgno" value="${param.imgno}">
  <input type="hidden" name="oldfile" value="${param.oldfile}">
  <input type="hidden"	name="col"	value="${param.col}">
  <input type="hidden"	name="word"	value="${param.word}">
  <input type="hidden"	name="nowPage"	value="${param.nowPage}">
  <div class="content">
  비밀번호:
  <input type ="password" name="passwd">
  
  </div>
  
  <DIV class='bottom'>
  
    <input type='submit' value='삭제'>
    <input type='button' value='취소' onclick="history.back()">
     
  </DIV>
</FORM>
 
 

</html> 
 