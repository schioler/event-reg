<c:if test="${ sesEventTemplates != null}">
	<table>
			<tr>
				<th class="event-col-1">&nbsp;</th>
				<th class="event-col-2">Event</th>
				<th class="event-col-3">Dose</th>
				<th class="event-col-4">Unit</th>
				<th class="event-col-5">alt date</th>
				<th class="event-col-6">alt time</th>
				<th class="event-col-7">Note</th>
			</tr>
		
		<c:forEach var="eventTemplate" items="${sesEventTemplates}">
				<tr>
					<form method="post" action="./event-queue-add.do">
						<td class="event-col-1">
						   <input type="hidden"	name="templateId" value="${eventTemplate.id}"> 
                     <input type="hidden" name="shortName" value="${eventTemplate.shortName}">
						   <input class="event-btn" type="submit" formaction="./event-insert.do" value="Gem" > 
						   <input  type="submit" value="Queue" >
						</td>
						<td class="event-col-2">
						   <input class="event-cnt-2"	type="text" name="name" value="${eventTemplate.name}">
						</td> 
						<td class="event-col-3">
						   <input class="event-cnt-3" type="text"  name="dose" value="${eventTemplate.dose}">
						</td>
						<td class="event-col-4">
						   <input class="event-cnt-4" type="text" name="unit"  value="${eventTemplate.unit}">
						</td>
						<td class="event-col-5">
						   <input class="event-cnt-5"	type="date" name="date">
						</td>
						<td class="event-col-6">
						   <input class="event-cnt-6"	type="time" name="time">
						</td>
						<td class="event-col-7">
						   <input class="event-cnt-7" type="text" name="note">
						</td>

					</form>
				</tr>
		</c:forEach>
	</table>
</c:if>
