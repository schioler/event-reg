<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
		<div class="container p-2" style="border: solid red 0px; text-align: center;">
				<h2>
						<fmt:message key="event-type.create" />
				</h2>
		</div>
		<DIV class="container">
				<c:choose>
						<c:when test="${not empty sesEventTypes}">
								<DIV class="container row bg-primary-subtle" style="text-align: left; padding: 4px; border: solid blue 0px;">
										<!--           <div class="col-1 va-m">&nbsp;</div> -->
										<div class="col-2 va-m">
												<form action="./event-type-create-show.do" method="post">
														<input class="header-text-btn" type="submit" name="new" value="<fmt:message key='create'/>" />
												</form>
										</div>
										<div class="col-4 va-m">
												<fmt:message key="event-type.name" />
										</div>
										<div class="col-3 va-m">
												<fmt:message key="event-type.shortname" />
										</div>
										<div class="col-3 va-m">
												<fmt:message key="event-type.description" />
										</div>
								</div>
								<c:forEach var="type" items="${sesEventTypes}">
										<form action="./event-type-update-show.do" autocomplete="on" method="post">
												<div class="row" style="padding: 4px;">
														<div class="col-2">
																<input type="hidden" name="id" value="${type.id}" /> <input type="submit" name="update" value="<fmt:message key='update'/>" /> <input
																		type="submit" name="delete" value="<fmt:message key='delete' />" formaction="./event-type-delete-show.do"
																/>
														</div>
														<div class="col-4">${type.name}</div>
														<div class="col-3">${type.shortName}</div>
														<div class="col-3">${type.description }</div>
												</div>
										</form>
								</c:forEach>
						</c:when>
						<c:otherwise>
								<p>No Favourites</p>
						</c:otherwise>
				</c:choose>
		</div>
</fmt:bundle>
