<%@ page contentType="text/html; charset=UTF-8" %> 
<% request.setCharacterEncoding("utf-8"); %> 

 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
	function input(f) {
		if (f.wname.value == "") {
			alert("이름을 입력하세요");
			f.wname.focus();
			return false;
		}

		if (f.title.value == "") {
			alert("제목을 입력하세요");
			f.title.focus();
			return false;
		}

		if (f.content.value == "") {
			alert("내용을 입력하세요");
			f.content.focus();
			return false;
		}
		if (f.filename.value == "") {
			alert("이미지를 올려주세요");
			f.filename.focus();
			return false;
		}
		var file = frm.filename.value;
		var fileExt = file.substring(file.lastIndexOf('.') + 1); //파일의 확장자를 구합니다.
	
		if (f.passwd.value == "") {
			alert("비밀번호를 입력하세요");
			f.passwd.focus();
			return false;
		}
		
		f.submit(); 
	}
</script>


<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head> 

 
<DIV class="title">답변</DIV>
 
<FORM name='frm' method='POST' action='reply' enctype="multipart/form-data"
  onsubmit="return input(this)">
<input type="hidden"	name="imgno"	value="${param.imgno}">
<input type="hidden"	name="grpno"	value="${dto.grpno}">
<input type="hidden"	name="indent"	value="${dto.indent}">
<input type="hidden"	name="ansnum"	value="${dto.ansnum}">
<input type="hidden"	name="col"	value="${param.col}">
<input type="hidden"	name="word"	value="${param.word}">
<input type="hidden"	name="nowPage"	value="${param.nowPage}">

  <TABLE class='table'>
    <TR>
      <TH>답변쟁이</TH>
      <TD><input type="text" name="wname"></TD>
    </TR>
    
    <TR>
      <TH>타이틀</TH>
      <TD><input type="text" name="title" size='80' value="${dto.title}"></TD>
    </TR>
    
    <TR>
      <TH>컨텐츠</TH>
      <TD><textarea name="content" rows='20' cols='80' maxlength="5000"></textarea></TD>
    </TR>
    <TR>
      <TH>이미지업로드</TH>
      <TD><input type="file" name="filenameMF" ></TD>
    </TR>
    <TR>
      <TH>비밀번호</TH>
      <TD><input type="password" name="passwd" placeholder="비밀번호를 써주세요!"></TD>
    </TR>
    
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='전송'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 
 

</html> 
