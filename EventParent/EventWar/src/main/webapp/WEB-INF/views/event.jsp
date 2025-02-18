<!DOCTYPE html>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dk.schioler.event.base.entity.EventType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>
<fmt:bundle
	basename="dk.schioler.event.base.resources.EventListResources">
	<title><fmt:message key="home.title" /></title>
</fmt:bundle>
<link rel="stylesheet" href="public/styles/cols.css">
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
<fmt:bundle
   basename="dk.schioler.event.base.resources.EventListResources">

	<div class="menu-div">
		<%@ include file="include/menu.jsp"%>
	</div>
	<fmt:bundle	basename="dk.schioler.event.base.resources.EventListResources">
   <h3><fmt:message key="event.menu" /></h3>
	</fmt:bundle>


	<div class="content-div">
		<table>
         <tr>
         <td>
		<form action="./event-type-select.do" method="post">
			<%@ include file="include/event-type-select.jsp"%>
		</form>
		</td>
		
      </tr>
		</table>
		<br>
	
	</div>
	<div class="footer-div">
		<%@ include file="include/eventTemplateList.jsp"%>
	</div>
	<div class="footer-div">
 	  <c:choose>
	     <c:when test="${not empty sesEventQueue}"> 
				<%-- <%@ include file="include/event-queue.jsp"% --%>>
	        <%@ include file="include/event-queue.jsp"%>
	     
	     </c:when>
	     <c:otherwise>
				<p>no queue</p>
	     </c:otherwise>
	  </c:choose> 
	</div>
	<div>
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

	</div>

</fmt:bundle>
</body>
</html>