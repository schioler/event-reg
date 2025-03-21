<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="center-area-col-100">
   <div>
      EVENT TYPE 
   </div>
</div>

<div class="body-col-33-34-33">
   <div>&nbsp;</div>
   <div>
			<Div class="center-col-50-50">
			   
			   <DIV class="center-col-50-50-left">
			     	<input type="hidden" name="event-type-id"	value="${sesEventType.id }">
			     	 <input type="hidden" name="event-type-id"   value="${sesEventType.loginId}">
					<div ><label for="name"><fmt:message key="event-type.name" />:</label></div>
			   </DIV>
			   <DIV class="center-col-50-50-right">
				     <div  >
			        <input type="text" name="name" value="${sesEventType.name }">
				     </div>
			   </DIV>
			   
			    <DIV class="center-col-50-50-left">
                 <div ><label for="shortname"><fmt:message key="event-type.shortname" />:</label></div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div >
                 <input type="text" name="short-name" value="${sesEventType.shortName }"/>
                 </div>       
            </DIV>
            
             <DIV class="center-col-50-50-left">
                 <div ><label for="description"><fmt:message key="event-type.description" />:</label></div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div  >
                 <input type="text" name="description" value="${sesEventType.description }">
                 </div>        
            </DIV>
            
            <DIV class="center-col-50-50-left">
                 <div >
                 
                 </div>
            </DIV>
            <DIV class="center-col-50-50-right">
                 <div >
                     <input type="submit"  value='<fmt:message key="accept"/>'  />
                     <input type="button" onclick="history.back()" value='<fmt:message key="cancel"/>' />
                 
                 </div>        
            </DIV>
			</Div>   	
   </div>
   <div>&nbsp;</div>
</div>


