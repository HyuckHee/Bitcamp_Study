<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pack.DBManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색한 게시글 리스트</title>
<style type="text/css">
	a{
		color: black;
		text-decoration: none;
	}
	
	body {
	font-size: 12px;
	}
</style>
</head>
<body>
<%
 	String search =(String)request.getParameter("search");
	
	DBManager db = new DBManager();
	db.DBConnection("ED");
	db.DBTable_Select_UserId(request, "ED", "QA_Board", search);
	db.DBTable_Reply_Amount(request, "ED", "QA_Reply");

	int index = (int)request.getAttribute("index");
 		
	out.println("<Table style='margin-left: auto; margin-right: auto;' bgcolor = '#fff0f0'>");
	out.println("<tr>");
	out.println("<td width='90' style='padding:10px;''>No.</td>");
	out.println("<td width='300'>Contents</td>");
	out.println("<td width='110'>Name</td></tr>");
	out.println("</Table>");
	
	
	for(int i = (index-1); i >= 0; i--){
		int id = (int)request.getAttribute("id"+i);
		String user_id = (String)request.getAttribute("user_id"+i);
		String title = (String)request.getAttribute("title"+i);
		String text = (String)request.getAttribute("text"+i);
		String ori_id = (String)request.getAttribute("ori_id"+i);
 		if(request.getAttribute(ori_id) == null){
 			request.setAttribute(ori_id, 0);
 		}
 		int reply_amount = (int)request.getAttribute(ori_id);
	
 		out.println("<Table style='margin-left: auto; margin-right: auto;'>");
		out.println("<tr>");	
		out.println("<td width='100'>"+id+"</td>");
		out.println("<td width='300'><a href='QA_Board_Pw.jsp?ori_id="+ori_id+"'>"+title+"</a>");
		out.println("<font color='#C6C6C6'>("+reply_amount+")</font></td>");
		out.println("<td width='100'>"+user_id+"</td></tr>");
		out.println("<hr noshade align='center' width='530' size='1' color='#EAEAEA'></hr>");
	}
	out.println("</Table>");
	out.println("<hr noshade align='center' width='530' size='1' color='#EAEAEA'></hr>");
		

%>
</body>
</html>