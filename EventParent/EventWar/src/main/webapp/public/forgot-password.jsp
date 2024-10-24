<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page session="true"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EventType</title>
<link rel="stylesheet" href="styles/cols.css">
<link rel="stylesheet" href="styles/event.css">
</head>
<body>
<fmt:bundle
   basename="dk.schioler.event.base.resources.EventListResources">
	
	<div class="content-div">
		<br>
		<p>Set New Password</p>
		<form action="set-new-password.do" method="post">
         <table>
         <tr>
            <td>Username/Email:</td>
            <td><input type="text" name="username"></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type="password" name="password1"></td>
         </tr>
         <tr>
            <td>repeat Password:</td>
            <td><input type="password" name="password2"></td>
         </tr>
         <tr>
            <td colspan="2" >
            <input type="submit" name="submit">
            </td>
         </tr>
         
         
         </table>
		</form>
		<br>
	</div>

</fmt:bundle>
</body>     
</html>