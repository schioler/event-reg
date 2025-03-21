<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">
<div class="center-area-col-100">
   <div>
       <fmt:message key="event-type.entity"/>
       <c:out value="${param.editable}"></c:out>
                  <fmt:message key="event-type.entity" />:
   </div>
</div>

<div class="body-col-33-34-33">
   <div>&nbsp;</div>
   <div>
         <Div class="center-col-50-50">
               <%-- <input type="hidden" name="event-type-id" value="${sesEventTemplate.parentId }"/>
                 <input type="hidden" name="event-tmpl-id" value="${sesEventTemplate.id }"/> --%>
         
          <c:choose>
            <c:when test="${ param.editable eq 'yes'}">
                             
            <DIV class="center-col-50-50-left">
               <div >
                  <div ><label ><fmt:message key="event-type.name" />:</label></div>
               </div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div  >
                          <input type="text" name="name" autofocus="autofocus"  value="${sesEventTmpl.name }"/>                     
                 </div>
            </DIV>
                        
             <DIV class="center-col-50-50-left">               
               <div ><label for="shortname"><fmt:message key="event-type.shortname" />:</label></div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div > 
                  <input type="text" name="short-name" value="${sesEventTemplate.shortName }">
                  </div>                
             </DIV>
            
            <div class="center-col-50-50-left">
               <label for="description"><fmt:message key="event-type.description" />:</label>
            </div>
            <div class="center-col-50-50-right">
                  <input type="text" id="description" name="description" value="${sesEventTemplate.description }">
             </div>
   
               
                
            </c:when>
            <c:otherwise>
                            
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



            <%-- 



<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="center-area-col-100">
   <div>
      EVENT TYPE 
   </div>
</div>

<div class="body-col-33-34-33">
   <div>&nbsp;</div>
   <div>
			<Div class="center-col-50-50">
			   
			   <DIV class="center-col-50-50-left">
			     	<input type="hidden" name="event-type-id"	value="${sesEventType.id }">
			     	 <input type="hidden" name="event-type-id"   value="${sesEventType.loginId}">
					<div ><label for="name"><fmt:message key="event-type.name" />:</label></div>
			   </DIV>
			   <DIV class="center-col-50-50-right">
				     <div  >
			        <input type="text" name="name" value="${sesEventType.name }">
				     </div>
			   </DIV>
			   
			    <DIV class="center-col-50-50-left">
                 <div ><label for="shortname"><fmt:message key="event-type.shortname" />:</label></div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div >
                 <input type="text" name="short-name" value="${sesEventType.shortName }"/>
                 </div>       
            </DIV>
            
             <DIV class="center-col-50-50-left">
                 <div ><label for="description"><fmt:message key="event-type.description" />:</label></div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div  >
                 <input type="text" name="description" value="${sesEventType.description }">
                 </div>        
            </DIV>
            
            <DIV class="center-col-50-50-left">
                 <div >
                 
                 </div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div >
                     <input type="submit"  value='<fmt:message key="accept"/>'  />
                     <input type="button" onclick="history.back()" value='<fmt:message key="cancel"/>' />
                 
                 </div>        
            </DIV>
			</Div>   	
   </div>
   <div>&nbsp;</div>
</div>


 --%>