<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">


         <Div class="center-col-50-50">
               <%-- <input type="hidden" name="event-type-id" value="${sesEventTemplate.parentId }"/>
                 <input type="hidden" name="event-tmpl-id" value="${sesEventTemplate.id }"/> --%>
               <c:choose>
                  <c:when test="${not empty sesEventType}">
                        <input type="hidden" name="event-type-id" value="${sesEventType.id }"/>
                   </c:when>
<%--                      <c:otherwise>
                        <input type="hidden" name="event-type-id" value="${sesEventTemplate.parentId }"/>
                        <input type="hidden" name="event-template-id" value="${sesEventTemplate.id }"/> 
                     </c:otherwise>
 --%>               </c:choose>
                               
            <DIV class="center-col-50-50-left">
               <div >
                  <div ><label ><fmt:message key="event-type.name" />:</label></div>
               </div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div  >
                          <input type="text" name="name" autofocus="autofocus"  value="${sesEventType.name }"/>                     
                 </div>
            </DIV>
                        
             <DIV class="center-col-50-50-left">               
               <div ><label for="shortname"><fmt:message key="event-type.shortname" />:</label></div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div > 
                  <input type="text" name="short-name" value="${sesEventType.shortName }">
                  </div>                
             </DIV>
            
            <div class="center-col-50-50-left">
               <label for="description"><fmt:message key="event-type.description" />:</label>
            </div>
            <div class="center-col-50-50-right">
                  <input type="text" id="description" name="description" value="${sesEventType.description }">
             </div>
   
             <DIV class="center-col-50-50-left">               
               <div ><label for="create"><fmt:message key="event-type.created" />:</label></div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div > 
                  <input type="text" disabled="disabled" name="created" value="${sesEventType.created }">
                  </div>                
             </DIV>  
   
   
               
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

<!--    </div>

   <div>&nbsp;</div>
</div> -->
</fmt:bundle>



   