<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
		<div class="container p-2" style="text-align: left;">
				<Div class="row p-2">
						<c:choose>
								<c:when test="${not empty sesEventType}">
										<input type="hidden" name="event-type-id" value="${sesEventType.id }" />
								</c:when>		
						</c:choose>
						<DIV class="col" style="text-align: right;">
								<label><fmt:message key="event-type.name" />:</label>
						</DIV>
						<DIV class="col">
								<input type="text" name="name" autofocus="autofocus" value="${sesEventType.name }" />
						</DIV>
				</div>
				<div class="row p-2">
						<DIV class="col"  style="text-align: right;">
								<label for="shortname"><fmt:message key="event-type.shortname" />:</label>
						</DIV>
						<DIV class="col">
								<input type="text" name="short-name" value="${sesEventType.shortName }">
						</DIV>
				</div>
				<div class="row p-2" >
						<div class="col" style="text-align: right;">
								<label for="description"><fmt:message key="event-type.description" />:</label>
						</div>
						<div class="col">
								<input type="text" id="description" name="description" value="${sesEventType.description }">
						</div>
				</div>
				<DIV class="row p-2" >
						<div class="col" style="text-align: right;">
								<label for="create"><fmt:message key="event-type.created" />:</label>
						</div>
						<DIV class="col" >
								<div>
										<input type="text" disabled="disabled" name="created" value="${sesEventType.created }">
								</div>
						</DIV>
				</div>
				<DIV class="row p-2">
						<DIV class="col" style="text-align: right;">
								<input type="submit" value='<fmt:message key="accept"/>' />
						</DIV>
						<DIV class="col">
								<input type="button" onclick="history.back()" value='<fmt:message key="cancel"/>' />
						</DIV>
				</DIV>
		</DIV>
</fmt:bundle>
