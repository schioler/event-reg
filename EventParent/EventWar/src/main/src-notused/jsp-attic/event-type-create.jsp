<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle
	basename="dk.schioler.event.base.resources.EventListResources">
	<head>
<%@ include file="include/common-head.jsp"%>
<title>EventType - create</title>
</head>
<body>
<DIV class="body-col-5-90-5">
<DIV>&nbsp;</DIV>
<DIV>
   <div class="body-col-100">
               <div class="body-row-div">
                  <DIV class="center-col-100">                  
                    <DIV >
                        <%@ include file="include/menu.jsp"%> 
                    </DIV>
                  </DIV>
                </div>
                <div class="body-row-div">
                  <DIV class="center-col-100">                  
                    <DIV >
                    <h2><fmt:message key="event-type.entity" /></h2>
                    </DIV>
                  </DIV>
               </div> 
					<div class="body-row-div">
						<div>
							<DIV class="center-col-100">
								<DIV>
									<form action="./event-type-create.do" method="post">
										<%@ include file="include/event-type.jsp"%>
									</form>
								</DIV>
								<DIV></DIV>
							</DIV>
						</div>
					</div>
					<div class="body-row-div"> 
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