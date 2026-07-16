<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="mb-3 mt-3">
  <label for="req-name" class="form-label"><fmt:message key="event-template.name" />:</label> 
  <input type="text" class="form-control" id="req-name" name="req-name" autofocus="autofocus" value="${sesEventTemplate.name }" />
</div>
<div class="mb-3">
  
      <label class="form-label" for="shortname"><fmt:message key="event-template.shortname" />:</label>
      <input type="text" class="form-control" id="req-short-name" name="req-short-name" value="${sesEventTemplate.shortName }">
    </div>

<div class="mb-3">

    <label for="req-dose" class="form-label"><fmt:message key="event-template.dose" />:</label>
    <input type="number" class="form-control" name="req-dose" id="req-dose" value="${sesEventTemplate.dose}">
</div>

<div class="mb-3">
 
    <label for="req-unit" class="form-label" ><fmt:message key="event-template.unit" />:</label>
      <% request.setAttribute("form-url", "/secure/event-template-select-unit-update.do");  %>>
    <%@ include file="../gui-related/select-unit.jsp"%>
 </div>
<div class="mb-3">

    <label class="form-label" for="req-description"><fmt:message key="event-template.description" />:</label>
    <input type="text" class="form-control" id="req-description" name="req-description" value="${sesEventTemplate.description }">
 </div>
 <div class="mb-3">

    <label class="form-label" for="req-is-favorite"><fmt:message key="event-template.is.favorite" />:</label>
    <c:choose>
      <c:when test="${sesEventTemplate.isFavorite == true }">
        <select class="form-control" name="req-is-favorite" id="req-is-favorite">
          <option selected>True</option>
          <option>False</option>
        </select>
      </c:when>
      <c:otherwise>
        <select name="req-is-favorite" id="is-favorite">
          <option value="true">True</option>
          <option selected>False</option>
        </select>
      </c:otherwise>
    </c:choose>
 </div>
 
 <div class="mb-3">

    <label class="form-label" for="req-sortorder"><fmt:message key="event-template.sortorder" />:</label>
    <input type="text" id="req-sort-order" name="req-sort-order" value="${sesEventTemplate.sortOrder}">
  </div>
      <input class="btn btn-primary" type="submit" value='<fmt:message key="accept"/>' /> 
      <input type="button" class="btn btn-primary" onclick="history.back()" value='<fmt:message key="cancel"/>' />
