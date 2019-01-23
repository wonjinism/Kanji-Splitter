<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form id="registerAction" action="insertUser" method="post">
		id:<input type="text" name="user_id" value="${user.user_id}" /><font color="red">${warning}</font><br />
		pw:<input type="password" name="user_password" />
		<input type="submit" value="Register" />
	</form>
</body>
</html>