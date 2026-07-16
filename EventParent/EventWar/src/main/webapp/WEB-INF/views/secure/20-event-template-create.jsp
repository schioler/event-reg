<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
  <head>
<title>EventTemplate</title>
<%@ include file="include/common-head.jsp"%>
  </head>
  <body>
     <% request.setAttribute("action", "create");  %>

    <%@ include file="include/event-template-crud.jsp" %>
                               
  </body>
</fmt:bundle>
</html>
