<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
   <DIV class="container text-center">
      <h2>
         <fmt:message key="event.create" />
      </h2>
   </div>
   <div class="container " style="text-align: center; border: solid green 2px;">
      <FORM action="event-create.do" method="post">
         <input type="hidden" name="caller" value="10-event-create.jsp" />
         <DIV class="row" style="border: solid blue 0px;">
            <DIV class="col">
               <Label>Login:</Label>
            </DIV>
            <DIV class="col">
               <Label>Name:</Label>
            </DIV>
            <DIV class="col">
               <Label>ShortName:</Label>
            </DIV>
         </DIV>
         <DIV class="row">
            <DIV class="col">
               <INPUT type="text" name="req-login-id" disabled="disabled" readonly="readonly" value="${sesEventData.webEntity.loginId}">
            </DIV>
            <DIV class="col">
               <INPUT type="text" name="req-name" value="${sesEventData.webEntity.name}">
            </DIV>
            <DIV class="col">
               <INPUT type="text" name="req-name-short" value="${sesEventData.webEntity.nameShort}">
            </DIV>
         </DIV>
         <DIV class="row">
            <DIV class="col">
               <Label>Event Timestamp:</Label>
            </DIV>
            <DIV class="col">
               <Label>Dose:</Label>
            </DIV>
            <DIV class="col">
               <Label>Unit:</Label>
            </DIV>
         </DIV>
         <DIV class="row">
            <DIV class="col">
               <INPUT type="datetime-local" name="req-event-ts" >
            </DIV>
            <DIV class="col">
               <INPUT type="text" name="req-dose" value="${sesEventData.webEntity.dose}">
            </DIV>
            <DIV class="col">
                <select name="req-unit" onchange="this.form.action='event-units-select-update.do';this.form.submit()">
                  <c:choose>
                     <c:when test="${empty sesEventData.webEntity.unit}">
                        <option selected value="----">Choose Unit</option>
                        <c:forEach var="unit" items="${sesEventData.units}">
                           <option value="${unit}">${unit}</option>
                        </c:forEach>
                     </c:when>
                     <c:otherwise>
                        <c:forEach var="unit" items="${sesEventData.units}">
                           <c:choose>
                              <c:when test="${unit== sesEventData.webEntity.unit}">
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
            
            
          
            </DIV>
         </DIV>
         <DIV class="row">
            <DIV class="col">
               <Label>Type:</Label>
            </DIV>
            <DIV class="col">
               <Label>Template:</Label>
            </DIV>
            <DIV class="col">&nbsp;</DIV>
         </DIV>
         <DIV class="row">
            <DIV class="col">
               <select name="req-event-type-id-select" onchange="this.form.action='event-event-type-select-update.do';this.form.submit()">
                  <c:choose>
                     <c:when test="${empty sesEventData.selectedEventTypeId}">
                        <option selected value="---">Choose EventType</option>
                        <c:forEach var="eventType" items="${sesEventData.eventTypes}">
                           <option value="${eventType.id}">${eventType.name}</option>
                        </c:forEach>
                     </c:when>
                     <c:otherwise>
                        <c:forEach var="eventType" items="${sesEventData.eventTypes}">
                           <c:choose>
                              <c:when test="${eventType.id == sesEventData.selectedEventTypeId}">
                                 <option selected value="${eventType.id}">${eventType.name}</option>
                              </c:when>
                              <c:otherwise>
                                 <option value="${eventType.id}">${eventType.name}</option>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach>
                     </c:otherwise>
                  </c:choose>
               </select>
            </DIV>
            <DIV class="col">
               <c:choose>
                  <c:when test="${empty sesEvent.eventTemplateId}">
                     <select name="req-event-template-id-select" disabled="disabled" onchange="this.form.action='event-event-template-select-update.do';this.form.submit()">
                  </c:when>
                  <c:otherwise>
                     <select name="req-event-template-id-select" onchange="this.form.action='event-event-template-select-update.do';this.form.submit()">
                  </c:otherwise>
               </c:choose>
               <c:choose>
                  <c:when test="${empty sesEvent.eventTemplateId}">
                     <option selected value="---">Choose EventTemplate</option>
                     <c:forEach var="eventTemplate" items="${sesEventTemplates}">
                        <option value="${eventTemplate.id}">${eventTemplate.name}</option>
                     </c:forEach>
                  </c:when>
                  <c:otherwise>
                     <c:forEach var="eventTemplate" items="${sesEventTemplates}">
                        <c:choose>
                           <c:when test="${eventTemplate.id == sesEvent.eventTemplateId}">
                              <option selected value="${eventTemplate.id}">${eventTemplate.name}</option>
                           </c:when>
                           <c:otherwise>
                              <option value="${eventTemplate.id}">${eventTemplate.name}</option>
                           </c:otherwise>
                        </c:choose>
                     </c:forEach>
                  </c:otherwise>
               </c:choose>
               </select>
            </DIV>
            <DIV class="col">&nbsp;</DIV>
         </DIV>
         <DIV class="row"></DIV>
         <DIV class="row">
            <DIV class="col">&nbsp;</DIV>
            <DIV class="col">&nbsp;</DIV>
            <DIV class="col">&nbsp;</DIV>
            <DIV class="col">
               <INPUT type="submit" value="Submit">
            </DIV>
         </DIV>
      </FORM>
   </div>
</fmt:bundle>
