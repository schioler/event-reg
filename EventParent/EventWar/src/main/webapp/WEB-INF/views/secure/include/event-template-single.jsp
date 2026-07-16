<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
      <DIV class="container-fluid" style="width: 100%; border: solid red 2px;">
            <div  >
                  <%
                     String action = (String) request.getAttribute("action");
                  %>
                  <c:choose>
                        <c:when test="${action eq 'create'}">
                              <form action="./event-template-create.do" method="post">
                                    <%@ include file="event-template-active.jsp"%>
                              </form>
                        </c:when>
                        <c:when test="${action eq 'update'}">
                              <form action="./event-template-update.do" method="post">
                                    <%@ include file="event-template-active.jsp"%>
                              </form>
                        </c:when>
                        <c:when test="${action eq 'delete'}">
                              <form action="./event-template-delete.do" method="post">
                                    <%@ include file="event-template-inactive.jsp"%>
                              </form>
                        </c:when>
                        <c:otherwise>
                              <p>Type action=${param.action }</p>
                        </c:otherwise>
                  </c:choose>
            </div>
      </DIV>
</fmt:bundle>





