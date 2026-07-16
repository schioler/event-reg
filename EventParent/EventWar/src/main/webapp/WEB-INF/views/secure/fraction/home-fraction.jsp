<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
<div class="container" style="width: 100%; border:solid red 1px; text-align: center;">
     <div class="container" style="border: solid red 0px; text-align: center;">
    <h2 >
      <fmt:message key="home.title" />
    </h2>
  </div>  
    <c:choose>
      <c:when test="${not empty sesFavorites}">
        <DIV class="  row bg-primary-subtle " style="text-align: left; border: solid blue 0px; padding:4px;">
          <div class="col-2" style="text-align:right;">&nbsp;</div>
          <div class="col-3" style="border: solid green 0px;">
            <fmt:message key="event-template.name" />
          </div>
          <div class="col-1" style="border: solid blue 0px;">
            <fmt:message key="event-template.dose" />
          </div>
          <div class="col-2" style="border: solid green 0px;">
            <fmt:message key="event-template.unit" />
          </div>
          <div class="col-4" style="border: solid red 0px;">&nbsp;</div>
        </div>
        
        <c:forEach var="event" items="${sesFavorites}">
          <form action="event-create.do" method="post" >
            <DIV class="row" style="text-align: left; padding:4px;">
              <div class="col-2" style="text-align:right;">
                  <input type="hidden" name="caller" value="favorites">
                <input type="hidden" name="login-id" value="${event.loginId}"> 
                <input type="hidden" name="req-parent-id" value="${event.parentId}">
                <input type="hidden" name="req-id" value="${event.id}">
                <input type="hidden" name="req-name" value="${event.name}">
                <input type="hidden" name="req-short-name" value="${event.shortName}">
                <input type="hidden" name="req-unit" value="${event.unit}">
                <input type="hidden" name="req-dose" value="${event.dose}">
                
                <input class="btn btn-secondary mb-05 " type="submit" value="<fmt:message key="save" />" />
                
              </div>
              <div class="col-3">
                <label>${event.name}</label>
              </div>
              <div class="col-1">
                <label>${event.dose}</label>
              </div>
              <div class="col-2" style="border: solid blue 0px;">
                <label style="border: solid green 0px;">${event.unit}</label>
              </div>
            <div class="col-4"></div>
            </DIV>
          </form>
        </c:forEach> 
      </c:when>
      <c:otherwise>
        <p>No Favourites</p>
      </c:otherwise>
    </c:choose>
  </DIV>  
</fmt:bundle>
