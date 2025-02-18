<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>

<title>EventTemplate</title>
<link rel="stylesheet" href="public/styles/cols.css">
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">

			<%@ include file="include/menu.jsp"%>
			<%@ include file="include/status.jsp"%>

			<h3><fmt:message key="event-template.entity"></fmt:message></h3>
<hr color="blue">

			<form method="post" action="./event-tmpl-delete.do">
				<div class="container-two-col">
				<div>
               <span> Name</span>
            </div>
            <div>
               <input type="hidden" name="event-template-id" value="${sesEventTemplate.id}">
               <input type="hidden" name="event-type-id"    value="${sesEventTemplate.parentId}"> 
               <input type="hidden" name="login-id"  value="${sesEventTemplate.loginId}">
               <input type="text"   id="name" name="name" value="${sesEventTemplate.name}" >
            </div>
            <div>
               <span>short Name</span>
            </div>
            <div>
               <input type="text" id="short-name" name="short-name"
                  value="${sesEventTemplate.shortName}" >
            </div>
            <div>Dose</div>
            <div>
               <input type="text" name="dose" id="dose" value="${sesEventTemplate.dose}">
            </div>
            <div>Unit</div>
            <div>
               <input type="text" id="unit" name="unit" value="${sesEventTemplate.unit}">
            </div>
            <div>Description</div>
            <div>
               <input type="text" id="description" name="description"
                  value="${sesEventTemplate.description }">
            </div>
            <div>Is Favorite</div>
            <div>
               <input type="text" id="is-favorite" name="is-favorite"
                  value="${sesEventTemplate.favorite}">
            </div>
            
            <div>&nbsp;</div>
            <div>
                      
               <input  class="tmpl-text-btn" type="submit" value="Delete">
            </div>
				
				
				</div>
			</form>



	</fmt:bundle>
</body>
</html>