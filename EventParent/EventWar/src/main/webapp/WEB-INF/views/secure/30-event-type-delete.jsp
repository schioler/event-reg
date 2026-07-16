<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
<head>
   <title><fmt:message key="event-type.entity" /></title>
   <%@ include file="include/common-head.jsp"%>
</head>
<body>
   <%-- <jsp:include page="include/event-type.single.jsp">
      <jsp:param  name="action" value="delete"/>
   </jsp:include> --%>
   <% request.setAttribute("action", "delete");  %>
   <%@ include file="include/event-type-crud.jsp" %>
</body>
</fmt:bundle>
</html>