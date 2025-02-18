<!DOCTYPE html>
<%@page import="dk.schioler.event.base.entity.EventTemplate"%>
<%@page import="java.util.List"%>
<%@page import="dk.schioler.event.web.entity.EventSearchCriteria"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>

<title>Choose tmpls</title>
<!-- <link rel="stylesheet" href="public/styles/cols.css"> -->
<link rel="stylesheet" href="public/styles/event.css">


</head>
<body>

   
	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">

		<div class="content-div">

			<h3>Templates chosen</h3>
			<!-- *********************************************** -->

			<table>
				<tr>
					<th>&nbsp;</th>
					<th >Name</th>
					<th>Short Name</th>
					<th>Dose</th>
					<th>Unit</th>
 					<th>Description</th>
					<th>&nbsp;</th>
				</tr>
				  <c:if	test="${sesSearchCriteria != null}">
				
						<c:forEach var="eventTemplate" items="${sesSearchCriteria.eventTemplates}">
						<form method="post" action="./search-remove-template.do">
							<tr>
									<td>
									 <input type="hidden" name="id" value="${eventTemplate.id}">  
										 <input type="submit" value="Fjern">
								   </td>
									<td>
								     	 <input type="text" name="name" value="${eventTemplate.name}">
									</td>
									<td>
									    <input type="text" name="shortName" value="${eventTemplate.shortName}">
									</td>
									<td>
									    <input type="text" name="dose" value="${eventTemplate.dose}">
									</td>
									<td>
									    <input type="text" name="unit" value="${eventTemplate.unit}">
									</td>
									<td>
									    <input type="text" name="description" value="${eventTemplate.description }">
									 </td>
									<td>&nbsp;</td>
	      				</tr>
						</form>
						</c:forEach>
				</c:if>
			</table>
			<!-- *********************************************** -->
			<hr>
			<br> <br>


			<form action="search-event-type-select.do" method="post">
				<select name="reqEventTypeId" id="reqEventTypeId"
					onchange="this.form.submit()">
					<c:choose>
						<c:when test="${empty sesSelectedEventTypeId }">
							<option selected value="---">Choose EventType</option>
							<c:forEach var="eventType" items="${sesEventTypes}">
								<option value="${eventType.id}">${eventType.name}</option>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach var="eventType" items="${sesEventTypes}">
								<c:choose>
									<c:when test="${eventType.id == sesSelectedEventTypeId }">
										<option selected value="${eventType.id}">${eventType.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${eventType.id}">${eventType.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</select>
			</form>
   
			<br>
			
			<c:if test="${sesEventTemplates != null}">
				<table>
					<tr>
						<th>&nbsp;</th>
						<th>Name</th>
						<th>Short Name</th>
						<th>Dose</th>
						<th>Unit</th>
						<th>Description</th>
						<th>&nbsp;</th>
					</tr>


					<c:forEach var="eventTemplate" items="${sesEventTemplates}">
							<form method="post" action="./search-add-template.do">
      						<tr>
								<td>
								    <input type="hidden" name="id" value="${eventTemplate.id}"> 
								    <input type="submit" value="Benyt">
								</td>
								<td>
								    <input type="text" name="name"value="${eventTemplate.name}">
								</td>
								<td>
								   <input type="text" name="shortName" value="${eventTemplate.shortName}">
								</td>
								<td>
								    <input type="text" name="dose" value="${eventTemplate.dose}">
								 </td>
								<td>
								    <input type="text" name="unit" value="${eventTemplate.unit}">
								 </td>
							    <td>
							        <input type="text"	name="description" value="${eventTemplate.description }">
							    </td>
							    <td>
							           <input type="time" name="time"> <input type="date" name="date">
								  </td>

							    </tr>
							</form>
					</c:forEach>
				</table>
			</c:if>
		
		
		
			<table>
				<TR>
					<td>
						<form action="search-current-show.do" method="get">
							<input type="submit" value="Back">
						</form>
					</td>
					<td colspan="6">&nbsp;</td>
				</tr>
			</table>



		</div>

	</fmt:bundle>
	<%@ include file="include/printSesVars.jsp"%>

</body>
</html>