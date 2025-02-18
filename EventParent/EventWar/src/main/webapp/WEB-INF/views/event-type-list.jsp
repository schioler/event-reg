<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page session="true"%>
<html>
<head>
<title>EventType</title>
<!-- <link rel="stylesheet" href="public/styles/cols.css"> -->
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">

	<%@ include file="include/menu.jsp"%>
	<%@ include file="include/status.jsp"%>
   
   <H3>
      <fmt:message key="event-type.entity-list" />
   </H3>
   
<!-- 	<div > -->
	  <br>
		<div class="container-6-col-head">
			<div>
				<form action="./event-type-create-show.do" method="post">
					<input class="header-text-btn" type="submit" name="new"
						value="Opret">
				</form>
			</div>
			<div>
				<span class="tmpl-text-head">Name </span>
			</div>
			<div>
				<span class="tmpl-text-head"> Shortname </span>
			</div>
			<div>
				<span class="tmpl-text-head">Description </span>
			</div>
			<div>
				<span class="tmpl-text-head">LoginId</span>
			</div>
		</div>


		<c:forEach var="type" items="${sesEventTypes}">
			<form action="./event-type-update-show.do" autocomplete="on"
				method="post">
				<div class="container-6-col">
					<div>
						<input type="hidden" name="id" value="${type.id}" /> <input
							class="tmpl-text-btn" type="submit" name="gem" value="Edit" /> <input
							class="tmpl-text-btn" type="submit" name="slet" value="Delete"
							formaction="./event-type-delete-show.do" />
					</div>
					<div>
						<input class="tmpl-text" type="text" value="${ type.name}">
					</div>
					<div>
						<input type="text" class="tmpl-text" value="${ type.shortName}">
					</div>
					<div>
						<input type="text" class="tmpl-text" value="${type.description }">
					</div>
					<div>
						<input type="text" class="tmpl-text" value="${type.loginId }">
					</div>
				</div>
			</form>
		</c:forEach>

		<div class="container-5-col">
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<div style="text-align: right;">&nbsp;</div>
		</div>

	</fmt:bundle>
</body>
</html>