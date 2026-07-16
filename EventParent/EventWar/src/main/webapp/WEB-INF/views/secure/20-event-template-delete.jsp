<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
  <title>EventTemplate</title>
  <%@ include file="include/common-head.jsp"%>
</fmt:bundle>
</head>
<body>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
  <%
  request.setAttribute("action", "delete");
  %>
  <%@ include file="include/event-template-crud.jsp"%>
</fmt:bundle>
</body>
</html>
