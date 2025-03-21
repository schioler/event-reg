<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page session="true"%>
<html>
   <fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">
<head>
      <%@ include file="include/common-head.jsp"%>
      <title><fmt:message key="event-type.entity-list" /></title>
</head>
<body>
   <DIV class="body-col-5-90-5">
        <DIV>&nbsp;</DIV>
        <DIV>
            <div class="body-row-100">
               <div class="row-body-div">
                  <DIV class="center-col-100">                  
                    <DIV >
                       <%@ include file="include/menu.jsp"%>
                    </DIV>
                  </DIV>
                </div>
               <div class="row-body-div">
                  <DIV class="center-col-100">                  
                    <DIV >
                    <h2>   <fmt:message key="event-type-list" /></h2>
                    </DIV>
                  </DIV>
               </div>
               <div class="row-body-div">
                  <DIV class="center-col-100">                  
                  <DIV >  

				      		<div class="center-col-15-35-12-37-header">
							<div>
								<form action="./event-type-create-show.do" method="post">
									<input class="header-text-btn" type="submit" name="new"
										value="Opret">
								</form>
							</div>
							<div>
								<fmt:message key="event-type.name" />
							</div>
							<div>
			                   <fmt:message key="event-type.shortname" />
							</div>
							<div>
			                 <fmt:message key="event-type.description" />						
							</div>
							</div>
				      				       
							<c:forEach var="type" items="${sesEventTypes}">
				   			<form action="./event-type-update-show.do" autocomplete="on" method="post">
								<div class="center-col-15-35-12-37">
								<div > 
									<input type="hidden" name="id" value="${type.id}" />
									 <input  type="submit" name="gem" value="Edit" /> 
									 <input  type="submit" name="slet" value="Delete"
									   formaction="./event-type-delete-show.do" />
					    		</div>
								<div>
											${type.name}
								</div>
								<div>
											${type.shortName}
								</div>
								<div>
											${type.description }
								</div>
						      </div>
								</form>
								</c:forEach>
                   </DIV>               
                   </DIV>
                </div>
			       <div class="row-body-div"> 
                     <DIV class="center-col-100">                  
                     <DIV >
                     
                           <%@ include file="include/footer.jsp"%>
                           
                     </DIV>               
                     </DIV>    
                </div>  
            </div>
        </DIV> 
        <DIV>&nbsp;</DIV>
   </DIV>
</body>
   </fmt:bundle>
</html>