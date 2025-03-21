<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="footer">
	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">
		<c:choose>
			<c:when test="${not empty sesStatusMessageList}">
			   <c:forEach var="msg" items="${sesStatusMessageList}">
			          <div ><label>${msg}</label></div>
			   </c:forEach>
			</c:when>
			<c:otherwise>
				<p>No Messages</p>
			</c:otherwise>
		</c:choose>
	</fmt:bundle>
</div>
