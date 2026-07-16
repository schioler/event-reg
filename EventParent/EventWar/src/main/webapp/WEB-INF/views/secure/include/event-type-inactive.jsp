<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<Div class="center-col-50-50">
  <input type="hidden" name="event-type-id" value="${sesEventType.id }" />
  <DIV class="center-col-50-50-left">
    <div>
      <div>
        <label><fmt:message key="event-type.name" />:</label>
      </div>
    </div>
  </DIV>
  <DIV class="center-col-50-50-right">
    <div>
      <input type="text" disabled="disabled" name="name" autofocus="autofocus" value="${sesEventType.name }" />
    </div>
  </DIV>

  <DIV class="center-col-50-50-left">
    <div>
      <label for="shortname"><fmt:message key="event-type.shortname" />:</label>
    </div>
  </DIV>
  <DIV class="center-col-50-50-right">
    <div>
      <input type="text" disabled="disabled" name="short-name" value="${sesEventType.shortName }">
    </div>
  </DIV>



  <div class="center-col-50-50-left">
    <label for="description"><fmt:message key="event-type.description" />:</label>
  </div>
  <div class="center-col-50-50-right">
    <input type="text" id="description" disabled="disabled" name="description" value="${sesEventType.description }">
  </div>


  <DIV class="center-col-50-50-left">
    <div>
      <label for="create"><fmt:message key="event-type.created" />:</label>
    </div>
  </DIV>
  <DIV class="center-col-50-50-right">
    <div>
      <input type="text" disabled="disabled" name="created" value="${sesEventType.created }">
    </div>
  </DIV>


  <DIV class="center-col-50-50-left">
    <div>&nbsp;</div>
  </DIV>
  <DIV class="center-col-50-50-right">
    <div>
      <input type="submit" value='<fmt:message key="accept"/>' /> <input type="button" onclick="history.back()"
        value='<fmt:message key="cancel"/>' />

    </div>
  </DIV>
</DIV>



