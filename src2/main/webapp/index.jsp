<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>Testing</h1>
		<h1>Hello JSP and Servlet!</h1>
<!-- Create a form with the action attribute to specific where to send the form-data when
the form is submitted, method attribute to specific the method used (GET, POST, PUT, DELETE,
Etc.) -->
<form action="HelloServlet" method="post">
 Enter your name: <input type="text" name="yourName" size="20">
 <!-- Implement submit button with type = submit to perform the request when clicked -->
 <input type="submit" value="Call Servlet" />
</form>
		
</body>
</html>