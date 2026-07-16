<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row rounded-3">
<DIV class="col-sm-2 ">&nbsp;</DIV>

<DIV class="col-sm-8">
 <div class="row bg-primary-subtle pb-3 pt-4 " style=" border: solid red 0px;">
    <div class="col-sm-1 " style=" border: solid blue 0px;" >&nbsp;</div>
    <DIV class="col-sm-10 text-center" style=" border: solid green 0px;">
        <DIV class="row border-bottom border-secondary " >
           <div class="col-sm-12 text-center bg-light">
               <h1><fmt:message key="event-template.entity" /> </h1>
               <h2><fmt:message key="${action }" /></h2>                   
               
          </DIV>
        </DIV>
        
    </DIV>
    <div class="col-sm-1 ">&nbsp;</div>
</div>

 <div class="row bg-primary-subtle " style=" border: solid red 0px;">
    <div class="col-sm-1 " style=" border: solid blue 0px;" >&nbsp;</div>
    <DIV class="col-sm-10 " style=" border: solid green 0px;">
    
        <DIV class="row" style=" border: solid yellow 0 px;">
          <div class="col-sm-3 text-end bg-light">
            <label class="m-1 " for="req-name"><B> <fmt:message key="event-template.name" /></B> : </label>
          </div>
          <DIV class="col-sm-9 text-start bg-light ">
              <c:if test="${action eq 'create'}">
                  <input type="text" style="width:100%;border:none;" name="req-name" id="req-name" autofocus="autofocus" value="${sesEventTemplate.name }" />
              </c:if>
              <c:if test="${action eq 'update'}">
                  <input type="text" style="width:100%;border:none;" name="req-name" id="req-name" autofocus="autofocus" value="${sesEventTemplate.name }" />
              </c:if>
              <c:if test="${action eq 'delete'}">
                 <input type="text" style="width:100%;border:none;" disabled="disabled" name="req-name" id="req-name" autofocus="autofocus" value="${sesEventTemplate.name }" />                                                    
              </c:if>

          </DIV>
        </DIV>
        
    </DIV>
    <div class="col-sm-1 ">&nbsp;</div>    
</div>

 <div class="row bg-primary-subtle " style=" border: solid red 0px;">
    <div class="col-sm-1 " style=" border: solid blue 0px;" >&nbsp;</div>
    <DIV class="col-sm-10 " style=" border: solid green 0px;">
    
        <DIV class="row  " style=" border: solid yellow 0px;">
          <div class="col-sm-3 text-end bg-light">
             <label class="m-1 " for="req-shortname"><B><fmt:message key="event-template.shortname" /></B> :</label>
          </div>
          <DIV class="col-sm-9 text-start bg-light  ">
              <c:if test="${action eq 'create'}">
             <input  style="width:100%;border:none;"  type="text" name="req-short-name" id="req-short-name" value="${sesEventTemplate.shortName }">
              </c:if>
              <c:if test="${action eq 'update'}">
                 <input  style="width:100%;border:none;"  type="text" name="req-short-name" id="req-short-name" value="${sesEventTemplate.shortName }">
              </c:if>
              <c:if test="${action eq 'delete'}">
                   <input  style="width:100%;border:none;"  type="text"  disabled="disabled" name="req-short-name" id="req-short-name" value="${sesEventTemplate.shortName }">                                                    
              </c:if>


          </DIV>
        </DIV>        
    </DIV>
    <div class="col-sm-1 ">&nbsp;</div>    
</div>
 <div class="row bg-primary-subtle " style=" border: solid red 0px;">
    <div class="col-sm-1 " style=" border: solid blue 0px;" >&nbsp;</div>
    <DIV class="col-sm-10 " style=" border: solid green 0px;">
    
        <DIV class="row" style=" border: solid yellow 0px;">
          <div class="col-sm-3 text-end bg-light">
               <label  class="m-1"  for="req-description"><B><fmt:message key="event-template.description" /></B> :</label>
          </div>
          <DIV class="col-sm-9 text-start bg-light  ">
               <input style="width:100%;border:none;" type="text" id="req-description" disabled="disabled" name="req-description" value="${sesEventTemplate.description }">
          </DIV>
        </DIV>        
    </DIV>
    <div class="col-sm-1 ">&nbsp;</div>    
</div>   

 <div class="row bg-primary-subtle " style=" border: solid red 0px;">
    <div class="col-sm-1 " style=" border: solid blue 0px;" >&nbsp;</div>
    <DIV class="col-sm-10 " style=" border: solid green 0px;">
    
        <DIV class="row  " style=" border: solid yellow 0px;">
          <div class="col-sm-3 text-end bg-light">

               <label class="m-1 " for="req-dose"><B><fmt:message key="event-template.dose" /></B> :</label>
          </div>
          <DIV class="col-sm-9 text-start bg-light  ">

               <input style="width:100%;border:none;" type="text" disabled="disabled" name="dose" id="req-dose" value="${sesEventTemplate.dose}">
          </DIV>
        </DIV>        
    </DIV>
    <div class="col-sm-1 ">&nbsp;</div>    
</div>

 <div class="row bg-primary-subtle " style=" border: solid red 0px;">
    <div class="col-sm-1 " style=" border: solid blue 0px;" >&nbsp;</div>
    <DIV class="col-sm-10 " style=" border: solid green 0px;">
    
        <DIV class="row  " style=" border: solid yellow 0px;">
          <div class="col-sm-3 text-end bg-light">
               <label  class="m-1 "  for="req-unit"><B><fmt:message key="event-template.unit" /></B> :</label>
          </div>
          <DIV class="col-sm-9 text-start bg-light  ">
              <input style="width:100%;border:none;"  type="text" id="req-unit" disabled="disabled" name="req-unit" value="${sesEventTemplate.unit }">        
          </DIV>
        </DIV>        
    </DIV>
    <div class="col-sm-1 ">&nbsp;</div>    
</div>



 <div class="row bg-primary-subtle " style=" border: solid red 0px;">
    <div class="col-sm-1 " style=" border: solid blue 0px;" >&nbsp;</div>
    <DIV class="col-sm-10 " style=" border: solid green 0px;">
    
        <DIV class="row  " style=" border: solid yellow 0px;">
          <div class="col-sm-3 text-end bg-light">
            <label  class="m-1"  for="req-favorite"><B><fmt:message key="event-template.isfavorite" /></B> :</label>

          </div>
          <DIV class="col-sm-9 text-start bg-light  ">
                  <input style="width:100%;border:none"   type="text" id="req-unit" disabled="disabled" name="req-description" value="${sesEventTemplate.favorite }">

          </DIV>
        </DIV>        
    </DIV>
    <div class="col-sm-1 ">&nbsp;</div>    
</div>



 <div class="row bg-primary-subtle " style=" border: solid red 0px;">
    <div class="col-sm-1 " style=" border: solid blue 0px;" >&nbsp;</div>
    <DIV class="col-sm-10 " style=" border: solid green 0px;">
    
        <DIV class="row  " style=" border: solid yellow 0px;">
          <div class="col-sm-3 text-end bg-light">
                    <label   class="m-1 "  for="req-sort-order"><B><fmt:message key="event-template.sortorder" /></B> :</label>
                  
          </div>
          <DIV class="col-sm-9 text-start bg-light  ">
              <input style="width:100%;border:none;"  disabled="disabled" type="text" id="req-sort-order" name="req-sort-order" value="${sesEventTemplate.sortOrder}">
            
          </DIV>
        </DIV>        
    </DIV>
    <div class="col-sm-1 ">&nbsp;</div>    
</div>


 <div class="row bg-primary-subtle " style=" border: solid red 0px;">
    <div class="col-sm-1 " style=" border: solid blue 0px;" >&nbsp;</div>
    <DIV class="col-sm-10 " style=" border: solid green 0px;">
    
        <DIV class="row m-3 " style=" border: solid yellow 0px;">
          <div class="col-sm-12 text-center ">
             
             <input type="submit" class="btn btn-success rounded-3  border " style="width:6em;"  value='<fmt:message key="accept"/>' /> 
             
          
             <input type="button" 
               class="btn bg-secondary text-light rounded-3  border" 
               style="width:6em;" 
               onclick="history.back()"         
               value='<fmt:message key="cancel"/>' />                  
          
          </DIV>
        </DIV>        
    </DIV>
    <div class="col-sm-1 ">&nbsp;</div>    
</div>










</DIV>

<DIV class="col-sm-2">&nbsp;</DIV>
</div>





