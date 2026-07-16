<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
		<DIV class="container text-center">
				<h2>
						<fmt:message key="event-search-result" />
				</h2>
		</div>
		<div class="container " style="text-align: center; border: solid green 2px;">
				<DIV class="row" style="border: solid blue 1px;">
						<DIV class="col-md-2"  >
								<Label>Event TS:</Label>
						</DIV>
						<DIV class="col-md-2">
								<Label>Login</Label>
						</DIV>
						<DIV class="col-md-2">
								<Label>Type</Label>
						</div>
						<DIV class="col-md-2">
								<Label>Name</Label>
						</DIV>
						<DIV class="col-md-2">
								<Label>Unit:</Label>
						</DIV>
						<DIV class="col-md-2">
								<Label>Dose:</Label>
						</DIV>
						
				</DIV>
				<DIV class="row" style="border: solid blue 1px;">
						<c:forEach var="event" items="${sesEventSearchResult}">
								<DIV class="col-md-2"> ${event.eventTS }</DIV>
								<DIV class="col-md-2">${event.loginId }</DIV>
								<DIV class="col-md-2">${event.parentId    }</div>
								<DIV class="col-md-2">${event.name }</DIV>
								<DIV class="col-md-2">${event.unit }</DIV>
								<DIV class="col-md-2">${event.dose }</DIV>
						
						</c:forEach>
				</DIV>
		</DIV>
</fmt:bundle>
`
<!-- 			<DIV class="col"> -->
<!-- 							<INPUT type="text" name="event.login"> -->
<!-- 					</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<select name="event.type.id-select"> -->
<%-- 									<c:choose> --%>
<%-- 											<c:when test="${empty sesEventTypeIdSelected }"> --%>
<!-- 													<option selected value="---">Choose EventType</option> -->
<%-- 													<c:forEach var="eventType" items="${sesEventTypeSelectable}"> --%>
<%-- 															<option value="${eventType.id}">${eventType.name}</option> --%>
<%-- 													</c:forEach> --%>
<%-- 											</c:when> --%>
<%-- 											<c:otherwise> --%>
<%-- 													<c:forEach var="eventType" items="${sesEventTypeSelectable }"> --%>
<%-- 															<c:choose> --%>
<%-- 																	<c:when test="${eventType.id == sesEventTypeSelectedId }"> --%>
<%-- 																			<option selected value="${eventType.id}">${eventType.name}</option> --%>
<%-- 																	</c:when> --%>
<%-- 																	<c:otherwise> --%>
<%-- 																			<option value="${eventType.id}">${eventType.name}</option> --%>
<%-- 																	</c:otherwise> --%>
<%-- 															</c:choose> --%>
<%-- 													</c:forEach> --%>
<%-- 											</c:otherwise> --%>
<%-- 									</c:choose> --%>
<!-- 							</select> -->
<!-- 					</DIV> -->
<!-- 			</DIV> -->
<!-- 			<DIV class="row"></DIV> -->
<!-- 			<DIV class="row"> -->
<!-- 					<DIV class="col"> -->
<!-- 							<Label>Name:</Label> -->
<!-- 					</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<INPUT type="text" name="event.name"> -->
<!-- 					</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<Label>ShortName:</Label> -->
<!-- 					</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<INPUT type="text" name="event.name-short"> -->
<!-- 					</DIV> -->
<!-- 			</DIV> -->
<!-- 			<DIV class="row"> -->
<!-- 					<DIV class="col"> -->
<!-- 							<Label>Dose:</Label> -->
<!-- 					</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<INPUT type="text" name="event.dose" readonly="readonly"> -->
<!-- 					</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<Label>Unit:</Label> -->
<!-- 					</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<INPUT type="text" name="event.unit" readonly="readonly"> -->
<!-- 					</DIV> -->
<!-- 			</DIV> -->
<!-- 			<DIV class="row"> -->
<!-- 					<DIV class="col"> -->
<!-- 							<Label>Start Date:</Label> -->
<!-- 					</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<INPUT type="datetime-local" name="event.ts-start"> -->
<!-- 					</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<Label>End Date:</Label> -->
<!-- 					</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<INPUT type="datetime-local" name="event.ts-end"> -->
<!-- 					</DIV> -->
<!-- 			</DIV> -->
<!-- 			<DIV class="row"> -->
<!-- 					<DIV class="col">&nbsp;</DIV> -->
<!-- 					<DIV class="col"> -->
<!-- 							<INPUT type="submit" value="Submit"> -->
<!-- 					</DIV> -->
<!-- 			</DIV> -->
<!-- 		</div> -->
<%-- </fmt:bundle> --%>
