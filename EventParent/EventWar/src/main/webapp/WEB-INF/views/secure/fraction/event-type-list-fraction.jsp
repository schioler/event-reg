<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
   <div class="container p-2" style="border: solid red 0px; text-align: center;">
      <h2>
         <fmt:message key="event-type.list" />
      </h2>
   </div>
   <DIV class="container mx-auto " style="border:solid red 0px;">
      <DIV class="row p-1 bg-primary-subtle " style="border: solid blue 0px;">
         <div class="col-2 ">
            <form action="./event-type-create-show.do" method="post">
               <input class="header-text-btn" type="submit" name="new" value="<fmt:message key='create'/>" />
            </form>
         </div>
         <div class="col-3   ">
            <fmt:message key="event-type.name" />
         </div>
         <div class="col-2 ">
            <fmt:message key="event-type.shortname" />
         </div>
         <div class="col-2 ">
            <fmt:message key="event-type.description" />
         </div>
         <div class="col-2 ">
            <fmt:message key="entity.created" />
         </div>
         <div class="col-1 ">
            <fmt:message key="entity.owner" />
         </div>
      </div>
      
      <c:forEach var="type" items="${sesEventTypes}">
         <form action="./event-type-update-show.do" autocomplete="on" method="post">
            <div class="row p-2" style="border: solid blue 0px;">
               <div class="col-2">
                  <input type="hidden" name="req-event-type-id" value="${type.id}" /> 
                  <INPUT type="submit" name="delete" value="<fmt:message key='delete' />" formaction="./event-type-delete-show.do" />
                  <input type="submit" name="update" value="<fmt:message key='update' />" formaction="./event-type-update-show.do" />
               </div>
               <div class="col-3">${type.name}</div>
               <div class="col-2">${type.shortName}</div>
               <div class="col-2">${type.description }</div>
               <div class="col-2">${type.created }</div>
               <div class="col-1">${type.loginId}</div>
            </div>
         </form>
      </c:forEach>
   </div>
</fmt:bundle>
