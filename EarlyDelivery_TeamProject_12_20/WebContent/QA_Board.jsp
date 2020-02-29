<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시판</title>
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
 	//session.setAttribute("user_id", "admin"); 
	String user_id = (String)session.getAttribute("user_id");
	if(user_id == null){
		user_id = "unlogin";
	}

	String pagefile = (String)request.getAttribute("page"); 
	if(pagefile == null){
		pagefile = "QA_Board_List.jsp";
	}

%>

<script type="text/javascript">
	function login_confirm() {
		if( '<%=user_id%>' == "unlogin"){
			alert("로그인을 먼저 해주세요");
			return false;
		}else if ( '<%=user_id%>' != "unlogin"){
			return true;
		}
	}
</script>

</head>
<body>
	<font size="5"><p align="Center">Q&A</p></font>
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
				<form action = "QA_Board_Write.jsp" method="get" onsubmit="return login_confirm()">
					<input type="submit" value="글쓰기">
				</form>
			</td>
			<td width='200'></td>
			<td width='200' align="right">
				<form action = "QA_Search" method="get">
				<input type="text" name="search" placeholder="회원 ID 검색" size="13">
				<input type="submit" value="검색">
				</form>
			</td>
		</tr>
	
	</Table>



</body>
</html>