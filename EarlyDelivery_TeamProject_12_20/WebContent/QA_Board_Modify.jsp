<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pack.DBManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String ori_id = (String)request.getParameter("ori_id");

	DBManager db = new DBManager();
	db.DBConnection("ED");
	db.DBTable_Select_Id(request, "ED", "QA_Board", ori_id);
%>


<script type="text/javascript">

	function input_check(){
		var pw =  modify_form.password.value;
		var title = modify_form.title.value;
		var text = modify_form.text.value;
		
		if(pw.length > 5 && pw.length <= 12 && pw.length != 0){
			if(title.length <= 30 && title.length != 0){
				if(text.length <= 1000 && text.length != 0){
					var check = confirm("정말로 게시물을 수정할까요?");
					
					if(check == true){
						return true;
					} else{
						return false;
					}
				}else{
					alert("내용은 1000자 이내로 입력해주세요");
					return false;
				}
			} else {
				alert("제목은 30자 이내로 입력해주세요");
				return false;
			}		
		} else{
			alert("비밀번호는 5~12자 이내로 입력해주세요");
			return false;
		}
	}	
	
	function cancle_conform(){
		var check = confirm("정말로 게시물 수정을 취소할까요?");
		
		if(check == true){
			return true;
		} else{
			return false;
		}
	}
	

</script>
</head>
<body>
	<font size="5"><p align="Center">게시글 수정</p></font>
	
	
	 <form name="modify_form" action="QA_Modify" method="get" onsubmit="return input_check()">
	 	<table bgcolor="#fff0f0" style="margin-left: auto; margin-right: auto;">
	 		<tr>
	 			<td width="100"><font size="2">아이디</font></td>
	 			<td width="400"><font size="2"><%=request.getAttribute("user_id") %></font></td>
	 		</tr>
	 		<tr>	
	 			<td width="100"><font size="2">비밀번호</font></td>
	 			<td><input type="password" name="password" size="55" placeholder="게시글 비밀번호를 입력해주세요(5~12자 이내)"></td>
	 		</tr>
	 		<tr>
	 			<td width="100"><font size="2">제목</font></td>
	    		<td width="400"><input type="text" name="title" size="55" value='<%=request.getAttribute("title")%>'></td>
	    	</tr>
	 	</table>
	 
    	<p align="Center">
    		<textarea name="text" cols="70" rows="20" ><%=request.getAttribute("text")%></textarea>
    	</p>
     	<p align="Center">
     		<a href="QA_Board.jsp"><input type="button" name = "cancle" value="취소" onclick="return cancle_conform()"></a>
    	 	<input type="submit" name = "modify" value="수정">
    	 </p>
    	 <input type="hidden" name = "ori_id" value='<%=request.getParameter("ori_id")%>'>
    </form>
</body>
</html>