<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<p>Send LoginID</p>
			<form action="send-login-id.do" method="post">
				<table>
					<tr>
						<td>Login Id</td>
						<td><input type="text" name="login-id"></td>
					</tr>
					<% String riddle = null; %>
					<tr>
						<td><%= riddle%></td>
						<td><input type="text" name="riddle-answer"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" name="submit">
						</td>
					</tr>


				</table>
			</form>
			<br>
		</div>

	</fmt:bundle>
</body>
</html>