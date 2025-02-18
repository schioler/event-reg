<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>
<script type="text/javascript">
   function changeValue(element, value) {
      document.getElementById(element).value = value;
   }
</script>

<title>EventTemplate</title>
<!-- <link rel="stylesheet" href="public/styles/cols.css"> -->
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">
      <%@ include file="include/menu.jsp"%>
      <%@ include file="include/status.jsp"%>

		<div class="content-div">
			<h3><fmt:message key="event-template.entity"></fmt:message></h3>
<hr color="blue">

      <form method="post" action="./event-tmpl-update.do">
         <div class="container-2-col">
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
					<c:choose>
						<c:when test="${eventTemplate.favorite == true }">
							<select name="is-favorite" id="is-favorite">
								<option selected value="${eventTemplate.isFavorite}">True</option>
								<option value="false">False</option>
							</select>
						</c:when>
						<c:otherwise>
							<select name="is-favorite" id="is-favorite">
								<option value="true">True</option>
								<option selected value="${eventTemplate.isFavorite}">False</option>
							</select>
						</c:otherwise>
					</c:choose>
				</div>
            
            <div>&nbsp;</div>
            <div>
                      
               <input  class="tmpl-text-btn" type="submit" value="Update">
            </div>
         </div>
      </form>

<%-- 


				<table>
					<tr>
						<td>
						   Name
						</td>
						<td>
						   <input type="hidden" name="id" value="${sesEventTemplate.id}">
						   <input type="hidden" name="eventTypeId" value="${sesEventTemplate.eventTypeId}">
						   <input type="text" name="name" value="${sesEventTemplate.name}">
						</td>
					</tr>
					<tr>
						<td>
						   Short Name
						</td>
						<td>
						   <input type="text" name="shortName"	value="${sesEventTemplate.shortName}">
						</td>
					</tr>
					<tr>
						<td>
						   Dose
						</td>
						<td>
						   <input type="text" name="dose" value="${sesEventTemplate.dose}">
						</td>
					</tr>
					<tr>
						<td>
						   Unit
						</td>
						<td>
						   <input type="text" name="unit" value="${sesEventTemplate.unit}">
						</td>
					</tr>
					<tr>
						<td>
						   Note
						</td>
						<td>
						   <input type="text" name="description" value="${sesEventTemplate.description }">
						</td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Update"></td>
					</tr>
				</table>
			</form>

		</div>
 --%>
	</fmt:bundle>
</body>
</html>