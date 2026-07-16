<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container bg-primary-subtle" style="border: solid red 0px; text-align: center;">
		<div class="dropdown pb-2 pt-2" style="text-align: rightr; border: solid red 1spx;">
<!-- 		       Login -->
				<button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown">
						<fmt:message key="menu.login" />
				</button>
				<ul class="dropdown-menu">
						<li><a href="login-list-show.do" class="dropdown-item"><fmt:message key="menu.login.list.show" /></a></li>
						<li><a href="login-search-show.do" class="dropdown-item"><fmt:message key="menu.login.search.show" /></a></li>
						<li><a href="login-tree-show.do" class="dropdown-item"><fmt:message key="menu.login.tree.show" /></a></li>
				</ul>
<!-- 				Event Type -->
				<button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown">
						<fmt:message key="menu.event-type" />
				</button>
				<ul class="dropdown-menu">
						<li><a href="event-type-list-show.do" class="dropdown-item"> <fmt:message key="menu.event-type.list.show" /></a></li>
				</ul>
<!-- 				Event Template -->
				<button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown">
						<fmt:message key="menu.event-template" />
				</button>
				<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="event-template-list-show.do"><fmt:message key="menu.event-template.list.show" /></a></li>
				</ul>
<!-- 				Event -->
				<button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown">
						<fmt:message key="menu.event" />
				</button>
				<ul class="dropdown-menu">   
						<li><a class="dropdown-item" href="event-search-show.do"><fmt:message key="menu.event.search" /> </a></li>
						<li><a class="dropdown-item" href="event-create-show.do"><fmt:message key="menu.event.new" /> </a></li>
				</ul>
				<button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown">
						<fmt:message key="menu.favorites" />
				</button>
				<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="favorites-show.do"><fmt:message key="menu.favorites.show" /> </a></li>
				</ul>
				<button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown">
						<fmt:message key="menu.report" />
				</button>
				<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="event-search-show.do" ><fmt:message key="menu.report.search" /></a></li>
						<li><a class="dropdown-item" href="report-search-show.dk"> <fmt:message key="menu.report.list.show" /></a></li>
				</ul>
		</div>
</DIV>
<div class="container bg-primary-subtle" style="border: solid red 0px;">
		<DIV class="container row" style="border: solid red 12;">
				<DIV class="col">
						<span> Status: </span> <SPAN> <c:choose>
										<c:when test="${ sesStatusMsgList != null}">
												<c:forEach var="msg" items="${sesStatusMsgList }">
														<span>${ msg}</span>
												</c:forEach>
										</c:when>
										<c:otherwise>
         No message is available
      </c:otherwise>
								</c:choose>
						</SPAN>
				</DIV>
				<DIV class="col" style="text-align: right;">
						Hej ${ sesAuthenticatedUser.login.token} <span> <a href="logout.do"> <fmt:message key="menu.logout" /></a>
						</SPAN>
				</DIV>
		</DIV>
</div>
