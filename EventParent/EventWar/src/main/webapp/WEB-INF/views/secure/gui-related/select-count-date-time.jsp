<%@page import="java.time.Month"%>
<%@page import="java.time.LocalDate"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <DIV class="container"> -->

<select name="req-select-year" onchange="this.form.action='select-year-update.do';this.form.submit()">
		<c:choose>
				<c:when test="${empty sesSelectedYear}">
						<option selected value="---">Choose Year</option>
						<c:forEach var="year" items="${'2024,2025,2026'}">
								<option value="${year}">${year}</option>
						</c:forEach>
				</c:when>
				<c:otherwise>
						<c:forEach var="year" items="${'2024,2025,2026'}">
								<c:choose>
										<c:when test="${year == sesSelectedYear}">
												<option selected value="${year}">${year}</option>
										</c:when>
										<c:otherwise>
												<option value="${year}">${year}</option>
										</c:otherwise>
								</c:choose>
						</c:forEach>
				</c:otherwise>
		</c:choose>
</select>
<select name="req-select-day" onchange="this.form.action='select-day-update.do';this.form.submit()">

		<c:choose>
				<c:when test="${empty sesSelectedDay }">
						<option selected value="---">Choose Day</option>
						<c:forEach var="day" items="${sesSelectDays}">
								<option value="${day}">${day}</option>
						</c:forEach>
				</c:when>
				<c:otherwise>
						<c:forEach var="day" items="${sesSelectDays}">
								<c:choose>
										<c:when test="${day == sesSelectedDay}">
												<option selected value="${day}">${day}</option>
										</c:when>
										<c:otherwise>
												<option value="${day}">${day}</option>
										</c:otherwise>
								</c:choose>
						</c:forEach>
				</c:otherwise>
		</c:choose>
</select>
<select name="req-select-hour" onchange="this.form.action='select-hour-update.do';this.form.submit()">
		<c:choose>
				<c:when test="${empty sesSelectedHour }">
						<option selected value="---">Choose Hour</option>
						<c:forEach var="hour" items="${sesSelectHours}">
								<option value="${hour}">${hour}</option>
						</c:forEach>
				</c:when>
				<c:otherwise>
						<c:forEach var="hour" items="${sesSelectHours}">
								<c:choose>
										<c:when test="${hour == sesSelectedHour}">
												<option selected value="${hour}">${hour}</option>
										</c:when>
										<c:otherwise>
												<option value="${hour}">${hour}</option>
										</c:otherwise>
								</c:choose>
						</c:forEach>
				</c:otherwise>
		</c:choose>
</select>
<select name="req-select-minute" onchange="this.form.action='select-minute-update.do';this.form.submit()">
		<c:choose>
				<c:when test="${empty sesSelectedMinute }">
						<option selected value="---">Choose Minute</option>
						<c:forEach var="minute" items="${sesSelectMinutes}">
								<option value="${minute}">${minute}</option>
						</c:forEach>
				</c:when>
				<c:otherwise>
						<c:forEach var="minute" items="${sesSelectMinutes}">
								<c:choose>
										<c:when test="${minute == sesSelectedMinute}">
												<option selected value="${minute}">${minute}</option>
										</c:when>
										<c:otherwise>
												<option value="${minute}">${minute}</option>
										</c:otherwise>
								</c:choose>
						</c:forEach>
				</c:otherwise>
		</c:choose>
</select>
<!-- </div> -->