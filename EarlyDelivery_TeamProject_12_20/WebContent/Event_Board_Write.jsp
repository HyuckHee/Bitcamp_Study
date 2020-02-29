<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<script type="text/javascript">
	function input_check(){
		var title = write_form.title.value;
		var text = write_form.text.value;
		

		if(title.length <= 30 && title.length != 0){
			if(text.length <= 1000 && text.length != 0){
				return true;
			}else{
				alert("내용은 1000자 이내로 입력해주세요");
				return false;
			}
		} else {
			alert("제목은 30자 이내로 입력해주세요");
			return false;
		}		
	}	
	
	function really(){
		var check = confirm("정말로 게시물 작성을 취소할까요?");
		if(check == true){
			return true;
		} else{
			return false;
		}
	}
	

</script>
</head>
<body>
	<font size="5"><p align="Center">게시판 쓰기</p></font>
	
	
	 <form name="write_form" action="Event_Write" method="get" onsubmit="return input_check()">
	 	<table bgcolor="#fff0f0" style="margin-left: auto; margin-right: auto;">
	 		<tr>
	 			<td width="100"><font size="2">아이디</font></td>
	 			<td width="400"><font size="2"><%=session.getAttribute("user_id") %></font></td>
	 		</tr>
	 		<tr>
	 			<td width="100"><font size="2">제목</font></td>
	    		<td width="400"><input type="text" name="title" size="55" placeholder="제목을 입력해주세요(30자 이내)"></td>
	    	</tr>
	 	</table>
	 
    	<p align="Center">
    		<textarea name="text" cols="70" rows="20" placeholder="내용을 입력해주세요(1000자 이내)"></textarea>
    	</p>
     	<p align="Center">
     		<a href="Event_Board.jsp"><input type="button" name = "cancle" value="취소" onclick="return really()"></a>
    	 	<input type="submit" name = "write" value="등록">
    	 </p>
    </form>
</body>
</html>