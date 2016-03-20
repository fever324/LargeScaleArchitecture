<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Session Controller</title>
</head>
<body>
<center>
<table width="100%" border="1" align="center">
<tr>
	<td>Session ID </td>
	<td>${sessionID}</td>
</tr>
<tr>
	<td>Version Number </td>
	<td>${sessionVersion}</td>
</tr>
<tr>
	<td>Message </td>
	<td>${sessionMessage}</td>
</tr>
<tr>
	<td>Expiration  </td>
	<td>${sessinoExpirationDate}</td>
</tr>
</table>
</center>

<form method="POST" action="Controller">

<input type="text"   name="input"/> 
<input type="submit" name="Replace" value="Replace" />
<input type="submit" name="Refresh" value="Refresh" />
<input type="submit" name="Logout" value="Logout" />
</form>



</body>
</html>