<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
		<div class="container bg-primary-subtle mt-2x" style="border: solid red 0px; text-align: center;">
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
		</div>
		<hr>
		<div class="container bg-warning" style="border: solid red 1px; text-align: center;">
				<DIV>
						<c:choose>
								<c:when test="${sesShowSessionVariables}">
										<DIV>
												<%@ include file="print-ses-vars.jsp"%>
										</DIV>
								</c:when>
								<c:otherwise>
                                We'll see sesVars another time.....
                         </c:otherwise>
						</c:choose>
				</DIV>
		</div>
</fmt:bundle>
