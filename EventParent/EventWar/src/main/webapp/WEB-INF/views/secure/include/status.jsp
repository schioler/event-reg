<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="status sys-color" >
  <DIV class="center-col-50-50">
    <DIV style="text-align: left;font-weight: bold;">
      <span style="font-weight: bold;">Status: </span> 
      <SPAN style="font-weight:normal; ">
      <c:choose>
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

    <DIV style="text-align: right;">
       <span style="font-weight: bold;">
      Hej ${ sesAuthenticatedToken}
      
      </SPAN>
       <span style="font-weight: normal;">
       <a href="logout.do"> <fmt:message key="menu.logout" /></a>
      
      </SPAN>
    </DIV>
  </DIV>


</div>

</fmt:bundle>