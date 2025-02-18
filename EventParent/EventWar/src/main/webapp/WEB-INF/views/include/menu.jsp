<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">
		<ul>
	<%-- 		<li><a href="event-type-list-show.do"> <fmt:message
						key="status.title" />
			</a></li> --%>
			<li><a href="event-type-list-show.do"> <fmt:message
						key="event-type.menu" />
			</a></li>
			<li><a href="event-tmpl-list-show.do  "> <fmt:message
						key="event-template.menu" />
			</a></li>
			<li><a href="search-new-show.do"> <fmt:message
						key="search.menu" />
			</a></li>
			<li><a href="favorites-show.do"> <fmt:message
						key="favorites.title" />
			</a></li>
			<li><a href="login-tree-show.do"> <fmt:message key="user.title" />
			</a></li>

			<li><a href="status-list-show.do"> <fmt:message key="status-list" />
			</a></li>
			<li style="float: right"><a href="./logout.do"> <fmt:message
						key="logout" />
			</a></li>


		</ul>

	</fmt:bundle>
</div>

