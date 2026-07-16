<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
      <%@ include file="include/common-head.jsp"%>
      <title><fmt:message key="event.delete.title" /></title>
</fmt:bundle>
</head>
<body>
      <%@ include file="include/part-top.jsp"%>
      <%@ include file="fraction/event-delete-fraction.jsp"%>      
      <%@ include file="include/part-bottom.jsp"%>
</body>
</html>





<!-- <!DOCTYPE html> -->
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@page import="java.util.ResourceBundle"%> --%>
<%-- <%@page import="java.util.Locale"%> --%>
<%-- <%@ page session="true"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> --%>

<%-- <html>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
  <head>
<%@ include file="include/common-head.jsp"%>
<title><fmt:message key="home.title" /></title>
  </head> --%>
<!--   <body> -->
<!--     <div class="menu-div"> -->
<%--       <%@ include file="include/menu.jsp"%> --%>
<!--     </div> -->


<!--     <div class="content-div"> -->
<%--       <fmt:bundle basename="dk.schioler.event.base.resources.EventListResources"> --%>
<!--         <table> -->
<!--           <tr> -->
<!--             <td> -->
<!--               <form action="./event-type.select.do" method="post"> -->
<%--                 <%@ include file="./include/event-type.select.jsp"%> --%>
<!--               </form> -->
<!--             </td> -->

<!--           </tr> -->
<!--         </table> -->
<!--         <br> -->
<%--       </fmt:bundle> --%>
<!--     </div> -->
<!--     <div class="footer-div"> -->
<%--       <%@ include file="include/event-template-list.jsp"%> --%>
<!--     </div> -->
<!--     <div class="footer-div"> -->
<%--       <c:choose> --%>
<%--         <c:when test="${not empty sesEventQueue}"> --%>
<%--           <%@ include file="include/event-queue.jsp"%> --%>
<%-- 	        <%@ include file="include/event-queue.jsp"%> --%>

<%--         </c:when> --%>
<%--         <c:otherwise> --%>
<!--           <p>no queue</p> -->
<%--         </c:otherwise> --%>
<%--       </c:choose> --%>
<!--     </div> -->
<!--     <div> -->
<%--       <c:choose> --%>
<%--         <c:when test="${not empty sesEventsInserted }"> --%>
<!--           <ul> -->
<%--             <c:forEach var="event" items="${sesEventsInserted }"> --%>
<%--               <out> ${event}</out> --%>
<!--               <br> -->
<%--             </c:forEach> --%>
<!--           </ul> -->
<%--         </c:when> --%>
<%--       </c:choose> --%>

<!--     </div> -->


<!--   </body> -->
<%-- </fmt:bundle> --%>
<!-- </html> -->