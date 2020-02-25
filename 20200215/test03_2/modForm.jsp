<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<style>
#cls {
	align-content: center;
}
#a{
	background-color: silver;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 class="cls1">회원정보 수정창</h1>
	<form method="post" action="${contextPath}/prod_/modsend.do">
		<table align="center">
			<tr>
				<td width="200"><p align="right">제품ID</td>
				<td width="400"><input type="text" id="a" name="prod_id"
					value="${memInfo.prod_id}" readonly></td>
			</tr>
			<tr>
				<td width="200"><p align="right">제품명</td>
				<td width="400"><input type="text" name="prod_name"
					value="${memInfo.prod_name}"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">제품가격</td>
				<td width="400"><p>
						<input type="text" name="prod_price"
							value="${memInfo.prod_price }"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">제품설명</td>
				<td width="400"><p>
						<input type="text" name="prod_desc" value="${memInfo.prod_desc }"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">공급업체ID</td>
				<td width="400"><p>
						<input type="text" id="a" name="vend_id" value="${memInfo.vend_id }" readonly></td>
			</tr>
			<tr>
				<td width="200"><p>&nbsp;</p></td>
				<td width="400"><input type="submit" value="추가입력"> <input
					type="reset" value="다시입력"></td>
			</tr>
		</table>
	</form>
</body>
</html>