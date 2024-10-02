
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>
<html>
<fmt:bundle
   basename="dk.schioler.event.base.resources.EventListResources">

<head>
<title><fmt:message key="event-type.entity" /></title>
<link rel="stylesheet" href="styles/cols.css">
<link rel="stylesheet" href="styles/event.css">
</head>
<body>

    

	<div class="menu-div">
		<%@ include file="include/menu.jsp"%>
	</div>
	<div class="content-div">
	
	<p><fmt:message key="event-type.entity" /> <b>
   <fmt:message key="delete" />
   </b></p>
   
		<br>
		<form action="./event-type-delete.do" method="post">
			<%@ include file="include/event-type.jsp"%>
		</form>
		<br>
	</div>
</body>
</fmt:bundle>
</html>