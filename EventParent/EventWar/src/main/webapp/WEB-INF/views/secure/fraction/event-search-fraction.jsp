<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
   <DIV class="container text-center">
      <h2>
         <fmt:message key="event.search" />
      </h2>
   </div>
   <DIV class="container">
      <FORM class="row g-3" action="event-search.do" method="post">
         <input type="hidden" name="caller" value="10-event-search.jsp" /> <input type="hidden" name="req-login-id" value="sesEventSearch.loginId" />
         <DIV class="col-2">
            <LABEL>Timeline Start Date:</LABEL>
         </DIV>
         <DIV class="col-10">
            <%-- 								<%@ include file="../gui-related/select-count-date-time.jsp"%> --%>
            <INPUT type="datetime-local" name="req-timeline-startdate" required value="${sesEventSearchInput.timelineInput.formattedTimelineStart}">
         </DIV>
         <!--  -->
         <DIV class="col-md-2">
            <Label>Timeline Interval:</Label>
         </DIV>
         <DIV class="col-md-4">
            <%@ include file="../gui-related/select-timeslot-length.jsp"%> 
         </DIV>
         <DIV class="col-md-2">
            <Label>Timeline Count slots:</Label>
         </DIV>
         <DIV class="col-md-4">
             <%@ include file="../gui-related/select-count-1-12.jsp"%> 
         </DIV>
         <!--  -->
         <%-- <DIV class="col-md-2">
         >   <Label>Login-Id:</Label>
         </DIV>
         <DIV class="col-md-4">
            <INPUT type="text" name="event-login" disabled="disabled" readonly="readonly" value="${sesEventSearch}">
         </DIV> --%>
         <!--  -->
         <%-- <DIV class="col-md-2">
            <Label>Name:</Label>
         </DIV>
         <DIV class="col-md-4">
            <INPUT type="text" name="req-event-name" value="${sesEventSearchInput.name}">
         </DIV>
         <DIV class="col-md-2">
            <Label>ShortName:</Label>
         </DIV>
         <DIV class="col-md-4">
            <INPUT type="text" name="req-event-name-short" value="${sesEventSearchInput.shortName}">
         </DIV>
         <!--  -->
         <DIV class="col-md-2">
            <Label>Dose:</Label>
         </DIV>
         <DIV class="col-md-4">
            <INPUT type="number" name="req-event-dose" value="${sesEventSearchInput.dose}">
         </DIV>
         <DIV class="col-md-2">
            <Label>Unit:</Label>
         </DIV>
         <DIV class="col-md-4">
            <INPUT type="number" name="req-event-dose" value="${sesEventSearchInput.dose}">
            								<%@ include file="../gui-related/select-unit.jsp"%>
         </DIV> --%>
         <!--  -->
         <DIV class="col-12 text-align-right">
            <INPUT type="submit" value="Submit">
         </DIV>
      </FORM>
   </div>
</fmt:bundle>
