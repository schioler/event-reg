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
   <DIV class="body-col-5-90-5">
        <DIV>&nbsp;</DIV>
        <DIV>
            <div class="body-row-100">
               <div class="body-row-div">
                  <DIV class="center-col-100">                  
                    <DIV >
                        <%@ include file="include/menu.jsp"%>
                    </DIV>
                  </DIV>
                </div>
               <div class="body-row-div">
                  <DIV class="center-col-100">                  
                    <DIV >
                    <h2>	<fmt:message key="favorites.title" /></h2>
                    </DIV>
                  </DIV>
               </div>
               <div class="body-row-div">
                  <DIV class="center-col-100">                  
                    <DIV >
                    
		                <c:choose>
                        <c:when test="${not empty sesFavorites}">          
                              <div class="center-col-10-30-10-15-15-30-header">
		                              <div>&nbsp;</div>
		                              <div><fmt:message key="event-template.name"/></div>
		                              <div ><fmt:message key="event-template.dose" /> </div>
		                              <div> <fmt:message key="event-template.unit" /> </div>
		                              <div> <fmt:message key="event.date" /> </div>
		                              <div><fmt:message key="event.time" /> </div>
                              </div>                              
                     
	                           <c:forEach var="event" items="${sesFavorites }">
   	                           <form action="favorite-save.do" method="post">
                              <div class="center-col-10-30-10-15-15-30"> 
		                           <div style="text-align: center">
		                             <input type="submit" value='<fmt:message key="save"/>' >
		                           </div>
		                           <div>
					                       <input type="hidden" name="login-id" value="${event.loginId}">
					                       <input type="hidden" name="event-template-id" value="${event.parentId}">
					                       <label>${event.name}</label>
		                           </div>
		                            <div ><label>${event.dose}</label></div>  
		                            <div> <label>${event.unit}</label> </div>
		                            <div><label>${event.eventTSDate}</label></div>
		                            <div><label>${event.eventTSTime}</label> </div>
	                            </div>                     
	                            </form>
              	               </c:forEach>
	 
                        </c:when>
                        <c:otherwise><p>No Favourites</p></c:otherwise>
                     </c:choose>                     
                     </DIV>               
		            </DIV>
		          </div>
                <div class="row-body-div"> 
                    <DIV class="center-area-100">                  
                        <DIV >
                     <%@ include file="include/footer.jsp"%>
                     </DIV>               
                     </DIV>    
                </div>	
            </div>
        </DIV> 
        <DIV>&nbsp;</DIV>
   </DIV> 
</body>
</fmt:bundle>
</html>  