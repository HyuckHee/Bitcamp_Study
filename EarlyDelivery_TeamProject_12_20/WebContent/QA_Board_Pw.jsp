<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pack.DBManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
	String ori_id = (String)request.getParameter("ori_id");
	String Login_id = (String)session.getAttribute("user_id");
	
	DBManager db = new DBManager();
	db.DBConnection("ED");
	db.DBTable_Select_Id(request, "ED", "QA_Board", ori_id);
%>

<script type="text/javascript">
	
	//입력한 비밀번호와 DB에 저장된 게시물 비밀번호를 비교하는 함수
	function pw_match(){		
		
		var Login_id = "<%=Login_id%>";
		var pw_input = pw_form.pw_input.value;
		var pw_db = "<%=request.getAttribute("password")%>";
		
		if(Login_id == "admin"){
			return true;
		} else {
			if(pw_input != pw_db){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			} else if(pw_input == pw_db){
				return true;
			}
		}
	}
	
</script>

	


</head>
<body>

	<form name="pw_form" action="QA_Board_Show.jsp" onsubmit="return pw_match()" method="get">
		<Table style="margin-left: auto; margin-right: auto;">
			<tr>
				<td><input type="password" name="pw_input" placeholder="게시물의 비밀번호를 입력하세요."></td>
				<td><input type="submit" value="확인"></td>
			</tr>
		</Table>	
		<input type="hidden" name="ori_id" value="<%=request.getParameter("ori_id")%>">
		<input type="hidden" name="user_id" value="<%=request.getAttribute("user_id")%>">
		<input type="hidden" name="password" value="<%=request.getAttribute("password")%>">
		<input type="hidden" name="title" value="<%=request.getAttribute("title")%>">
		<input type="hidden" name="text" value="<%=request.getAttribute("text")%>">
	</form>
		
	
		
</body>
</html>