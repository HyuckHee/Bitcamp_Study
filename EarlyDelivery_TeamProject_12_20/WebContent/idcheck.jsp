<%@page import="java.sql.DriverManager"%>
<%@page import="pack.DB_Manager_user"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    <%
	    	request.setCharacterEncoding("UTF-8");
	    %>
		<%
			response.setCharacterEncoding("UTF-8");
		%>
		<%
			response.setContentType("UTF-8");
		%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
<%String insert_id = (String) request.getParameter("id");%>
function re_id(){
document.write('사용하실 아이디를 입력하세요.');
document.write('<form name="checkForm" method="post" action="idcheck.jsp">');
document.write('<input type="text" name="id">');
document.write('<input type="submit" value="ID중복확인"/></form>');
}
<%request.setCharacterEncoding("UTF-8");
			String f_id = (String) request.getParameter("id");
			int check = -1;
			DB_Manager_user.checknumber_id = 0;
			
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			String sq1 = "select * from user where id_DB='" + insert_id + "'";
			try {
				Class.forName("com.mysql.jdbc.Driver"); // jdbc 드라이버를 갖고온다.
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ED", "root", "fbemskfm");

				stmt = conn.createStatement();

				rs = stmt.executeQuery(sq1);
				if (rs.next()) {
					check = 1;
				} else {
					check = 0;
				}

			} catch (Exception e) {%>alert('오류'+e)<%} finally {
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException err) {%>alert('id check error');<%}
				if (rs != null)
					try {
						stmt.close();
					} catch (SQLException err) {%>alert('id check error');<%}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException err) {%>alert('id check error');<%}
			}%>	
			if(<%=check%> == 1){
				<%--DB_Manager.checknumber_id = 0;--%>
				document.write("'"+'<%=insert_id%>'+"'"+ '는 이미 있는 ID입니다.');
				document.write('<form name="checkForm" method="post" action="idcheck.jsp">');
				document.write('<input type="text" name="id">');
				document.write('<input type="submit" value="ID중복확인"/></form>');
				
			}
			else if(<%=insert_id.length()%> <5)
			{
				<%--DB_Manager.checknumber_id = 0;--%>
				document.write('ID를 6자리이상 입력해주세요.');
				document.write('<form name="checkForm" method="post" action="idcheck.jsp">');
				document.write('<input type="text" name="id">');
				document.write('<input type="submit" value="ID중복확인"/></form>');
				
			}else if(<%=insert_id.length()%> >13){
				<%--DB_Manager.checknumber_id = 0;--%>
				document.write('ID를 최대 12자리이하로 입력하세요.');
				document.write('<form name="checkForm" method="post" action="idcheck.jsp">');
				document.write('<input type="text" name="id">');
				document.write('<input type="submit" value="ID중복확인"/></form>');
				
			}
			else{
				<%--DB_Manager.checknumber_id = 1;--%>
				document.write("'"+'<%=insert_id%>'+"'"+'사용 가능한 ID입니다.');
				document.write("<input type='button' value='사용' onclick='setid()'>");
				document.write("<input type='button' value='재입력' onclick='re_id()'>");
				
			}

			function setid()
			{	
				opener.document.sign_form.id.value="<%=insert_id%>";
				opener.document.sign_form.hh.value=1;
				window.self.close();
			}
			
			
	
</script>
</head>
<body>

</body>
</html>