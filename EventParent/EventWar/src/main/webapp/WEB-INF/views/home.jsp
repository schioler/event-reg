<!DOCTYPE html>
<%@page import="dk.schioler.event.base.entity.EventType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page session="true"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="dk.schioler.event.base.entity.EventTemplate"%>
<%@ page import="dk.schioler.event.base.entity.EventType"%>
<%@ page import="dk.schioler.event.web.controller.AbstractController"%>
<%@ page import="dk.schioler.shared.timeline.TIMESLOT_LENGTH"%>

<html>
<head>
<title>Search</title>
<link rel="stylesheet" href="styles/cols.css">
<link rel="stylesheet" href="styles/event.css">
</head>
<body>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">

	<div >
		<%@ include file="include/menu.jsp"%>
	</div>
	<div class="content-div">
	  <h2>Favourites</h2>
	  <!-- 5 cells -->
		<table>
		 	<tr>
					<td >

					</td>
			</tr>
					<td> </td>
				</tr>
			</form>
			<tr>
				<td ></td>

		</table>
		<br>
		<br>
		<hr>
	</div>
</fmt:bundle>
</body>
</html>
