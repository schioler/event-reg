<!DOCTYPE html>
<%@page import="java.util.Enumeration"%>
<%@page import="dk.schioler.event.base.entity.EventType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="dk.schioler.event.base.entity.EventTemplate"%>
<%@ page import="dk.schioler.event.base.entity.EventType"%>
<%@ page import="dk.schioler.event.web.controller.AbstractController"%>
<%@ page import="dk.schioler.shared.timeline.api.entity.TIMESLOT_LENGTH"%>

<html>
<head>
<title>Search</title>
<link rel="stylesheet" href="public/styles/cols.css">
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">

		<div>
			<%@ include file="include/menu.jsp"%>
		</div>
		<div class="content-div">
    			<!-- 5 cells -->
			<form action="search.do" method="post">
				<table>
					<tr>
						<td><input type="submit" value="Search" /></td>
						<td colspan="4">
							<h2>Search</h2>
						</td>
					</tr>
					<tr>
						<td><b>Timeline start</b></td>
						<td><input name="from-date" type="datetime" ></td>
						<!-- <td><input name="from-time" type="time" pattern="HH24:Mi"></td> -->
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td><b>Interval length</b></td>
						<td><select name="interval">
								<option>Choose interval</option>
								<option><%=TIMESLOT_LENGTH.MINUTE%></option>
								<option><%=TIMESLOT_LENGTH.HOUR%></option>
								<option><%=TIMESLOT_LENGTH.DAY%></option>
								<option><%=TIMESLOT_LENGTH.MONTH%></option>
						</select></td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td><b>Interval Count</b></td>
						<td><input name="count" type="number"></td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td><b>EventTemplates</b></td>
						<td><input class="event-btn" type="submit"
							formaction="./search-add-templates-show.do" value="Choose/Tilpas"></td>
						<td colspan="3">&nbsp;</td>
					</tr>
					</form>
					<tr>
						<td>&nbsp;</td>
						<td COLSPAN="4"><c:choose>
								<c:when test="${not empty sesSearchCriteria}">
									<table>
									   <% int i = 1; %>
										<c:forEach var="item" items="${sesSearchCriteria.eventTemplates}">
											<tr>
												<!-- <form action="search-remove-template.do"  method="post"> -->
												<td>
												   <input name="id<% out.print(i);%> " type="hidden" value="${item.id }"> </input> 
												   <label>${item.name }</label>
												</td>
												<td>
												</td>
<!-- 												<input class="event-btn" type="submit"	value="Fjern" formaction="./search-remove-template.do">
												</form>
 -->											</tr>
                                    <% i++; %>
										</c:forEach>
									</table>
								</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</table>
			

			<br> <br>
			<hr>
	</fmt:bundle>
</body>
</html>
