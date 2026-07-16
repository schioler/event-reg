<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
<head>
		<%@ include file="include/common-head.jsp"%>
		<title><fmt:message key="event.search" /></title>
</head>
<body>
		<%@ include file="include/part-top.jsp"%>
		<%@ include file="fraction/event-search-fraction.jsp"%>
		<%@ include file="include/part-bottom.jsp"%>
</body>
</fmt:bundle>
</html>