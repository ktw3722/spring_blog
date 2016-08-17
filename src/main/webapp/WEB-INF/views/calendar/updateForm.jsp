<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 수정 </title>
<script type="text/javascript">
function f_delete(){  // 삭제
    var sw = confirm('정말로 삭제하시겠습니까?');
    if (sw == true){
      document.frm.action = './delete';
      document.frm.submit();
    }
} 
  
function f_list(){  // 목록 
  location.href='./list';
} 
</script>
<link href="${pageContext.request.contextPath }/css2/style.css" rel="stylesheet" type="text/css">
</head>

<!-- *********************************************** -->
<body style="margin: 0px">
<!-- *********************************************** -->

<div style='text-align: center; font-size: 28px'>
  조회 및 수정
</div>
    
<FORM name="frm" method="POST" action="./update">
  <input type='hidden' name='calendarno' value='${dto.calendarno }'>
  
  <table align='center' width='90%' cellpadding='0' cellspacing='0'>
    <tr>
      <th width='15%'>레이블 날짜</th>
      <td width='85%' align='left'>
        <input type="date" name='labeldate' value='${dto.labeldate }' size='10'>
      </td>
    </tr>   
    <tr>
      <th>레이블</th>
      <td align='left'>
        <input type='text' name='label' value='${dto.label }' size='20'>
        형식: 최대 20자
        </td>
    </tr>  
    <tr>
      <th>제목</th>
      <td align='left'><input type='text' name='title' value='${dto.title }' size='100'></td>      
      </td>
    </tr>
    <tr>
      <th>내용</th>
      <td align='left'><TEXTAREA name='content' style="font-size:12; color:#000000;border:1px solid; width: 80%" rows="30">${dto.content}</TEXTAREA>
      </td>
    </tr>      
     
  </table>
  
  <div style='text-align: center;'>
    <input type="submit" value="저장">
    <input type="button" value="취소(목록)" onclick="f_list();">
    <input type="button" value="삭제" onclick="f_delete()">
  </div>
  
</FORM>

<!-- *********************************************** -->
</body>
</html>
<!-- *********************************************** -->


