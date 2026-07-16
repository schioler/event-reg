<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
  <div class="container" style="border: solid red 0px; text-align: center;">
    <h2>
      <fmt:message key="event-template.list" />
    </h2>
  </div>
  <div class="container">
    <c:choose>
      <c:when test="${not empty sesEventTypes}">
        <c:forEach var="item" items="${sesEventTypes}">
          <div class="container" style="width: 100%; border: solid blue 1px; text-align: left;">
            <details class="container" style="on-hover: width: 100%; border: solid green 1px; text-align: left;">
              <summary style="margin: 2px;">
                <span>EventType:</span> <span>${item.name }</span> <span>&nbsp;</span> <span>(${item.shortName })</span>
              </summary>
              <div class="container row border" style="borrder: solid red 1px;">
                <form action="./event-template-create-show.do" method="post">
                  <div class="container col">
                    <input type="submit" value="New Template"> <input type="hidden" id="req-event-type-id" name="req-event-type-id" value="${item.id }">
                    <input type="hidden" name="login-id" value="${item.loginId  }">
                  </div>
                </form>
                <DIV class="col">&nbsp;</DIV>
                <div class="col">Name</div>
                <div class="col">ShortName</div>
                <div class="col">Dose</div>
                <div class="col">Unit</div>
                <div class="col">Favorite</div>
                <div class="col">Sort order</div>
              </div>
              <c:forEach var="tmpl" items="${item.children}">
                <form action="./event-template-delete-show.do" method="post">
                  <div class="row">
                    <div class="col">
                      <input type="hidden" id="req-event-template-id" name="req-event-template-id" value="${tmpl.id }"> <input type="submit" name="edit"
                        value="edit" formaction="event-template-update-show.do"
                      /> <input type="submit" name="delete" value="delete" />
                    </div>
                    <div class="col">${tmpl.name}</div>
                    <div class="col">${tmpl.shortName }</div>
                    <div class="col">${tmpl.dose }</div>
                    <div class="col">${tmpl.unit}</div>
                    <div class="col">${tmpl.favorite}</div>
                    <div class="col">${tmpl.sortOrder}</div>
                  </div>
                </form>
              </c:forEach>
            </details>
          </div>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <p>No types/template to show</p>
      </c:otherwise>
    </c:choose>
  </DIV>
  <!-- EO PAGE SPECIFIC DATA 2-->
</fmt:bundle>