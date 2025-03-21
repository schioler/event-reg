<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle	basename="dk.schioler.event.base.resources.EventListResources">
<head>
	  <title>EventTemplate</title>
   <%@ include file="include/common-head.jsp"%>
</head>
<body>
   <jsp:include page="include/event-template-single.jsp">
      <jsp:param  name="action" value="update"/>
   </jsp:include>
</body>
	</fmt:bundle>
</html>
            