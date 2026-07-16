<!DOCTYPE html>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dk.schioler.event.base.entity.EventCategory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
		<head>
<%@ include file="include/common-head.jsp"%>
<title><fmt:message key="event-update" /></title>
		</head>
		<body>
				<%@ include file="include/part-top.jsp"%>
				<div class="row">
						<form action="./event-type.select.do" method="post">
								<%@ include file="gui-related/select-event-type.jsp"%>
						</form>
				</div>
				<%@ include file="include/part-bottom.jsp"%>
		</body>
</fmt:bundle>
</html>
<!-- 				<div class="footer-div"> -->
<%-- 						<%@ include file="include/event-template-list.jsp"%> --%>
<!-- 				</div> -->
<!-- 				<div class="footer-div"> -->
<%-- 						<c:choose> --%>
<%-- 								<c:when test="${not empty sesEventQueue}"> --%>
<%-- 										<%@ include file="include/event-queue.jsp"%> --%>
<%-- 	        <%@ include file="include/event-queue.jsp"%> --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<!-- 										<p>no queue</p> -->
<%-- 								</c:otherwise> --%>
<%-- 						</c:choose> --%>
<!-- 				</div> -->
<%-- <div>
						<c:choose>
								<c:when test="${not empty sesEventsInserted }">
										<ul>
												<c:forEach var="event" items="${sesEventsInserted }">
														<out> ${event}</out>
														<br>
												</c:forEach>
										</ul>
								</c:when>
						</c:choose>
				</div> --%>