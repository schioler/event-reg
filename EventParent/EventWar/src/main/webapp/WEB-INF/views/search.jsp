<!DOCTYPE html>
<%@page import="dk.schioler.event.base.entity.EventType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page session="true"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="dk.schioler.event.base.entity.EventTemplate"%>
<%@ page import="dk.schioler.event.base.entity.EventType"%>
<%@ page import="dk.schioler.event.web.controller.WebTokensAbstract"%>
<%@ page import="dk.schioler.shared.timeline.TIMESLOT_LENGTH"%>

<html>
<head>
<title>Search</title>
<link rel="stylesheet" href="styles/cols.css">
<link rel="stylesheet" href="styles/event.css">
</head>
<body>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">

	<div >
		<%@ include file="include/menu.jsp"%>
	</div>
	<div class="content-div">
	  <!-- 5 cells -->
		<table>
			<form action="search.do" method="post">
				<tr>
					<td class="fc-5-c" colspan="5">
						<h2>Search</h2>
					</td>
				</tr>
				<tr>
					<td class="fc-1-r"><b>Timeline</b></td>
					<td class="fc-1-l"><select name="interval">
							<option>Choose interval</option>
							<option><%=TIMESLOT_LENGTH.MINUTE%></option>
							<option><%=TIMESLOT_LENGTH.HOUR%></option>
							<option><%=TIMESLOT_LENGTH.DAY%></option>
							<option><%=TIMESLOT_LENGTH.MONTH%></option>
					</select></td>
					<td class="fc-1-l"><input  name="from-date" type="datetime-local" pattern="">
					</td>
					<td  class="fc-1-l"><input  name="count" type="number"></td>
					<td class="fc-1-l"><input  type="submit" value="Search" /></td>
				</tr>
			</form>
			<tr>
				<td class="fc-1-r"><b>EventTemplates</b></td>

				<td class="fc-2-l" COLSPAN="3">
				  <c:choose>
						<c:when test="${not empty sesSearchCriteriaTmplList}">
							<table>

								<c:forEach var="item" items="${sesSearchCriteriaTmplList}">
									<tr>
										<form action="./search-remove-template.do" method="post">
											<td><input name="eventTemplateId" type="hidden"
												value="${item.id }"></input> <label>${item.name }</label></td>
											<td><input class="event-btn" type="submit" value="Fjern">
											</td>
										</form>
									</tr>
								</c:forEach>
							</table>
						</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose>
				</td>
				<td class="fc-1-l" >&nbsp;</td>
			</tr>
		</table>
		<br>
		<br>
		<hr>

		<table>
			<tr>
				<td class="td-right">
					<form action="./search-select-type.do" method="post">
						<%@ include file="include/event-type-select.jsp"%>
					</form>
				</td>
				<td>
					<table>
						<%
						int i = 0;
						
						%>
						<c:choose>
							<c:when test="${not empty sesEventTemplates}">
								<c:forEach var="eventTemplate" items="${sesEventTemplates}">
									<form action="./search-add-tmpl.do" method="post">
										<tr>
											<td class="event-col-1">
											 <input type="hidden" name="templateId" value="${eventTemplate.id}">
											 <input class="event-cnt-1" type="submit" value="Add"></td>
											<td class="event-col-2"><label class="event-cnt-2"
												for="eventTemplate.id"> ${eventTemplate.name}</label></td>
											<td class="event-col-3"><input class="event-cnt-3"
												type="text" name="dose" value="${eventTemplate.dose}">
											</td>
											<td class="event-col-4"><input class="event-cnt-4"
												type="text" name="unit" value="${eventTemplate.unit}">
											</td>
											<td class="event-col-5">&nbsp;</td>
											<td class="event-col-6">&nbsp;</td>
											<td class="event-col-7"><input class="event-cnt-7"
												type="text" name="note"></td>
											<%
											i++;
											%>
										</tr>
									</form>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td class="event-col-1"></td>
									<td class="event-col-2">&nbsp;</td>
									<td class="event-col-3">&nbsp;</td>
									<td class="event-col-4">&nbsp;</td>
									<td class="event-col-5">&nbsp;</td>
									<td class="event-col-6">&nbsp;</td>
									<td class="event-col-7">Booh</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</table>
				</td>
			</tr>
		</table>
	</div>
</fmt:bundle>
</body>
</html>
