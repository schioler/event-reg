<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>

<title>EventTmplList</title>
<link rel="stylesheet" href="public/styles/cols.css">
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
<fmt:bundle
   basename="dk.schioler.event.base.resources.EventListResources">
	<div class="menu-div">	
		<%@ include file="include/menu.jsp"%>
	</div>
	<div class="content-div">
      <h3>Event Tmpl List</h3>
                     
						<form action="./event-tmpl-type-select.do" method="post">
				            <%@ include file="include/event-type-select.jsp"%>
						</form>               

<hr>
      <c:if test="${sesEventTemplates != null}">
         <table>
            <tr>
               <th class="event-col-1">&nbsp;</th>
               <th class="event-col-2">Name</th>
               <th class="event-col-3">Short Name</th>
               <th class="event-col-4">Dose</th>
               <th class="event-col-5">Unit</th>
               <th class="event-col-6">Description</th>
               <th class="event-col-7">&nbsp;</th>
            </tr>

        
            <c:forEach var="eventTemplate" items="${sesEventTemplates}">
               <tr>
                  <form method="post" action="./event-tmpl-delete-show.do">
                     <td class="event-col-1">
                        <input type="hidden" name="id" value="${eventTemplate.id}"> 
                        <input type="hidden" name="eventTypeId" value="${eventTemplate.eventTypeId}">
                        <input class="event-btn" type="submit" formaction="./event-tmpl-update-show.do" value="Update"> 
                        <input type="submit" value="Delete">
                     </td>
                     <td class="event-col-2">
                       <input class="event-cnt-2" type="text" name="name" value="${eventTemplate.name}">
                     </td>
                     <td class="event-col-3">
                        <input class="event-cnt-3" type="text" name="shortName"  value="${eventTemplate.shortName}"> 
                     </td>
                     <td class="event-col-4">
                       <input class="event-cnt-4" type="text" name="dose" value="${eventTemplate.dose}">
                      </td>
                     <td class="event-col-5">
                         <input class="event-cnt-5" type="text" name="unit" value="${eventTemplate.unit}">
                     </td>
                     <td class="event-col-6">
                          <input class="event-cnt-6" type="text" name="description" value="${eventTemplate.description }">
                     </td>
                     <td class="event-col-7">
                         <input  type="time" name="time">
                         <input  type="date" name="date">
                     </td>
               
                  </form>
               </tr>
            </c:forEach>
               <tr>
                <td>
                <form action="event-tmpl-create-show.do" method="post">
                     <input type="hidden"  name="eventTypeId" value="${sesSelectedEventTypeId}">
                      <input type="submit" value="New">
                </form>   
                </td>
                <td colspan="6">
                  &nbsp;
                </td>
                </tr>
         </table>
      </c:if>



	</div>

</fmt:bundle>
</body>
</html>