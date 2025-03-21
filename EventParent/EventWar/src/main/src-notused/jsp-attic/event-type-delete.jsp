
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle	basename="dk.schioler.event.base.resources.EventListResources">
<head>
   <title><fmt:message key="event-type.entity" /></title>
   <%@ include file="include/common-head.jsp"%>
</head>
<body>
	<DIV class="col-body-5-90-5">
        <DIV>&nbsp;</DIV>
        <DIV>
            <div class="row-body-100">
               <div class="row-body-div">
                  <DIV class="center-area-100">                  
                    <DIV >
                        <%@ include file="include/menu.jsp"%> 
                    </DIV>
                  </DIV>
                </div>
                <div class="row-body-div">
                  <DIV class="center-area-100">                  
                    <DIV >
                    <h2><fmt:message key="event-type.title" /></h2>
                    </DIV>
                  </DIV>
               </div> 
               <div class="row-body-div">
                  <div>
                     <DIV class="center-area-100">
                        <DIV>
                           <form action="./event-type-delete.do" method="post">
                              <%@ include file="include/event-type.jsp"%>
                           </form>
                        </DIV>
                        <DIV></DIV>
                     </DIV>
                  </div>
               </div>
               <div class="row-body-div"> 
                    <DIV class="center-area-100">                  
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