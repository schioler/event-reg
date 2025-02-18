<!DOCTYPE html>
<%@page import="dk.schioler.event.web.controller.AbstractController"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
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
<!-- <link rel="stylesheet" href="public/styles/cols.css"> -->
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">
		
		<%@ include file="include/menu.jsp"%>
		<%@ include file="include/status.jsp"%>
      
		<p>
		<fmt:message key="favorites.title" />
		</p>

		<div >
			<c:choose>
				<c:when test="${not empty sesFavorites}">
					<div class="container-5-col-head">
						<div>&nbsp;</div>
						<div>					
						   <span class="tmpl-text-head">
							<fmt:message key="event-template.name"/>					   
						   </span>						   
						</div>
						<div style="text-align: right">
						   <span class="tmpl-text-head">
						   <fmt:message key="event-template.dose" />
						   </span>    
						</div>
						<div>
						   <span class="tmpl-text-head" ">
							<fmt:message key="event-template.unit" />
						   </span> 
						</div>
						<div>
						   <span class="tmpl-text-head">  
                     <fmt:message key="event-template.description" />
                     </span>  
                  </div>
					</div>

					<c:forEach var="eventTmpl" items="${sesFavorites }">
					 <form action="favorite-save.do" method="post">
						<div class="container-5-col">
							<div>
							  <input class="tmpl-text-btn" type="submit" value='<fmt:message key="save"/>' >
							</div>
							<div>
							  <input name="login-id"  type="hidden"  value="${eventTmpl.loginId}">
							  <input name="event-template-id"  type="hidden"  value="${eventTmpl.id}">
							  <input name="short-name" type="hidden"  value="${eventTmpl.shortName}">
							  <input  name="name" type="text"  value="${eventTmpl.name}">							
							</div>
							<div >
							  <input  style="text-align: right" name="dose" type="text" value="${eventTmpl.dose}">								
							</div>
							<div>
					    		<input  name="unit" type="text"  value="${eventTmpl.unit}">								
					 		</div>
					 		<div>
                        <input  name="description" type="text"  value="${eventTmpl.description}">                        
                     </div>					 		
						</div>
					 </form>
					</c:forEach> 
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