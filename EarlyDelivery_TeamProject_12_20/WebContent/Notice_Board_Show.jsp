<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pack.DBManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		font-size: 12px;
	}
	

</style>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("UTF-8");
	
	
	String Login_id = (String)session.getAttribute("user_id");
	if(session.getAttribute("user_id")==null){
		Login_id = "visitor";
	}
	
	String ori_id = (String)request.getParameter("ori_id");
	String user_id = (String)request.getParameter("user_id");
	String password = (String)request.getParameter("password");
	String title = (String)request.getParameter("title");
	String text = (String)(request.getParameter("text")).replace("\n", "<br>");

	DBManager db = new DBManager();
	db.DBConnection("ED");
	db.DBTable_Select_Id(request, "ED", "Notice_Board" ,ori_id);
	db.DBTable_Reply_Select_Id(request, "ED", "Notice_Reply", ori_id);
	int index = (int)request.getAttribute("index");
%>

<script type="text/javascript">

	function delete_reconfirm() {	
		var Login_id = '<%=session.getAttribute("user_id")%>';
		
		if(Login_id == "admin"){
			var check = confirm("정말로 글을 삭제하시겠습니까?");
			
			if(check == true){
				return true;
			} else {
				return false;
			}
		} else {
			alert("관리자가 아닙니다");
			return false;
		}
	}
	
	function modify_reconfirm() {	
			var Login_id = '<%=session.getAttribute("user_id")%>';
			
			if(Login_id == "admin"){
				return true;
			} else{
				alert("관리자가 아닙니다");
				return false;
			}
	}
	
	
	function write_reply() {
		var reply = reply_form.reply_text.value;

		if('<%=Login_id%>' != 'visitor'){
			if(reply.length <= 300 && reply.length != 0){
	
				return true;
			}else {
				alert("댓글은 300자 이내로 입력해주세요");
				return false;
			}	
		} else if('<%=Login_id%>' == 'visitor'){
			alert("방문자는 댓글을 쓰실 수 없습니다.");
			return false;
		}
	}

</script>
</head>
<body>

<Table bgcolor="White" style="margin-left: auto; margin-right: auto;">
	<tr>
		<td colspan='3' style="padding:10px; font-size: 14px;"><%=request.getParameter("title")%></td>
	</tr>

	<tr>
		<td colspan='3'><hr noshade align='center' width='510' size='1' color='#EAEAEA'></hr></td>
	</tr>

	<tr bgcolor="#F6F6F6">
		<td colspan='3'height='400' style="padding:10px;"><%=text%></td>
	</tr>
	<tr>
		<td colspan='3'><hr noshade align='center' width='510' size='1' color='#EAEAEA'></hr></td>
	</tr>
	<tr>
		<td align="left"><a href = "Notice_Board.jsp"><input type="button" value="목록보기"></a></td>
		<td align="right" width="400">
			<form name="modify_form" action="Notice_Board_Modify.jsp" method="get" onsubmit="return modify_reconfirm()">
				<input type="submit" value="글 수정">
				<input type="hidden" name="ori_id" value="<%=request.getParameter("ori_id")%>">
			</form>	
		</td>
		<td align="right">
			<form name="delete_form" action="Notice_Delete" method="get" onsubmit="return delete_reconfirm()">
				<input type="submit" value="글 삭제">
 				<input type="hidden" name="ori_id" value="<%=request.getParameter("ori_id")%>">
			</form>	
		</td>
	</tr>
</Table>
<br>
<br>


	<form name="reply_form" action="Notice_Reply" method="get" onsubmit="return write_reply()">


		<Table style="margin-left: auto; margin-right: auto;">
			<tr>
				<td colspan='3'>
				<textarea name="reply_text" cols="70" rows="5" placeholder="댓글 쓰기(300자 이내)"></textarea>
				</td>
			</tr>
			<tr>
			<td></td>
			<td width="450"></td>
			<td align="right"><input type="submit" name="btn_reply" value="댓글 달기"></td>
			</tr>
		</Table>
		<input type="hidden" name="ori_id" value="<%=request.getParameter("ori_id")%>">
		<input type="hidden" name="user_id" value="<%=request.getParameter("user_id")%>">
		<input type="hidden" name="password" value="<%=request.getParameter("password")%>">
		<input type="hidden" name="title" value="<%=request.getParameter("title")%>">
		<input type="hidden" name="text" value="<%=request.getParameter("text")%>">
	</form>	
<br>

	<%
		for(int i = (index-1); i >= 0; i--){
				String reply_user_id = (String)request.getAttribute("reply_user_id"+i);
				String reply_text = (String)request.getAttribute("reply_text"+i);
				String reply_id = (String)request.getAttribute("reply_id"+i);
				
			if(reply_user_id != null && reply_text !=null){
				out.println("<Table width='510' style='margin-left: auto; margin-right: auto;'>");
				out.println("<tr height='30' bgcolor='#F6F6F6'>");
				out.println("<td>ID:  "+reply_user_id+"</td></tr>");
				out.println("<tr height='70' bgcolor='#F6F6F6'>");
				out.println("<td>"+reply_text+"</td></tr>");
				if(Login_id.equals(reply_user_id) || Login_id.equals("admin")){
					out.println("<tr><td align='right' bgcolor='white'>");
					out.println("<form action='Notice_Reply_Delete' method='get'>");
					out.println("<input type='submit' value='삭제'>");
					out.println("<input type='hidden' name='reply_id' value='"+reply_id+"'>");
					out.println("<input type='hidden' name='reply_user_id' value='"+reply_user_id+"'>");
					out.println("<input type='hidden' name='reply_text' value='"+reply_text+"'>");
					
					out.println("<input type='hidden' name='ori_id' value='"+ori_id+"'>");
					out.println("<input type='hidden' name='user_id' value='"+user_id+"'>");
					out.println("<input type='hidden' name='password' value='"+password+"'>");
					out.println("<input type='hidden' name='title' value='"+title+"'>");
					out.println("<input type='hidden' name='text' value='"+text+"'>");
					out.println("</form></td></tr>");
				}
				out.println("</Table>");
				out.println("<br>");
			}
		}
	 %>

</body>
</html>