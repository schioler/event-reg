<%@page import="java.util.Enumeration"%>
<%@page import="dk.schioler.event.web.entity.EventSearchCriteria"%>
<table>
	<%
	String crit = "sesSearchCriteria";
		EventSearchCriteria search = (EventSearchCriteria) session.getAttribute(crit);

		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String string2 = (String) attributeNames.nextElement();
	%>
	<tr>
		<td>
			<%
			out.print(string2);
			%>
		</td>
		<td>
			<%
			out.print(session.getAttribute(string2));
			%>
		</td>
	</tr>
	<%
	}
	%>

</table>
