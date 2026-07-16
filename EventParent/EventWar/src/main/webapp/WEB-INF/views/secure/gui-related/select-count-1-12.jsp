<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<select name="req-timeline-count" onchange="this.form.action='timeline-update.do';this.form.submit()">
		<c:choose>
				<c:when test="${-1 eq sesEventSearchInput.selectedCount }">
						<option selected value="-1">Select Count</option>
						<c:forEach var="count" items="${'1,2,3,4,5,6,7,8,9,10,11,12'}">
								<option value="${count}">${count}</option>
						</c:forEach>
				</c:when>
				<c:otherwise>
						<c:forEach var="count" items="${'-1,1,2,3,4,5,6,7,8,9,10,11,12'}">
								<c:choose>
										<c:when test="${count == sesEventSearchInput.selectedCount }">
												<option selected value="${count}">${count}</option>
										</c:when>
										<c:otherwise>
												<option value="${count}">${count}</option>
										</c:otherwise>
								</c:choose>
						</c:forEach>
				</c:otherwise>
		</c:choose>
</select>
