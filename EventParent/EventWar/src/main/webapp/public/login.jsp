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
			<p>Login</p>
			<form action="user-authenticate.do" method="post">
				<table>
					<tr>
						<td>Username/Email:</td>
						<td><input type="text" name="username"></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td><input type="submit" name="submit"></td>
						</form>
						<td>
							<form action="show-forgot-password.do">
								<input type="submit" name="new-password" value="New-password" method="GET">
							</form>
						</td>

					</tr>

				</table>
				<br>
		</div>

	</fmt:bundle>
</body>
</html>