<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">
<div class="center-area-col-100">
   <div>
       <fmt:message key="event-template.entity"/>
       <c:out value="${param.editable}"></c:out>
   </div>
</div>

<div class="body-col-33-34-33">
   <div>&nbsp;</div>
   <div>
         <Div class="center-col-50-50">
               <%-- <input type="hidden" name="event-type-id" value="${sesEventTemplate.parentId }"/>
                 <input type="hidden" name="event-tmpl-id" value="${sesEventTemplate.id }"/> --%>
             <DIV class="center-col-50-50-left">
               <div >
                  <fmt:message key="event-template.entity" />:
               </div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div  >
                 <c:choose>
                     <c:when test="${param.editable eq 'yes' }">
			                 <input type="text" name="name" autofocus="autofocus"  value="${sesEventTemplate.name }"/>                     
                     </c:when>
                     <c:otherwise>
                        <label >
                           ${sesEventTemplate.name }
                        </label>
                     </c:otherwise>
                     
                 </c:choose>
                 </div>
            </DIV>
            
            
            <DIV class="center-col-50-50-left">               
               <div ><label for="shortname"><fmt:message key="event-template.shortname" />:</label></div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div >
                 
                     <c:choose>
                     <c:when test="${param.editable eq 'yes' }">
                          <input type="text" name="short-name" value="${sesEventTemplate.shortName }"/>                     
                     </c:when>
                     <c:otherwise>
                        <label >
                           ${sesEventTemplate.shortName }
                        </label>
                     </c:otherwise>                     
                 </c:choose>
                 </div>                
             </DIV>
            
            <div class="center-col-50-50-left" > 
               <label for="dose"><fmt:message key="event-template.dose" />:</label>
            </div>
			   <div class="center-col-50-50-right"> 
			      <c:choose>
                     <c:when test="${param.editable eq 'yes' }">                    
               			      <input type="text" name="dose" id="dose" value="${sesEventTemplate.dose}">
                     </c:when>
                     <c:otherwise>
                        <label >
                           ${sesEventTemplate.dose}
                        </label>
                     </c:otherwise>                     
                 </c:choose>
			   </div>            
			   <div class="center-col-50-50-left">
			      <label for="unit"><fmt:message key="event-template.unit" />:</label>
			   </div>
			   <div class="center-col-50-50-right">
			      <c:choose>
                     <c:when test="${param.editable eq 'yes' }">                    
                           <input type="text" name="unit" id="unit" value="${sesEventTemplate.unit}">
                     </c:when>
                     <c:otherwise>
                        <label >
                           ${sesEventTemplate.unit}
                        </label>
                     </c:otherwise>                     
                 </c:choose>

			   </div>

			   <div class="center-col-50-50-left">
			      <label for="description"><fmt:message key="event-template.description" />:</label>
		      </div>
			   <div class="center-col-50-50-right">
			      <c:choose>
                     <c:when test="${param.editable eq 'yes' }">                    
              			      <input type="text" id="description" name="description" value="${sesEventTemplate.description }">
                     </c:when>
                     <c:otherwise>
                        <label >
                           ${sesEventTemplate.description}
                        </label>
                     </c:otherwise>                     
                 </c:choose>
			   </div>
	
			   <div class="center-col-50-50-left">
			      <label for="favorite"><fmt:message key="event-template.is-favorite" />:</label>
			   </div>
			   <div class="center-col-50-50-right">
			       <c:choose>
                     <c:when test="${param.editable eq 'yes' }">                    
               
							      <c:choose>
							         <c:when test="${eventTemplate.favorite == true }">
							            <select name="is-favorite" id="is-favorite">
							               <option selected value="${eventTemplate.favorite}">True</option>
							               <option value="false">False</option>
							            </select>
							         </c:when>
							         <c:otherwise>
							            <select name="is-favorite" id="is-favorite">
							               <option value="true">True</option>
							               <option selected value="${eventTemplate.favorite}">False</option>
							            </select>
							         </c:otherwise>
							      </c:choose>
                     </c:when>
                     <c:otherwise>
                        <label >
                           ${sesEventTemplate.favorite}
                        </label>
                     </c:otherwise>                     
                 </c:choose>
			   </div>
			
			   <div class="center-col-50-50-left">
			      <label for="sortorder"><fmt:message key="event-template.sortorder" />:</label>
			   </div>
			   <div class="center-col-50-50-right">
			         <c:choose>
                     <c:when test="${param.editable eq 'yes' }">                    
							      <input type="text" id="sort-order" name="sort-order" value="${sesEventTemplate.sortOrder}">
                     </c:when>
                     <c:otherwise>
                        <label >
                           ${sesEventTemplate.sortOrder}
                        </label>
                     </c:otherwise>                     
                 </c:choose>
			   
			   
			   </div>
            
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

