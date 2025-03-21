<!DOCTYPE html>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dk.schioler.event.base.entity.EventType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>
<fmt:bundle
	basename="dk.schioler.event.base.resources.EventListResources">
	<title><fmt:message key="favorites.title" /></title>
</fmt:bundle>
<link rel="stylesheet" href="public/styles/cols.css">
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">

		<div >
			<%@ include file="include/menu.jsp"%>
		</div>

		<h3>
			<fmt:message key="favorites.title" />
		</h3>

		<div >
			<c:choose>
				<c:when test="${not empty sesFavorites}">
					<table>
						<tr>
							<td><fmt:message key="event-template.name" /></td>
							<td><fmt:message key="event-template.dose" /></td>
							<td><fmt:message key="event-template.unit" /></td>
							<td>&nbsp;</td>
						</tr>
						<c:forEach var="eventTmpl" items="${sesFavorites }">
							<tr>
								<td><out> ${eventTmpl.name}</out></td>
								<td><out> ${eventTmpl.dose}</out></td>
								<td><out> ${eventTmpl.unit}</out></td>
								<td><out> <fmt:message key="save" /></out></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>

				<c:otherwise>
					<p>No Favourites</p>
				</c:otherwise>
			</c:choose>
			<br>

		</div>

		<div ></div>
		

	</fmt:bundle>
</body>
</html>