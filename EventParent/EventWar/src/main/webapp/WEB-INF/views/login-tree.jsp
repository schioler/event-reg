<!DOCTYPE html>
<%@page import="dk.schioler.shared.security.entity.*"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dk.schioler.event.web.controller.AbstractController.*"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>
<fmt:bundle
	basename="dk.schioler.event.base.resources.EventListResources">
	<title><fmt:message key="favorites.title" /></title>
</fmt:bundle>
<!-- <link rel="stylesheet" href="public/styles/cols.css"> -->
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
	

	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">
		
		<%@ include file="include/menu.jsp"%>
<%-- 		<%@ include file="include/status.jsp"%> --%>
      
		<p>
		<fmt:message key="login.tree" />
		</p>

		<div >
			<c:choose>
				<c:when test="${not empty sesLoginTree}">
				  <div class="container-0">
				  <div></div>
				  <div>
         				  Login Tree
			<!-- 	                      <table>
                          <tr>
                             <td class="col-role-hdr">ROLE</td>
                             <td class="col-token-hdr">Token</td>
                             <td class="col-startdate-hdr" >Start Date</td>
                             <td class="col-enddate-hdr">End Date</td>
                             <td class="col-button-hdr">&nbsp;</td>
                          </tr></table> -->
                          </div>
				</div>
            <hr class="line">
				<c:forEach var="login" items="${sesLoginTree}" >
    		        <form action="event-save.do" method="post">
                  <div class="container-${login.level}">
 							<div  >&nbsp;</div>
							<div>
								   <input type="hidden"  value="${login.parent.id}">							
		                     <input type="hidden"  value="${login.id}">                     
                           <div class="login">
                              <div  >Role</div>
                              <div style="color:black">
							            ${login.role}
                              </div>
                              <div>
                                 <input type="button" style="border-radius: 5px;color:maroon;" value="Edit" >  
                               </div>
                               <div>Login Token</div>
                             <div style="color:black">
									      ${login.token}
                              </div>
                              <div>&nbsp;</div>
                              
                              <div>Created:</div>
                              <div style="color:black">
                                 ${ login.startTS.format(DateTimeFormatter.ISO_LOCAL_DATE )} 
                              </div>
                               <div>&nbsp;</div>
                              <div>Invalidated:</div>
                              <div style="color:black">
                                 ${login.endTS }
                              </div>
                              <div>&nbsp;</div>
                              <!-- <div>
                              </div>
                              <div>&nbsp;</div> -->
                              <!-- <div>&nbsp;</div> -->
                          </div>

							  			  
							  </div>
					 		</div>
                  </div>
     					 </form>

					</c:forEach> 
				</c:when>

				<c:otherwise>
					<p>No Users</p>
				</c:otherwise>
			</c:choose>
			<br>

		</div>

 

	</fmt:bundle>
	  <%@ include file="include/printSesVars.jsp"%>
</body>
</html>