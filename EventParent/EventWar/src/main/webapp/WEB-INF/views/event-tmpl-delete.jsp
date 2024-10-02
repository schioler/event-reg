<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>

<title>EventTemplate</title>
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
			<h3><fmt:message key="event-template.entity"></fmt:message></h3>
<hr color="blue">

			<form method="post" action="./event-tmpl-delete.do">
				<table>
					<tr>
						<td>
						   Name
						</td>
						<td>
						   <input type="hidden" name="id" value="${sesEventTemplate.id}">
						   <input type="hidden" name="eventTypeId" value="${sesEventTemplate.eventTypeId}">
						   <input type="text" name="name" value="${sesEventTemplate.name}">
						</td>
					</tr>
					<tr>
						<td>
						   Short Name
						</td>
						<td>
						   <input type="text" name="shortName"	value="${sesEventTemplate.shortName}">
						</td>
					</tr>
					<tr>
						<td>
						   Dose
						</td>
						<td>
						   <input type="text" name="dose" value="${sesEventTemplate.dose}">
						</td>
					</tr>
					<tr>
						<td>
						   Unit
						</td>
						<td>
						   <input type="text" name="unit" value="${sesEventTemplate.unit}">
						</td>
					</tr>
					<tr>
						<td>
						   Note
						</td>
						<td>
						   <input type="text" name="description" value="${sesEventTemplate.description }">
						</td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Delete"></td>
					</tr>
				</table>
			</form>

		</div>

	</fmt:bundle>
</body>
</html>