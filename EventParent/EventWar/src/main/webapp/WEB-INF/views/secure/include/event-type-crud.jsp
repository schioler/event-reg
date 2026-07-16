<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
   <form id="event_type" name="event_type" method="post" action="secure/event-type-delete.do">
      <div class="row rounded-3 heading-row">
         <DIV class="col-sm-2 heading-col ">&nbsp;</DIV>
         <DIV class="col-sm-8 heading-col">
            <div class="row bg-primary-subtle pb-3 pt-4 heading-row" style="border: solid red 0px;">
               <div class="col-sm-1 " style="border: solid blue 0px;">&nbsp;</div>
               <DIV class="col-sm-10 text-center heading-col" style="border: solid green 0px;">
                  <DIV class="row border-bottom border-secondary ">
                     <div class="col-sm-12 text-center bg-light">
                        <h1>
                           <fmt:message key="event-type.entity" />
                        </h1>
                        <h2>
                           <fmt:message key="${action }" />
                        </h2>
                     </DIV>
                  </DIV>
               </DIV>
               <div class="col-sm-1 ">&nbsp;</div>
            </div>
            <div class="row bg-primary-subtle" style="border: solid red 0px;">
               <div class="col-sm-1 " style="border: solid blue 0px;">&nbsp;</div>
               <DIV class="col-sm-10 " style="border: solid green 0px;">
                  <DIV class="row" style="border: solid yellow 0 px;">
                     <div class="col-sm-3 text-end bg-light">
                        <label class="m-1 " for="req-name"><B> <fmt:message key="event-type.name" /></B> : </label>
                     </div>
                     <DIV class="col-sm-9 text-start bg-light ">
                        <c:if test="${action eq 'create'}">
                           <input type="text" style="width: 100%; border: none;" name="req-name" id="req-name" autofocus="autofocus" value="${sesEventType.name }" />
                        </c:if>
                        <c:if test="${action eq 'update'}">
                           <input type="text" style="width: 100%; border: none;" name="req-name" id="req-name" autofocus="autofocus" value="${sesEventType.name }" />
                        </c:if>
                        <c:if test="${action eq 'delete'}">
                           <input type="text" style="width: 100%; border: none;" name="req-name" id="req-name" autofocus="autofocus" value="${sesEventType.name }" disabled="disabled" />
                        </c:if>
                     </DIV>
                  </DIV>
               </DIV>
               <div class="col-sm-1 ">&nbsp;</div>
            </div>
            <div class="row bg-primary-subtle " style="border: solid red 0px;">
               <div class="col-sm-1 " style="border: solid blue 0px;">&nbsp;</div>
               <DIV class="col-sm-10 " style="border: solid green 0px;">
                  <DIV class="row  " style="border: solid yellow 0px;">
                     <div class="col-sm-3 text-end bg-light">
                        <label class="m-1 " for="req-shortname"><B><fmt:message key="event-type.shortname" /></B> :</label>
                     </div>
                     <DIV class="col-sm-9 text-start bg-light  ">
                        <c:if test="${action eq 'create'}">
                           <input style="width: 100%; border: none;" type="text" name="req-short-name" id="req-short-name" value="${sesEventType.shortName }">
                        </c:if>
                        <c:if test="${action eq 'update'}">
                           <input style="width: 100%; border: none;" type="text" name="req-short-name" id="req-short-name" value="${sesEventType.shortName }">
                        </c:if>
                        <c:if test="${action eq 'delete'}">
                           <input style="width: 100%; border: none;" type="text" disabled="disabled" name="req-short-name" id="req-short-name" value="${sesEventType.shortName }">
                        </c:if>
                     </DIV>
                  </DIV>
               </DIV>
               <div class="col-sm-1 ">&nbsp;</div>
            </div>
            <div class="row bg-primary-subtle " style="border: solid red 0px;">
               <div class="col-sm-1 " style="border: solid blue 0px;">&nbsp;</div>
               <DIV class="col-sm-10 " style="border: solid green 0px;">
                  <DIV class="row" style="border: solid yellow 0px;">
                     <div class="col-sm-3 text-end bg-light">
                        <label class="m-1" for="req-description"><B><fmt:message key="event-type.description" /></B> :</label>
                     </div>
                     <DIV class="col-sm-9 text-start bg-light  ">
                        <c:if test="${action eq 'create'}">
                           <input style="width: 100%; border: none;" type="text" id="req-description" name="req-description" value="${sesEventType.description }">
                        </c:if>
                        <c:if test="${action eq 'update'}">
                           <input style="width: 100%; border: none;" type="text" id="req-description" name="req-description" value="${sesEventType.description }">
                        </c:if>
                        <c:if test="${action eq 'delete'}">
                           <input style="width: 100%; border: none;" type="text" id="req-description" name="req-description" value="${sesEventType.description }" disabled="disabled" s>
                        </c:if>
                     </DIV>
                  </DIV>
               </DIV>
               <div class="col-sm-1 ">&nbsp;</div>
            </div>
            <div class="row bg-primary-subtle " style="border: solid red 0px;">
               <div class="col-sm-1 " style="border: solid blue 0px;">&nbsp;</div>
               <DIV class="col-sm-10 " style="border: solid green 0px;">
                  <DIV class="row m-3 " style="border: solid yellow 0px;">
                     <div class="col-sm-12 text-center ">
                        <c:choose>
                           <c:when test="${action eq 'update'}">
                              <input type="submit" class="btn btn-success rounded-3  border " style="width: 6em;" onclick="this.form.action='event-type-update.do';this.form.submit();"
                                 value='<fmt:message key="accept"/>'
                              />
                              <input type="hidden" name="req-event-type-id" id="req-event-type-id" value="${sesEventType.id}" />
                              <input type="hidden" name="req-login-id" value="${sesEventType.loginId}" />
                              <input type="hidden" name="caller" value="02-event-type-update.jsp" />
                           </c:when>
                           <c:when test="${action eq 'create'}">
                              <input type="submit" class="btn btn-success rounded-3  border " style="width: 6em;" onclick="this.form.action='event-type-create.do';this.form.submit();"
                                 value='<fmt:message key="accept"/>'
                              />
                              <input type="hidden" name="req-login-id" value="${sesEventType.loginId}" />
                              <input type="hidden" name="caller" value="02-event-type-create.jsp" />
                           </c:when>
                           <c:when test="${action eq 'delete'}">
                              <input type="submit" class="btn btn-success rounded-3  border " style="width: 6em;" onclick="this.form.action='event-type-delete.do';this.form.submit();"
                                 value='<fmt:message key="accept"/>' 
                              />
                                 <input type="hidden" name="req-event-type-id" id="req-event-type-id" value="${sesEventType.id}" />
                              <input type="hidden" name="req-login-id" value="${sesEventType.loginId}" />
                              <input type="hidden" name="caller" value="02-event-type-delete.jsp" />
                           </c:when>
                           <c:otherwise>
                           </c:otherwise>
                        </c:choose>
                        <input type="button" class="btn bg-secondary text-light rounded-3  border" style="width: 6em;" onclick="history.back()" value='<fmt:message key="cancel"/>' />
                     </DIV>
                  </DIV>
               </DIV>
               <div class="col-sm-1 ">&nbsp;</div>
            </div>
         </DIV>
         <DIV class="col-sm-2 heading-col">&nbsp;</DIV>
      </div>
   </form>
</fmt:bundle>