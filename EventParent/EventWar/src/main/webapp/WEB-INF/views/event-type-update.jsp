<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page session="true"%>
<html>
<head>
<title>EventType - Update</title>
<!-- <link rel="stylesheet" href="public/styles/cols.css"> -->
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
<fmt:bundle
   basename="dk.schioler.event.base.resources.EventListResources">

	<div class="menu-div">
		<%@ include file="include/menu.jsp"%>
	</div>

	<!-- <div class="content-div"> -->
   <p><fmt:message key="event-type.entity" />Update</p>
		<br>
		<form action="./event-type-update.do" method="post">
			<div class="container-2-col"> 
			<div>
		       Navn
		 	</div>
			<div>
			<input type="hidden" name="event-type-id" value="${sesEventType.id }">
			<input type="text" name="name" value="${sesEventType.name }">
			</div>
			
         <div>ShortName</div>
			<div>
			<input type="text" name="short-name" value="${sesEventType.shortName }">
			</div>

         <div>Desc</div>
         <div>
         <input type="text" name="description" value="${sesEventType.description }">
         </div>
         
     
         <div>&nbsp;</div>
         <div>
			<input type="submit" > 
         </div>
			</div>
		</form>
		<br>
	<!-- </div> -->

</fmt:bundle>
</body>
</html>