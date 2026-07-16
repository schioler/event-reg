<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<select name="req-timeline-interval" onchange="this.form.action='timeline-update.do';this.form.submit()"  >
	<c:choose>
		<c:when test="${empty sesEventSearchInput.selectedInterval }">
			<option selected value="---">Choose Timeslot Length</option>
			<c:forEach var="timeslot" items="${sesEventSearchInput.timeslots}">
				<option value="${timeslot}">${timeslot}</option>
   			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach var="timeslot" items="${sesEventSearchInput.timeslots}">
				<c:choose>
					<c:when test="${timeslot == sesEventSearchInput.selectedInterval }">
						<option selected value="${timeslot}">${timeslot}</option>
					</c:when>
					<c:otherwise>
						<option value="${timeslot}">${timeslot}</option>
					</c:otherwise>
				</c:choose>   
			</c:forEach>
		</c:otherwise>
	</c:choose>

</select>
