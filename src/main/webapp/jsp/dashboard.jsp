<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>DashBoard accessed for both USER and ADMIN roles.</h2><br>
<h4>Your role is :${roles}</h4>
<a href="logout"> Logout</a>
<br>
-------------------------
<br>
<a href="userPage"> Click here for user role</a><br>

<a href="adminPage">Click here for admin role</a>

</body>
</html>