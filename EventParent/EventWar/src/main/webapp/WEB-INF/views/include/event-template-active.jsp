<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">

<div class="body-col-33-34-33">
   <div>&nbsp;</div>
   <div>
         <Div class="center-col-50-50">
				<c:choose>
					<c:when test="${not empty sesEventTemplate}">
						<input type="hidden" name="event-type-id"
							value="${sesEventTemplate.parentId}" />
						<input type="hidden" name="event-template-id"
							value="${sesEventTemplate.id}" />
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${not empty sesEventTypeId}">
								<input type="hidden" name="event-type-id"
									value="${sesEventTypeId }" />
							</c:when>
						</c:choose>
					</c:otherwise>
				</c:choose>


				<DIV class="center-col-50-50-left">
               <div >
                  <div ><label ><fmt:message key="event-template.name" />:</label></div>
               </div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div  >
                          <input type="text" name="name" autofocus="autofocus"  value="${sesEventTemplate.name }"/>                     
                 </div>
            </DIV>
                        
             <DIV class="center-col-50-50-left">               
               <div ><label for="shortname"><fmt:message key="event-template.shortname" />:</label></div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div > 
                  <input type="text" name="short-name" value="${sesEventTemplate.shortName }">
                  </div>                
             </DIV>
            
            <div class="center-col-50-50-left" > 
               <label ><fmt:message key="event-template.dose" />:</label>
            </div>
            <div class="center-col-50-50-right"> 
                 <input type="text" name="dose" id="dose" value="${sesEventTemplate.dose}">
             </div>            
            
            <div class="center-col-50-50-left">
               <label for="unit"><fmt:message key="event-template.unit" />:</label>
            </div>
            <div class="center-col-50-50-right">
                <%-- <input type="text" name="unit" id="unit" value="${sesEventTemplate.unit}"> --%>
                  
                  <select name="unit" id="unit">
                        <c:choose>
                           <c:when test="${empty sesEventTemplate }">
                              <option selected value="---">Choose UNIT</option>
                              <c:forEach var="unit" items="${sesSelectableUnits}">
                                 <option value="${unit}">${unit}</option>
                              </c:forEach>
                           </c:when>
                           <c:otherwise>
                              <c:forEach var="unit" items="${sesSelectableUnits}">
                                 <c:choose>
                                    <c:when test="${sesEventTemplate.unit eq unit }">
                                       <option selected value="${unit}">${unit}</option>
                                    </c:when>
                                    <c:otherwise>
                                       <option value="${unit}">${unit}</option>
                                    </c:otherwise>
                                 </c:choose>
                              </c:forEach>
                           </c:otherwise>
                        </c:choose>
                       </select>
            </div>

            <div class="center-col-50-50-left">
               <label for="description"><fmt:message key="event-template.description" />:</label>
            </div>
            <div class="center-col-50-50-right">
                  <input type="text" id="description" name="description" value="${sesEventTemplate.description }">
             </div>
   
            <div class="center-col-50-50-left">
               <label for="favorite"><fmt:message key="event-template.is-favorite" />:</label>
            </div>
            <div class="center-col-50-50-right">
                  <c:choose>
                     <c:when test="${sesEventTemplate.favorite == true }">
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
            </div>
         
            <div class="center-col-50-50-left">
               <label for="sortorder"><fmt:message key="event-template.sortorder" />:</label>
            </div>
            <div class="center-col-50-50-right">
                 <input type="text" id="sort-order" name="sort-order" value="${sesEventTemplate.sortOrder}">
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
