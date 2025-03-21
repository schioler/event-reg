<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
	<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">
<head>
   <meta name="viewport" content="width=device-width, initial-scale=1.0" />
   <link rel="stylesheet" href="./styles/event2.css">
   <link rel="stylesheet" href="./styles/event.css">
   
   <title><fmt:message key="login" /></title>
</head>
<body>
	<div class="col-body">
		<div class="div-body">
			<div class="col-content">
         <div>			
				<div class="col-container">
            <div>&nbsp;</div>
				<div>
					<form action="user-authenticate.do" method="post">
					<div class="grid-container">
					<div class="item1">
								<h2>
									<fmt:message key="login" />
								</h2>
					</div>
					<div class="item2">
								<div class="row-container">
									<div><fmt:message key="username" />:</div>
									<div><fmt:message key="password" />:</div>
								</div>
					</div>
					<div class="item3">
								<div class="row-container">
									<div><input type="text" name="username" autofocus="true"></div>
									<div><input type="password" name="password"></div>
								</div>
		       	</div>
      				<div class="item5">
								<input type="submit" name="<fmt:message key='login'/>"
									value="<fmt:message key='login'/>">
					</div>
					</div>
					</form>
				</div>
				<div>&nbsp;</div>
				</div> <!-- EO col-container -->
			</div>
			</div>
		</div>
	   </div>
</body>
</fmt:bundle>
</html>