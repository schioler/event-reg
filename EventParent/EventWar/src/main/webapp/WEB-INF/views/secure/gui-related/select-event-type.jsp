<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<select name="req-select-event-type" onchange="this.form.action='select-event-type-update.do';this.form.submit()"  >
   <c:choose>
		<c:when test="${empty sesEventSearchInput.eventTypeIdSelected }">
			<option selected value="---">Choose EventType</option>
   			<c:forEach var="eventType" items="${sesEventSearchInput.eventTypes}">
				<option value="${eventType.id}">${eventType.name}</option>
   			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach var="eventType" items="${sesEventSearchInput.eventTypes}">
				<c:choose>    
					<c:when test="${eventType.id == sesEventSearchInput.eventTypeIdSelected }">
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
      