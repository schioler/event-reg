<!DOCTYPE html>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Formatter"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="dk.schioler.event.web.controller.AbstractController"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Locale"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">
<head>
      <%@ include file="include/common-head.jsp"%>
      <title><fmt:message key="favorites.title" /></title>
</head>

 <body >
 <div class="col-body">
    <div class="content">
<!--       <div>&nbsp;</div> -->
   	   <%@ include file="include/menu.jsp"%> 
       <div> <!-- one-col-container item -->       
				<h2>
      				<fmt:message key="favorites.title" />
				</h2>
       </div>
       <div > <!-- one-col-container item -->        
			<c:choose>
				<c:when test="${not empty sesFavorites}">
		
			<div class="container-6-col-head">
				<div>&nbsp;</div>
				<div>					
<!-- 						   <span class="tmpl-text-head"> -->
							<fmt:message key="event-template.name"/>					   
<!-- 						   </span> -->						   
				</div>
				<div >
<!-- 						   <span class="tmpl-text-head"> -->
						   <fmt:message key="event-template.dose" />
<!-- 						   </span> -->    
				</div>
				<div>
<!-- 						   <span class="tmpl-text-head" "> -->
							<fmt:message key="event-template.unit" />
<!-- 						   </span> --> 
				</div>
				<div>
<!-- 						   <span class="tmpl-text-head"> -->  
                     <fmt:message key="event.date" />
	         </div>
	         <div>
                     <fmt:message key="event.time" />
<!--                      </span> -->  
            </div>
			</div> <!-- EO "container-5-col-head" -->

					<c:forEach var="event" items="${sesFavorites }">
					 <form action="favorite-save.do" method="post">
			<div class="container-6-col"> <!-- "container-5-col" -->
				<div style="text-align: center"><!-- "container-5-col" item-->
				     <input type="submit" value='<fmt:message key="save"/>' >
				</div>
				<div><!-- "container-5-col" item-->
							  <input type="hidden" name="login-id" value="${event.loginId}">
      					  <input type="hidden" name="event-template-id" value="${event.parentId}">
                       <label>${event.name}</label>
				</div>
				<div > <!-- "container-5-col" item--> 							  
                        <label>${event.dose}</label>
				</div>  
				<div> <!-- "container-5-col" item-->
					    	  <label>${event.unit}</label>
		 		</div>
		 		<div> <!-- "container-5-col" item-->
                       
                       <label>${event.eventDate}</label>
            </div>
            <div>
                        <label>${event.eventTime}</label>
 
            </div>					 		
			</div><!-- EO "container-5-col" -->
					 </form>
					</c:forEach> 
				</c:when>

				<c:otherwise>
					<p>No Favourites</p>
				</c:otherwise>
			</c:choose>
			<br>

	   </div>
      
	   <div><!-- one-col-container item -->	     
	     <%@ include file="include/footer.jsp"%>
	   </div>	
   </div> <!-- EO one-col-container -->
</div> <!-- EO body -->
</body>
</fmt:bundle>
</html>