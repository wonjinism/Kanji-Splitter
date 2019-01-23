<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign in</title>
</head>
<body>
	<h1>로그인</h1>
	<form id="loginAction" action="loginUser" method="post">
		id:<input type="text" name="user_id" value="${user_id}"/><font color="red">${id_warning}</font><br />
		pw:<input type="password" name="user_password" /><font color="red">${pw_warning}</font><br />
		<input type="submit" value="Sign in"/>
	</form>
</body>
</html>