
<!DOCTYPE html>
<%@page import="dk.schioler.shared.security.entity.*"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dk.schioler.event.web.controller.AbstractController.*"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle basename="dk.schioler.shared.bits.resources.LanguageResources">
		<head>
<%@ include file="include/common-head.jsp"%>
<title><fmt:message key="login.title" /></title>
		</head>
		<body>
				<%@ include file="include/part-top.jsp"%>
				<div>
						<p>
								<fmt:message key="login.tree" />
						</p>
				</div>
				<div class="container">
						<c:choose>
								<c:when test="${not empty sesLoginList}">
										<div class="row">
												<form action="login-save.do" method="post">
														<c:forEach var="login" items="${sesLoginList}">
																<div class="row">
																		<div class="col">
																				Role <input type="hidden" value="${login.parent.id}"> <input type="hidden" value="${login.id}">
																		</div>
																		<div class="col" style="color: black">${login.role}</div>
																		<div class="col">
																				<input type="button" style="border-radius: 5px; color: maroon;" value="Edit">
																		</div>
																		<div class="col">Login Token</div>
																		<div class="col" style="color: black">${login.token}</div>
																		<div class="col">Created:</div>
																		<div class="col" style="color: black">${ login.startTS.format(DateTimeFormatter.ISO_LOCAL_DATE )}</div>
																		<div class="col">Invalidated:</div>
																		<div class="col" style="color: black">${login.endTS }</div>
																</div>
														</c:forEach>
												</form>
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