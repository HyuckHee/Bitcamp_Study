<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">


	.selector_hour_info img{
		position: relative; top:0; left: 0;
		display: inline;
		width: 60%;
		height: auto;
		float: left;
	}

</style>

<% 
	String body_page = request.getParameter("page");
	if(body_page ==null){
		body_page = "Main_body.jsp";
	}
	

%>

</head>
<body>
	<div>
		<div style="width:100%; height:auto;">
			<jsp:include page='top_bar.jsp'/>
		</div>
		<div style="width:100%; height:auto;">
			<a href="MAIN_PAGE.jsp"><img src="img/main_top.jpg" style="margin-top:40px; margin-left: 20%; margin-right: 20%"></a>
		</div>	
		<div style="width:100%; height:auto;">
			<jsp:include page="Menu_bar.jsp"/>
		</div>	
	</div>

	<div style="margin-top: 100px; margin-bottom: 100px;"> <jsp:include page='<%=body_page%>'/> </div>

	<div>
		<div style="width:60%; margin: 0 auto; ">
			<img style="max-width: 100%; height:auto;" src="img/main_hour_info.jpg"></div>
	</div>
	
	<div>
		<div style="width:60%; margin: 0 auto; ">
			<img style="max-width: 100%; height:auto;" src="img/main_bottom_line.jpg"></div>
	</div>
	
	<div>
		<div style="width:60%; margin: 0 auto; ">
			<img style="max-width: 100%; height:auto;" src="img/main_bottom_info.jpg"></div>
	</div>
	
	
</body>
</html>