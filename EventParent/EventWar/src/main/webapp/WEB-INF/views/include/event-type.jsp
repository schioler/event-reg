<!-- <fmt:bundle basename="dk.schioler.event.base.resources.EventListResources"> -->
	<table>
		<tr>
			<input type="hidden" name="id" value="${sesEventType.id }">
			<td><label for="name"><fmt:message key="event-type.name" /></label></td>
			<td><input type="text" name="name" value="${sesEventType.name }">
			</td>
		</tr>
		<tr>
			<td><label for="shortName"><fmt:message
						key="event-type.shortname" /></label></td>
			<td><input type="text" name="shortName"
				value="${sesEventType.shortName}"></td>
		</tr>
		<tr>
			<td><label for="description"><fmt:message
						key="event-type.description" /></label></td>
			<td><input type="text" name="description"
				value="${sesEventType.description }"></td>
		</tr>
		<tr>
			<td><fmt:message key="btn.ok" /></td>
			<td><input type="submit" value="Sure" /></td>
	  </tr>
</table>

<!-- </fmt:bundle> -->



