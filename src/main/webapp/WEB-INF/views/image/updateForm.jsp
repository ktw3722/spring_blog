<%@ page contentType="text/html; charset=UTF-8" %> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 24px; 
} 
</style> 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body leftmargin="0" topmargin="0">
<!-- *********************************************** -->
 
<DIV class="title">사진 수정</DIV>

<FORM name='frm' 
 method='POST' 
 action='update'
 enctype="multipart/form-data">
		   

  <input type="hidden" name="imgno" value="${param.imgno}"/> 
  <input type="hidden" name="oldfile" value="${param.oldfile}"/>
  <input type="hidden"	name="col"	value="${param.col}">
  <input type="hidden"	name="word"	value="${param.word}">
  <input type="hidden"	name="nowPage"	value="${param.nowPage}">
   
  <TABLE class='table'>
    <TR>
      <TH>원본파일</TH>
      <TD>
       <img src="../img/${param.oldfile}">
       원본파일명:${param.oldfile}
      </TD>
    </TR>
    <TR>
      <TH>변경파일</TH>
      <TD>
       <input type="file" name="filenameMF" >
      </TD>
    </TR>
    
  
     <tr>  
       <th>이름</th> 
        <td><input value ="${dto.wname}" name ="wname"> </td>
         
          
      
     </tr> 
   
     <tr>  
       <th>제목</th> 
       <td><input value ="${dto.title}" name ="title"> </td>
       
     </tr> 
     <tr>  
       <th>내용</th> 
       <TD><TEXTAREA name='content' rows='10' cols='30'>${dto.content}</TEXTAREA></td>
     </tr> 
     
     <tr>  
       <th>비밀번호</th> 
       <TD><input type="password"  name ="passwd"></td>
     </tr> 
       

     
     

  
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='변경'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 
 
<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html> 