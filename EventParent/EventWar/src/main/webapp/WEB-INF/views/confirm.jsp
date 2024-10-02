<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page session="true"%>
<%@ page import="java.util.List"%>
<%@ page import="dk.schioler.event.base.entity.EventType"%>
<html>
<head>
<title>Confirm</title>
<link rel="stylesheet" href="styles/event.css">
</head>
<body>
	<h1>Congrats - you have saved the following event:</h1>
	<form action="./" method="get">
		<ul>
			<c:forEach var="event" items="${sesEventsInserted }">
				<out> ${event}</out><br>
			</c:forEach>




		</ul>
		<input type="submit" value="Home" />
	</form>

</body>
</html>