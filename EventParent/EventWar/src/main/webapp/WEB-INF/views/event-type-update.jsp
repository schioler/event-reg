
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page session="true"%>
<html>
<head>
<title>EventType</title>
<link rel="stylesheet" href="styles/cols.css">
<link rel="stylesheet" href="styles/event.css">
</head>
<body>
<fmt:bundle
   basename="dk.schioler.event.base.resources.EventListResources">
	<div class="menu-div">
		<%@ include file="include/menu.jsp"%>
	</div>
	<div class="content-div">
   <p><fmt:message key="event-type.entity" /></p>
		<br>
		<form action="./event-type-update.do" method="post">
			<input type="hidden" name="id" value="${sesEventType.id }">
				<%@ include file="include/event-type.jsp"%>
		</form>
		<br>
	</div>

</fmt:bundle>
</body>
</html>