<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>EventType</title>
<link rel="stylesheet" href="styles/cols.css">
<link rel="stylesheet" href="styles/event.css">
</head>
<body >
	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">

		<div class="content-div">
			<br>
			<p>Login</p>
         <hr>
			<form action="user-authenticate.do" method="post">
			   <div class="container-3-col" style="text-align:center;width:100%;">
			       <div>&nbsp;</div>
			        <div>&nbsp;</div>
			         <div>&nbsp;</div>
			      
			      <div class="text-input-head">
			         <span  >Username</span>
			      </div>
				   <div >
						<input class="text-input" type="text" name="username" autofocus="true">
					</div>
			      <div>&nbsp;</div>
				
               <div class="text-input-head">
                  <span  style="width:90%">Password</span>
                  </div>
               <div>
                  <input class="text-input" type="password" name="password">
               </div>
				   <div>&nbsp;</div>

               <div>&nbsp;</div>
               <div style="text-align: right; margin-bottom: 8px;">
                  <input class="text-input-btn" type="submit" name="submit" value="Send">
               </div>   				  	
               <div >&nbsp;</div>
			   
			   </div>
			   

				</form>
				<br>
		</div>

	</fmt:bundle>
</body>
</html>