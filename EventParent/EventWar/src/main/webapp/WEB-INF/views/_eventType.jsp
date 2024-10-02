<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page session="true"%>

<html>
<head>
<title>EventType</title>
<link rel="stylesheet" href="styles/cols.css">
<link rel="stylesheet" href="styles/event.css">
</head>
<body>
	<div class="menu-div">
		<%@ include file="include/menu.jsp"%>
	</div>
	<div class="content-div">
	
	  <br> 
	  <c:choose>
	     <c:when test="${not empty sesEventType}">
            <form action="./event-type-update.do" method="post">	     
                 <input type="hidden" name="id" value="${sesEventType.id }" > 
	     </c:when>
	     <c:otherwise>
	      <form action="./event-type-create.do" method="post">
	     </c:otherwise>
	  </c:choose>
	        <table>
	        <tr>
              <td>
              <label for="name">Name</label>
              </td>        
              <td>
                 <input type="text" name="name" value="${sesEventType.name }" >
              </td>
              </tr>
              <tr>
              
              <td>
              <label for="shortName">shortName</label>
              </td>        
              <td>
              <input type="text" name="shortName" value="${sesEventType.shortName}">
              </td>
              </tr>
              
              <tr>
              <td>
              <label for="description">Description</label>
              </td>        
              <td>
              <input type="text" name="description" value="${sesEventType.description }">
              </td>
              </tr>
              
              <tr>
              <td>
               
              </td>        
              <td>
              <input type="submit" value="save" >
              </td>
              </tr>
	        </table>		
	        
			</form>
		<br>
	</div>
	

</body>
</html>