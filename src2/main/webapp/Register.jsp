<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="RegisterServlet" method="Post">
	Name : <input type="text" name="userName"><br>
	Password : <input type="password" name="password"><br>
	Email : <input type="text" name="email"><br>
	Language : <select name="language">
	<option>English</option>
	<option>Spanish</option>
	<option>French</option>
	</select>
	<input type="submit" value = "Call Servlet"/>
</form>

</body>
</html>