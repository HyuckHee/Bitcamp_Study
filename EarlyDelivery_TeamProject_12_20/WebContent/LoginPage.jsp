<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%request.setCharacterEncoding("UTF-8");%>
		<%response.setCharacterEncoding("UTF-8");%>
		<%response.setContentType("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript">
function check_form() {
	var id_length = login_form.id.value.length;
	var pw_length = login_form.pw.value.length;
	if(id_length<3){
		alert('아이디를 입력해주세요');
		return false;
	}
	if(pw_length==0)
		{
		alert('비밀번호를 입력해주세요.');
		return false;
		}
	
}
function serch_id(){
		try{
		var url = "/earlybird/serch_id.jsp";
		var ret = window.open(url,"LoginPage.jsp",
						"toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=500,height=150");
		}catch (e) {
			alert('serch_id1() 데이터베이스 이름 변경');
		}
		return;
}

</script>


</head>
<body>
<h2><p align="center">로그인</p></h2>
<form name="login_form" action="Login" method="post"
		onsubmit="return check_form()">
<table style="font-size: 12px; height: 240px; margin-left: 40%;">
<tr height="60px">
<td>아이디: </td>
<td><input type="text" name="id"></td>
</tr>
<tr height="60px">
<td>비밀번호: </td>
<td><input type="password" name="pw"></td>
</tr>
<tr height="40px" align="center">
<td><p><input type="submit" style="font-size: 14px;" value ="로그인"></p></td>
<td><input style="font-size: 12px;" type="button" name="idsearch" value="아이디 찾기" onclick="serch_id()" ><br><input style="font-size: 11px;" type="button"  name="idsearch" value="비밀번호찾기"></td>
</tr>
</table>
</form>

</body>
</html>