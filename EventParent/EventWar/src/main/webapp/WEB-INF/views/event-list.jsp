<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page session="true"%>
<html>
   <fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">
<head>
      <%@ include file="include/common-head.jsp"%>
      <title><fmt:message key="event-type.entity-list" /></title>
</head>
<body>
   <DIV class="body-col-5-90-5">
        <DIV>&nbsp;</DIV>
        <DIV>
            <div class="body-row-100">
               <div class="row-body-div">
                  <DIV class="center-col-100">                  
                    <DIV >
                       <%@ include file="include/menu.jsp"%>
                    </DIV>
                  </DIV>
                </div>
               <div class="row-body-div">
                  <DIV class="center-col-100">                  
                  <DIV >
                       <h2><fmt:message key="event.list" /></h2>
                  </DIV>
                  </DIV>
               </div>
               <div class="row-body-div">
                  <DIV class="center-col-100">                  
                  <DIV >     
                     
				      	<div class="event-cols-header">
				      		<div>Created</div>   
								<div><fmt:message key="event.name" /></div>
								<div><fmt:message key="event.shortname" /></div>
								
								<div style="text-align:right"><fmt:message key="event.dose" /></div>
								<div><fmt:message key="event.unit" /></div>
  								<div><fmt:message key="event.date" /></div>
  						      <div><fmt:message key="event.time" /></div>
						      <!-- <div>Best</div>
								<div>&nbsp;</div> -->
							</div>
				        <c:forEach var="event" items="${sesEvents}">
                        <div class="event-cols">
                           <div>${event.createdDate} </div>
                           <div>${event.name}</div>
                           <div>${event.shortName}</div>
                           
                           <div style="text-align:right">${event.dose}</div>
                           <div>${event.unit}</div>
                           <div>${event.eventTSDate }</div>
                           <div>${event.eventTSTime }</div>
                           
                        </div>                        
                     </c:forEach>				       
						
                   </DIV>               
                   </DIV>
                
                </div>
			      <div class="row-body-div"> 
                    <DIV class="center-col-100">                  
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