<select name="reqEventTypeId" id="reqEventTypeId"
	onchange="this.form.submit()">
	<c:choose>
		<c:when test="${empty sesSelectedEventTypeId }">
			<option selected value="---">Choose EventType</option>
			<c:forEach var="eventType" items="${sesEventTypes}">
				<option value="${eventType.id}">${eventType.name}</option>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach var="eventType" items="${sesEventTypes}">
				<c:choose>
					<c:when test="${eventType.id == sesSelectedEventTypeId }">
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
