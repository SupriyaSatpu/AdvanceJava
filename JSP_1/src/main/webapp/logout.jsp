<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4> Hello, ${sessionScope.user_details.email}</h4>
	<%
		session.invalidate();
	%>
	<%--OR--%>
	<%--${pageContext.session.invalidate()}--%>
	<h5>
		<a href = 'index.jsp'>Visit Again</a>
	</h5>
</body>
</html>