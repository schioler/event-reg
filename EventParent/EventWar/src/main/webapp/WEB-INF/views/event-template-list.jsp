<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dk.schioler.event.base.entity.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle  basename="dk.schioler.event.base.resources.EventListResources">

<head>
      <%@ include file="include/common-head.jsp"%>
   <title>EventTmplList</title>
</head>
<body>
<DIV class="body-col-5-90-5">
   <DIV>&nbsp;</DIV>
   <DIV>
       <div class="body-row-100">
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
               <h2> <fmt:message key="event-template.entity" />:</h2>
            </DIV>
            </DIV>
       </div>
       
       <div class="row-body-div">
	       <c:forEach var="item" items="${sesEventTypes}">
	            
	            <details >
	                    <summary >	                 
                        <span>&nbsp;</span>
	                     <span>${item.name }</span>
	                     <span>&nbsp;</span>
	                     <span>(${item.shortName })</span>
	                  </summary>
	                                
	                 <form action="./event-template-create-show.do" method="post">
	                 <div class="center-col-7-header">
	                    <div><input type="submit" value="New"> 
	                       <input type="hidden" id="event-type-id" name="event-type-id" value="${item.id }">
	                       <input type="hidden" name="login-id" value="${item.loginId  }">
	                       
	                    </div>
	                    <div>Name</div>
	                    <div>ShortName</div>
                       <div>Dose</div>
                       <div>Unit</div>
                       <div>Favorite</div>
                       <div>Sort order</div>
	                 </div>
                    </form>
                    <c:forEach var="tmpl" items="${item.children}">
	                    <form action="./event-template-delete-show.do" method="post">
	                     <div class="center-col-7">
                       <div>
                           <input type="hidden" id="event-template-id" name="event-template-id" value="${tmpl.id }">
                           <input type="submit" name="edit" value="edit" formaction="event-template-update-show.do"/>
                           <input type="submit" name="delete" value="delete"/>
                       </div>
                       <div>${tmpl.name}</div>
                       <div>${tmpl.shortName }</div>
                       <div>${tmpl.dose }</div>
                       <div>${tmpl.unit}</div>
                       <div>${tmpl.favorite}</div>
                       <div>${tmpl.sortOrder}</div>
                    </div>
	                    </form>
	                 </c:forEach>        
	            </details>                       
                			               
	           
	       </c:forEach>
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
         
   <%--    <%@ include file="include/printSesVars.jsp"%> --%>
</body>
</fmt:bundle>
</html>
