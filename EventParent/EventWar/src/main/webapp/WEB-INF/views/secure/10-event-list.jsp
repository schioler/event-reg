<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
		<head>
<%@ include file="include/common-head.jsp"%>
<title><fmt:message key="event-type.entity-list" /></title>
		</head>
		<body>
				<%@ include file="include/part-top.jsp"%>
				<div class="row">
						<h2>
								<fmt:message key="event.list" />
						</h2>
				</div>
				<div class="row">
						<div class="col">Created</div>
						<div>
								<fmt:message key="event.name" />
						</div>
						<div class="col">
								<fmt:message key="event.shortname" />
						</div>
						<div class="col" style="text-align: right">
								<fmt:message key="event.dose" />
						</div>
						<div class="col">
								<fmt:message key="event.unit" />
						</div>
						<div class="col">
								<fmt:message key="event.date" />
						</div>
						<div class="col">
								<fmt:message key="event.time" />
						</div>
				</div>
				<c:forEach var="event" items="${sesEvents}">
						<div class="row">
								<div class="col">${event.createdDate}</div>
								<div class="col">${event.name}</div>
								<div class="col">${event.shortName}</div>
								<div class="col" style="text-align: right">${event.dose}</div>
								<div class="col">${event.unit}</div>
								<div class="col">${event.eventTSDate }</div>
								<div class="col">${event.eventTSTime }</div>
						</div>
				</c:forEach>
				<%@ include file="include/part-bottom.jsp"%>
		</body>
</fmt:bundle>
</html>