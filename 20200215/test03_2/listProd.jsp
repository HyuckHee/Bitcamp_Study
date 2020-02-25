<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,sec03.ex02.*"
	isELIgnored="false"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
</head>
<c:choose>
	<c:when test='${msg=="addMember"}'>
		<script type="text/javascript">
			window.onload = function() {
				alert("회원을 등록했습니다.");
			}
		</script>
	</c:when>
	<c:when test='${msg=="modified"}'>
		<script type="text/javascript">
			window.onload = function() {
				alert("회원 정보를 수정했습니다.");
			}
		</script>
	</c:when>
	<c:when test='${msg=="deleted"}'>
		<script type="text/javascript">
			window.onload = function() {
				alert('회원 정보를 삭제했습니다.');
			}
		</script>
	</c:when>
</c:choose>
<body>

	<p class="cls1">회원정보</p>
	<table align="center" border="1">
		<tr align="center" bgcolor="lightgreen">
			<td width="7%"><b>제품ID</b></td>
			<td width="7%"><b>제품명</b></td>
			<td width="7%"><b>제품가격</b></td>
			<td width="7%"><b>제품설명</b></td>
			<td width="7%"><b>공급업체명</b></td>
			<td width="7%"><b>삭제</b></td>
			<td width="7%"><b>수정</b></td>
		</tr>
		<c:choose>
			<c:when test="${ membersList==null}">
				<tr>
					<td colspan=5>pro17/test01/listMembers.jsp 12
					<td colspan=5><b>등록된 회원이 없습니다.</b></td>
				</tr>
			</c:when>
			<c:when test="${membersList != null }">
				<c:forEach var="mem" items="${membersList }">
					<tr align="center">
						<td>${mem.prod_id }</td>
						<td>${mem.prod_name }</td>
						<td>${mem.prod_price}</td>
						<td>${mem.prod_desc }</td>
						<td>${mem.vend_name }</td>
						<td><a
							href="${contextPath}/prod_/modprod.do?id=${mem.prod_id}">수정</a></td>
						<td><a
							href="${contextPath}/prod_/delprod.do?id=${mem.prod_id}">삭제</a></td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<a href="${contextPath}/prod_/productForm.do"><p class="cls2">제품등록</p></a>
	 <form method="get" action="${contextPath }/prod_/searchProd.do">
	<input type="text" name="id_search">&nbsp
	<input type="submit" value="검색">
	<a align="left"> &nbsp검색할 아이디 &nbsp</a>
	<a href="${contextPath }/prod_/prodslist.do">전체목록보기</a>
	</form>
</body>
</html>