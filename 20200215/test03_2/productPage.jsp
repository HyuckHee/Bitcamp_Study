<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${contextPath}/prod_/addProd.do">
		<h1 style="text-align: center">제품정보</h1>
		<table align="center">
			<tr>
				<td width="200"><p align="right">제품ID</td>
				<td width="400"><input type="text" name="prod_id"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">제품명</td>
				<td width="400"><input type="text" name="prod_name"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">제품가격</td>
				<td width="400"><p>
						<input type="text" name="prod_price"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">제품설명</td>
				<td width="400"><p>
						<input type="text" name="prod_desc"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">공급업체ID</td>
				<td width="400"><p>
						<input type="text" name="vend_id"></td>
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