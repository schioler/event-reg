<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>

<title>EventTmplList</title>
<!-- <link rel="stylesheet" href="public/styles/cols.css"> -->
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
<fmt:bundle
   basename="dk.schioler.event.base.resources.EventListResources">


   
   <%@ include file="include/menu.jsp"%>
   <%@ include file="include/status.jsp"%>
   
   <H3>
      <fmt:message key="event-template.list" />
      </H3>
<hr>
	<form action="./event-tmpl-type-select.do" method="post">
      <%@ include file="include/event-type-select.jsp"%>
	</form>               

<hr>
      <c:if test="${sesEventTemplates != null}">

			<div class="container-7-col-head">
				<div>
					<span class="tmpl-text-head">
						<form action="event-tmpl-create-show.do" method="post">
							<input type="hidden" name="event-type-id"
								value="${sesSelectedEventTypeId}"> <input
								class="header-text-btn" type="submit" value="New">
						</form>
					</span>
				</div>
				<div>
					<span class="tmpl-text-head">Name</span>
				</div>
				<div>
					<span class="tmpl-text-head">Short Name</span>
				</div>
				<div>
					<span class="tmpl-text-head">Dose</span>
				</div>
				<div>
					<span class="tmpl-text-head">Unit</span>
				</div>
				<div>
					<span class="tmpl-text-head">Description</span>
				</div>
				<div>
               <span class="tmpl-text-head">Is Favorite</span>
            </div>
			</div>

			<c:forEach var="eventTemplate" items="${sesEventTemplates}">
				<form method="post" action="./event-tmpl-delete-show.do">
					<div class="container-7-col">
						<div>
							<input type="hidden" name="event-template-id" value="${eventTemplate.id}"> 
							<input type="hidden" name="event" value="${eventTemplate.parentId}"> 
							<input class="header-text-btn" type="submit" formaction="./event-tmpl-update-show.do" value="Update">
							<input class="header-text-btn" type="submit" value="Delete">
						</div>
						<div>
							<input class="tmpl-text" type="text" name="name"
								value="${eventTemplate.name}">
						</div>
						<div>
							<input class="tmpl-text" type="text" name="shortName"
								value="${eventTemplate.shortName}">
						</div>
						<div>
							<input class="tmpl-text" type="text" name="dose"
								value="${eventTemplate.dose}">
						</div>
						<div>
							<input class="tmpl-text" type="text" name="unit"
								value="${eventTemplate.unit}">
						</div>
						<div>
							<input class="tmpl-text" type="text" name="description" value="${eventTemplate.description }">
						</div>
                  <div>
							<input class="tmpl-text" type="text" id="is-favorite" name="is-favorite" 
							     value="${eventTemplate.favorite}">
                  </div>
					</div>
				</form>
			</c:forEach>
		</c:if>



	

</fmt:bundle>
<%@ include file="include/printSesVars.jsp"%>
</body>
</html>
<%-- <table>        
            <c:forEach var="eventTemplate" items="${sesEventTemplates}">
               <tr>
                  <form method="post" action="./event-tmpl-delete-show.do">
                     <div class="event-col-1">
                        <input type="hidden" name="id" value="${eventTemplate.id}"> 
                        <input type="hidden" name="eventTypeId" value="${eventTemplate.parentId}">
                        <input class="event-btn" type="submit" formaction="./event-tmpl-update-show.do" value="Update"> 
                        <input type="submit" value="Delete">
                     </td>
                     <td class="event-col-2">
                       <input class="event-cnt-2" type="text" name="name" value="${eventTemplate.name}">
                     </td>
                     <td class="event-col-3">
                        <input class="event-cnt-3" type="text" name="shortName"  value="${eventTemplate.shortName}"> 
                     </td>
                     <td class="event-col-4">
                       <input class="event-cnt-4" type="text" name="dose" value="${eventTemplate.dose}">
                      </td>
                     <td class="event-col-5">
                         <input class="event-cnt-5" type="text" name="unit" value="${eventTemplate.unit}">
                     </td>
                     <td class="event-col-6">
                          <input class="event-cnt-6" type="text" name="description" value="${eventTemplate.description }">
                     </td>
                     <td class="event-col-7">
                         <input  type="time" name="time">
                         <input  type="date" name="date">
                     </td>
               
                  </form>
               </tr>
            </c:forEach>
               <tr>
                <td>
                <form action="event-tmpl-create-show.do" method="post">
                     <input type="hidden"  name="eventTypeId" value="${sesSelectedEventTypeId}">
                      <input type="submit" value="New">
                </form>   
                </td>
                <td colspan="6">
                  &nbsp;
                </td>
                </tr>
         </table> --%>