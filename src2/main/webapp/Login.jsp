<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<h1>Login Page</h1>
	<form action="LoginServlet" method = "post">
	Enter your username: <input type= "text" name="uname" size= "20"><br>
	Enter your password: <input type= "password" name= "pass" size="20">
	<input type= "submit" value="Login!"/>
	</form>

</body>
</html>