<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="dk.schioler.shared.security.entity.*"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<html>
<fmt:bundle basename="dk.schioler.shared.bits.resources.LanguageResources">
		<head>
<%@ include file="include/common-head.jsp"%>
<title><fmt:message key="login-search" /></title>
		</head>
		<body>
				<%@ include file="include/part-top.jsp"%>
				<div class="container">
						LOGIN SEARCH
						<fmt:message key="login.tree" />
				</div>
				<div class="container">
						<c:choose>
								<c:when test="${not empty sesLoginTree}">
										<div class="container">
												<hr class="line">
												<c:forEach var="login" items="${sesLoginTree}">
														<form action="event-save.do" method="post">
																<div class="row">
																		<div class="col">
																				<input type="hidden" value="${login.parent.id}"> <input type="hidden" value="${login.id}">
																		</div>
																		<div>Role</div>
																		<div style="color: black">${login.role}</div>
																		<div>
																				<input type="button" style="border-radius: 5px; color: maroon;" value="Edit">
																		</div>
																		<div>Login Token</div>
																		<div style="color: black">${login.token}</div>
																		<div>&nbsp;</div>
																		<div>Created:</div>
																		<div style="color: black">${ login.startTS.format(DateTimeFormatter.ISO_LOCAL_DATE )}</div>
																		<div>&nbsp;</div>
																		<div>Invalidated:</div>
																		<div style="color: black">${login.endTS }</div>
																</div>
														</form>
												</c:forEach>
										</div>
								</c:when>
								<c:otherwise>
										<p>No Users</p>
								</c:otherwise>
						</c:choose>
				</div>
				<%@ include file="include/part-bottom.jsp"%>
		</body>
</fmt:bundle>
</html>