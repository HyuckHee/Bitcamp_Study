<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤 게시판</title>
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
	String pagefile = (String)request.getAttribute("page");
 
	if(pagefile == null){
		pagefile = "Event_Board_List.jsp";
	}
%>

<% 
	String user_id = (String)session.getAttribute("user_id");

	if(user_id != null){
		if(user_id.equals("admin")){
			user_id = "admin";
		} else {
			user_id = "0";
		}
	} else if(user_id == null) {
		user_id = "0";
	}
%>

<script type="text/javascript">
	function login_confirm() {
		if('<%=user_id%>' == "admin"){
			return true;
		}else if('<%=user_id%>' == "0"){
			alert("관리자만 작성할 수 있습니다");
			return false;
		}
	}
</script>

</head>
<body>
	<font size="5"><p align="Center">Event</p></font>
	<p align="Center">
	
		<a href="Notice_Board.jsp"><input type="image" alt="공지사항" src="img/Notice.jpg"></a>
		<a href="QA_Board.jsp"><input type="image" alt="Q&A" src="img/Q&A.jpg"></a>
		<a href="Event_Board.jsp"><input type="image" alt="Event" src="img/Event.jpg"></a>
	</p>
	<br>

	<jsp:include page='<%=pagefile%>'></jsp:include>

	<Table style='margin-left: auto; margin-right: auto;'>
		<tr>
			<td width='100'>
				<form action = "Event_Board_Write.jsp" method="get" onsubmit="return login_confirm()">
					<input type="submit" value="글쓰기">
				</form>
			</td>
			<td width='200'></td>
			<td width='200' align="right">
				<form action = "Event_Search" method="get">
				<input type="text" name="search" placeholder="회원 ID 검색" size="13">
				<input type="submit" value="검색">
				</form>
			</td>
		</tr>
	
	</Table>



</body>
</html>