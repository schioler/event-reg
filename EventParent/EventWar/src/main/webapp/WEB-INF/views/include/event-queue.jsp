<c:if test="${ sesEventQueue != null}">
	<table>
			<tr>
				<form action="event-queue-store.do" method="POST">
				<th class="event-col-1">
				  <input type="submit" value="Save Q">
				</th>
				</form>
				<th class="event-col-2">Text</th>
				<th class="event-col-3">Dose</th>
				<th class="event-col-4">Unit</th>
				<th class="event-col-5">Date</th>
				<th class="event-col-6">Time</th>
				<th class="event-col-7">Note</th>
			</tr>
		
		<c:forEach var="event" items="${sesEventQueue}">
				<tr>
				<form method="post" action="event-queue-remove.do" >
						<td class="event-col-1">
						   <input type="submit" value="Rmov">
						</td>
						<td class="event-col-2">
						   <input class="event-cnt-2" type="text" name="name" value="${event.name}">
						   <input type="hidden" name="templateId" value="${event.eventTemplateId}">
						</td>
						<td class="event-col-3">
						   <input class="event-cnt-3" type="text" name="dose" value="${event.dose}">
						</td>
						<td class="event-col-4">
						      <input class="event-cnt-4" type="text" name="unit" value="${event.unit}">
						</td>
						<td class="event-col-5">
						   <input class="event-cnt-5" type="text"  name="eventTS" value="${event.eventTS}">
						</td>
						<td class="event-col-6">
						   <input class="event-cnt-6" type="text"  name="note" value="${event.note}">
						   
						</td>

						<td class="event-col-7">
						   &nbsp;   

						</td>
						</form>
				</tr>
		</c:forEach>
	</table>
</c:if>
