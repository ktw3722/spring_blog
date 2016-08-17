<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title> 등록 </title>
<link href="${pageContext.request.contextPath }/css2/style.css" rel="stylesheet" type="text/css">
</head>

<!-- *********************************************** -->
<body style="margin: 0px">
<!-- *********************************************** -->

<div style="text-align: center; font-size: 24px">
  등록
</div>
    
<FORM name="frmData" method="POST" action="./create">
  <TABLE align='center' width='90%' cellpadding='0' cellspacing='0'>
    <tr>
      <th width='10%'>출력 날짜</th>
      <td colspan='5' align='left'>
        <input type="date" name='labeldate' size='10'>
        </td>
    </tr>  
    <tr>
      <th>출력 레이블</th>
      <td colspan='5' align='left'>
        <input type='text' name='label' value='' size='20'>
        형식: 최대 20자
        </td>
    </tr>  
    <tr>
      <th>제목</th>
      <td colspan='3' align='left'><input type='text' name='title' value='' size='100'></td>      
      </td>
    </tr>
    <tr>
      <th>내용</th>
      <td colspan='5' align='left'><TEXTAREA name='content' style="font-size:12; color:#000000;border:1px solid; width: 80%" rows="20"></TEXTAREA>
      </td>
    </tr>      
     
  </table>

  <div style='text-align: center'>
    <input type="submit" value="저장">
    <input type="button" value="취소(목록)" onclick="history.back();">
  </div>
  
</FORM>

<!-- *********************************************** -->
</body>
</html>
<!-- *********************************************** -->

