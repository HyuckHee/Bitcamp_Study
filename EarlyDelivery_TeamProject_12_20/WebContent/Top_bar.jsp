<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

	a{
		color: black;
		text-decoration: none;
	}
	
	body {
	font-size: 12px;
	}

</style>


<% 
	String Login = "";
	String Logout = "";
 	String Welcome="";
 	String Join="";
 	String Mypage="";
 	
	if(session.getAttribute("user_id") == null){
		Login = "로그인";
		Join = "회원가입";
	} else{
		Logout = "로그아웃"; 
		Mypage = "마이페이지";
		Welcome = session.getAttribute("user_id")+" 님 환영합니다.";
	}
%>


<script type="text/javascript">

	function logout_confirm() {
		
		var check = confirm("로그아웃 하시겠습니까?");
		if(check == true){
			alert("정상적으로 로그아웃 되었습니다.");
			return true;
		} else{
			return false;
		}
	}

</script>

</head>
<body>

<table style="margin-left: auto; margin-right: 20%;">
	<tr>
		<td><%=Welcome%></td>
		<td><a href="LoginPage.jsp"><%=Login%></a>
			<a href="LogoutPage.jsp" onclick="return logout_confirm()"><%=Logout%></a></td>
		<td><a href="SignUp.jsp"><%=Join%></a></td>
		<td><a href=""><%=Mypage%></a></td>
	</tr>
</table>
</body>
</html>