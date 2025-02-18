<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="border-color: black ; border-style: solid; border-width: 1px; border-radius: 8px; margin: 3px; padding:5px;">
<!--    <hr> -->
   <!-- <p>Status:</p> -->
	<c:forEach var="msg" items="${sesStatusMessageList }">
		<span class="span-status" >${ msg}</span>
	</c:forEach>
	<!-- <hr> -->
</div>

