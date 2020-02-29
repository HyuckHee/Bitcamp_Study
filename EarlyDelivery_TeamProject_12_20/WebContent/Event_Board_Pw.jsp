<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pack.DBManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("UTF-8");
	String ori_id = (String)request.getParameter("ori_id");
	
	DBManager db = new DBManager();
	db.DBConnection("ED");
	db.DBTable_Select_Id(request, "ED", "Event_Board", ori_id);
%>
</head>
<body>

	<jsp:forward page="Event_Board_Show.jsp">
		<jsp:param name="ori_id" value='<%=request.getAttribute("ori_id")%>'/>
		<jsp:param name="user_id" value='<%=request.getAttribute("user_id")%>'/>
		<jsp:param name="password" value='<%=request.getAttribute("password")%>'/>
		<jsp:param name="title" value='<%=request.getAttribute("title")%>'/>
		<jsp:param name="text" value='<%=request.getAttribute("text")%>'/>
	</jsp:forward>

</body>
</html>