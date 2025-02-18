<!DOCTYPE html>
<%@page import="dk.schioler.shared.timeline.api.entity.TimelineSlot"%>
<%@page import="dk.schioler.shared.timeline.api.entity.*"%>
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
<%@ page import="dk.schioler.event.web.init.SearchResultStruct"%>

<html>
<head>
<title>Search Result</title>
<link rel="stylesheet" href="public/styles/cols.css">
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">

	<div class="menu-div">
		<%@ include file="include/menu.jsp"%>
	</div>
	<div class="content-div">
		<table>
			<tr>
				<td>



					<h1>Search Result</h1>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>					
							<td class="col-firstcol">Start Dato</td>
							<td>${sesSearchResultStartDate}</td>
						</tr>
						<tr>
							<td class="col-firstcol">End Date</td>					
							<td>${sesSearchResultEndDate}</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table border="0">
						<%
						int row = 0;
						int col = 0;
						%>
						<c:forEach var="row" items="${sesSearchResult}">

							<%
							String trStyle = null;
							String colStyle = "col-content";

							if (row == 0) {
								trStyle = "tr-header";
							} else {
								if (row % 2 == 1) {
									trStyle = "tr-odd";
								} else {
									trStyle = "tr-even";
								}
							}
							%>

							<tr class="<%=trStyle%>">
								<c:forEach var="cell" items="${row}">
									<%
									if (col == 0) {
										colStyle = "col-firstcol";
									} else {
										colStyle = "col-content";
									}
									%>

									<td class="<%=colStyle%>">${cell}</td>
									<%
									col++;
									%>
								</c:forEach>
								<%
								col = 0;
								;
								%>
							</tr>
							<%
							row++;
							%>
						</c:forEach>

					</table>

					</div> <img alt="ØvBøv" src="${chart }">
               </td>
				</td>
			</tr>
		</table>
		
</fmt:bundle>
</body>
</html>
