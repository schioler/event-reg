<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
		<DIV class="container" style="width: 100%; border: solid red 1px; text-align: center;">
				<div>
						<%
						String action = (String) request.getAttribute("action");
						%>
						<c:choose>
								<c:when test="${action eq 'create'}">
										<form action="./event-type-create.do" method="post">
												<%@ include file="event-type-active.jsp"%>
										</form>
								</c:when>
								<c:when test="${action eq 'update'}">
										<form action="./event-type-update.do" method="post">
												<%@ include file="event-type-active.jsp"%>
										</form>
								</c:when>
								<c:when test="${action eq 'delete'}">
										<form action="./event-type-delete.do" method="post">
												<%@ include file="event-type-inactive.jsp"%>
										</form>
								</c:when>
								<c:otherwise>
										<p>Type action=${param.action }</p>
								</c:otherwise>
						</c:choose>
				</div>
		</DIV>
</fmt:bundle>
