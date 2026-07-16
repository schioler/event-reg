<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<select name="req-select-event-template" onchange="this.form.action='select-event-template-update.do';this.form.submit()"  >
	<c:choose>
		<c:when test="${empty sesSelectedEventTemplateId}">
		
			<option selected value="---">Choose EventTemplate</option>
			<c:forEach var="eventTemplate" items="${sesSelectEventTemplates}">
				<option value="${eventTemplate.id}">${eventTemplate.name}</option>
   			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach var="eventTemplate" items="${sesSelectEventTemplates}">
				<c:choose>
					<c:when test="${eventTemplate.id == sesSelectedEventTemplateId}">
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
