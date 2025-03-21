<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle	basename="dk.schioler.event.base.resources.EventListResources">
<head>
	  
</head>

<body>
<DIV class="body-col-5-90-5">
<DIV>&nbsp;</DIV>
<DIV>
   <div class="body-col-100">
   <div class="body-row-div">
       <DIV class="center-col-100">                  
       <DIV >
           <%@ include file="menu.jsp"%> 
        </DIV>
        </DIV>
   </div>
   <div class="body-row-div">
         <DIV class="center-col-100">                  
           <DIV >
           <h2><fmt:message key="event-template.entity" /></h2>
           </DIV>
         </DIV>
   </div> 
    <div class="body-row-div">
      <div>
       <DIV class="center-col-100">
       <DIV>
           <c:choose>
            <c:when test="${param.action eq 'create'}">
               <form action="./event-template-create.do" method="post">
                     <jsp:include page="event-template-active.jsp" />
               </form>                         
            </c:when>
            <c:when test="${param.action eq 'update'}">
               <form action="./event-template-update.do" method="post">
                  <jsp:include page="event-template-active.jsp" />
               </form>                         
            </c:when>
            <c:when test="${param.action eq 'delete'}">
               <form action="./event-template-delete.do" method="post">
                  <jsp:include page="event-template-inactive.jsp"/>
               </form>                         
            </c:when>
            <c:otherwise><p>Tmpl action=${param.action }</p></c:otherwise>
              </c:choose> 
            

       </DIV>
       <DIV></DIV>
      </DIV>
      </div>
   </div>
   <div class="body-row-div"> 
      <DIV class="center-col-100">                  
       <DIV >
          <%@ include file="footer.jsp"%>
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