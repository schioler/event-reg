<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%
Map<String, String> values = new TreeMap<String, String>();

Enumeration<String> e = session.getAttributeNames();

while (e.hasMoreElements()) {
   String key = e.nextElement();
   Object obj = session.getAttribute(key);

   if (obj instanceof String) {
      values.put(key, (String) obj);
   } else if (obj instanceof Iterable) {
      String val = "";
      Iterable it = (Iterable) obj;
      for (Object object : it) {
   val = val + "<br>" + object.toString();
      }
      values.put(key, val);
   } else {
      values.put(key, obj.toString());
   }
}
%>
<div class="container" style="border: solid red 1px; margin: 0vw;; padding: 0vw;">
   <TABLE class="table table-striped ">
      <THEAD>
         <TR>
            <TD>Session.Key</TD>
            <TD>Session.Value</TD>
         </TR>
      </THEAD>
      <TBODY>
         <%
         for (String k : values.keySet()) {
         %>
         <TR>
            <Td style="text-align: right; border: solid 1px green;">
               <%
               out.print(k);
               %>
            </Td>
            <TD style="text-align: left; border: solid 1px blue;">
               <%
               String s = "";
               Object v = values.get(k);
               /* if (v instanceof Map){
                  Map
               }else */
               if (v instanceof Iterable) {
                  Iterable i = (Iterable) v;
                  for (Object o : i) {
                     s = "<br>" + o.toString();
                  }

               } else {
                  s = v.toString();
               }
               out.print(s);
               %>
            </TD>
         </TR>
         <%
         }
         %>
      </TBODY>
   </TABLE>
</div>
