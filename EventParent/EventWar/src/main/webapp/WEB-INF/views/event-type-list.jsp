<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page session="true"%>
<html>
<head>
<title>EventType</title>
<link rel="stylesheet" href="styles/cols.css">
<link rel="stylesheet" href="styles/event.css">
</head>
<body>
<fmt:bundle
   basename="dk.schioler.event.base.resources.EventListResources">

	<div class="menu-div">
		<%@ include file="include/menu.jsp"%>
	</div>
	<div class="content-div">
	  <br>
	        <table >
	           <tr >
	           <td class="fc-05-r">&nbsp;</td>
	           <td class="fc-1-l" ><b>Name</b></td>
	           <td class="fc-1-l"><b>Shortname</b></td>
	           <td class="fc-2-l"><b>Description</b></td>
	           <td class="fc-05-r">&nbsp;</td>
	           </tr>
	           
   	     <% int i = 0; %>
   	     <c:forEach var="type" items="${sesEventTypes}">
   	     <form action="./event-type-update-show.do"  autocomplete="on" method="post">
   	        <c:set var="now" value="<%= i % 2%>"/>
   	        
   	        <c:choose>  
   	           <c:when test="${now == 0 }">
   	              <tr class="tr-odd">
   	           </c:when>
   	           <c:otherwise>   
   	              <tr class="tr-even"> 
   	           </c:otherwise>
   	        </c:choose>
	           
	           <td class="fc-05-r">
	              <input type="hidden" name="id" value="${type.id}"/>
	              <input class="event-btn" type="submit" name="gem" value="Edit"/>
	              <input class="event-btn" type="submit" name="slet" value="Delete" formaction="./event-type-delete-show.do"/>
	           </td>        
	           <td class="fc-1-l">
	           <label > ${ type.name} </label>
	           </td>	              
              <td class="fc-1-l">      
              <label>${ type.shortName}</label> 
              </td>
              <td class="fc-2-l">        
               <label>${type.description }</label>
               </td>
	           <td class="fc-05-r">&nbsp;</td>
              </tr>
              
   	     </form>
   	     <% i++; %>
   	     </c:forEach>
              

           <tr>
              <td class="fc-05-r">
               <form action="./event-type-create-show.do" method="post">
               <input class="event-btn" type="submit" name="new" value="Opret">
               </form> 
              </td>
              <td class="fc-1-c" >&nbsp;</td>
              <td class="fc-1-c">&nbsp;</td>
              <td class="fc-2-c">&nbsp;</td>
              <td class="fc-05-r">&nbsp;</td>
           </tr>
            
	        </table>		
	        
		
		<br>
	</div>
	
</fmt:bundle>
</body>
</html>