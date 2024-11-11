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
	<title><fmt:message key="favourite.title" /></title>
</fmt:bundle>
<link rel="stylesheet" href="styles/cols.css">
<link rel="stylesheet" href="styles/event.css">
</head>
<body>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">

	<div class="menu-div">
		<%@ include file="include/menu.jsp"%>
	</div>
	
   <h3><fmt:message key="event.menu" /></h3>
	  
	<div class="content-div">
		<table>
         <tr>
         <td></td>
	     </tr>
	     <tr>
        
         	  <c:choose>
                 <c:when test="${not empty sesFavourites}"> 
                    <c:forEach var="eventTmpl" items="${sesFavourites }">
                     <td>                
                        <out> ${eventImpl}</out>
                  
                  </td>
                       </c:forEach>
                    
                 </c:when>
                 <c:otherwise>
                     <p>No Favourites</p>
                 </c:otherwise>
              </c:choose>
		
         </tr>
		</table>
		<br>
	
	</div>
	
	<div class="footer-div">
 	 
	</div>
	<div>
		

	</div>

</fmt:bundle>
</body>
</html>