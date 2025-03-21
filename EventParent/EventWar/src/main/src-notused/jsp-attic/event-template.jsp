<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">
<div class="center-area-col-100">
   <div>
       <fmt:message key="event-template.entity"/>
       <c:out value="${param.editable}"></c:out>
                  <fmt:message key="event-template.entity" />:
   </div>
</div>

<div class="body-col-33-34-33">
   <div>&nbsp;</div>
   <div>
         <Div class="center-col-50-50">
                <c:choose>
                  <c:when test="${empty sesEventTemplate}">
                        <input type="hidden" name="event-type-id" value="${sesSelectedEventTypeId }"/>
                   </c:when>
                     <c:otherwise>
                        <input type="hidden" name="event-type-id" value="${sesEventTemplate.parentId }"/>
                        <input type="hidden" name="event-template-id" value="${sesEventTemplate.id }"/> 
                     </c:otherwise>
               </c:choose>
       
                  
            
            
            <DIV class="center-col-50-50-left">
                 <div >
                  &nbsp;     
                 </div>
            </DIV>            
             <DIV class="center-col-50-50-right">
                 <div >
                     <input type="submit"  value='<fmt:message key="accept"/>'  />
                     <input type="button" onclick="history.back()" value='<fmt:message key="cancel"/>' />
                 
                 </div>        
                  </DIV>
            </DIV>
   </div>
   <div>&nbsp;</div>
</div>
</fmt:bundle>
<%--   <c:choose>
            <c:when test="${ param.editable eq 'yes'}">
               <jsp:include page="event-template-active.jsp"></jsp:include>
            </c:when>
            <c:otherwise>
               <jsp:include page="event-template-disabled.jsp"></jsp:include>
             </c:otherwise>
          </c:choose>
        --%>
