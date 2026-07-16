<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
		<head>
<%@ include file="include/common-head.jsp"%>
<title><fmt:message key="favorites.title" /></title>
		</head>
		<body>
				<!--   INCLUDE part-top.jsp -->
				<%@ include file="include/part-top.jsp"%>
				<%@ include file="fraction/event-template-list-fraction.jsp"%>
				<%@ include file="include/part-bottom.jsp"%>
		</body>
</fmt:bundle>
</html>