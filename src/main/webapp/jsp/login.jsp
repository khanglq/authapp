<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="${contextPath}/login" method="post">
<table>
	<tr>
	 <td>UserName :</td>
	 <td><input id="username" name="username" type="text" /></td>
	</tr>
	<tr>
	 <td>password :</td>
	 <td><input id="password" name="password" type="password" /></td>
	</tr>
</table>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

	<input  type="submit" value="Login"/>
</form>
</body>
</html>