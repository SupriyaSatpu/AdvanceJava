<%@page import="pojos.UserDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		UserDetails userDetails = new UserDetails(request.getParameter("em"), request.getParameter("pass"));
		//in JSP inbuild support session
		session.setAttribute("User_details", userDetails);	
		response.sendRedirect("display.jsp");
		
	%>
	

</body>
</html>