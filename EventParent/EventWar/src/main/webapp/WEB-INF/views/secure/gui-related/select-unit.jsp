<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%
String formUrl = (String) request.getAttribute("form-url");
out.print(formUrl);

FRA SELECT: onchange="this.form.action='"+formUrl + "';this.form.submit()"
%> --%>
<select name="req-select-unit" >
  <c:choose>
    <c:when test="${empty sesSelectedUnit}">
      <option selected value="---">Choose Unit</option>
      <c:forEach var="unit" items="${sesSelectUnits}">
        <option value="${unit}">${unit}</option>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <option value="-1">Do not use</option>
      <c:forEach var="unit" items="${sesSelectUnits}">
        <c:choose>
          <c:when test="${unit == sesSelectedUnit }">
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
