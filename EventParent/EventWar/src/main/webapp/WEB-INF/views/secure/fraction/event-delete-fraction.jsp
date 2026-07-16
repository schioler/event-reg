<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
		<div class="container " style="text-align: center; border: solid green 2px;">
				<FORM action="event-delete.do" method="post">
						<DIV class="row" style="border: solid blue 0px;">
								<DIV class="col">
										<Label>Login:</Label>
								</DIV>
								<DIV class="col">
										<Label>Name:</Label>
								</DIV>
								<DIV class="col">
										<Label>Type:</Label>
								</DIV>
								<DIV class="col">
										<Label>Template:</Label>
								</DIV>

						</DIV>
						<DIV class="row"></DIV>
						<DIV class="row">
								<DIV class="col">
								    <INPUT type="text" name="event.login">										
								</DIV>
								<DIV class="col">
										<INPUT type="text" name="event.name">
								</DIV>
								<DIV class="col">
								    	  <INPUT type="text" name="event.typeId">
								</DIV>
								<DIV class="col">
								    <INPUT type="text" name="event.templateId">								    									
								</DIV>
						</DIV>
						<DIV class="row">
								<DIV class="col">
								       <Label>ShortName:</Label>										
								</DIV>
								<DIV class="col">
										<Label>Dose:</Label>										
								</DIV>
								<DIV class="col">
										<Label>Unit:</Label>
								</DIV>								
								<DIV class="col">
										<Label>Event Timestamp:</Label>                              
                        </DIV>
						</DIV>
						   <DIV class="row">
                        <DIV class="col">
                              <INPUT type="text" name="event.name-short">
                        </DIV>
                        <DIV class="col">
                              <INPUT type="text" name="event.dose" >
                        </DIV>
                        <DIV class="col">
                             <INPUT type="text" name="event.unit" >
                        </DIV>                        
                        <DIV class="col">
										<INPUT type="datetime-local" name="event.ts-start" value="event.event_ts">
                        </DIV>
                  </DIV>
						<DIV class="row">
								<DIV class="col">&nbsp;</DIV>
								<DIV class="col">&nbsp;</DIV>
								<DIV class="col">&nbsp;</DIV>
								<DIV class="col">
										<INPUT type="submit" value="Submit">
								</DIV>
						</DIV>
				</FORM>
		</div>
</fmt:bundle>
