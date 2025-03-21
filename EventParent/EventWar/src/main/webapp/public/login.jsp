<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle
	basename="dk.schioler.event.base.resources.EventListResources">
	<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="styles/login.css">
	</head>
	<body>
		<DIV class="body-col-33-34-33">
		    <DIV>&nbsp;</DIV> 
			 <DIV>
					<div class="body-row-100">
					<!-- 	<div >&nbsp;</div>
						<div >&nbsp;</div> -->
				
						<div> 
						
							<DIV class="center-col-100" style="background-color:lightblue;   ">
								 <DIV>
								    <h2><fmt:message key="login" /></h2>
								 </DIV>  
							</DIV>
							<form action="user-authenticate.do" method="post">
   						<DIV class="center-col-50-50">
			   				   <DIV class="center-col-50-50-left">   
							           <fmt:message key="username" />:
				     			   </DIV>
		    					   <DIV class="center-col-50-50-right" >
										<input type="text" name="username" autofocus="true">
	                       </DIV>
	                       
	                       
	                       <DIV class="center-col-50-50-left">
                              <fmt:message key="password" />:
                          </DIV>
                           <DIV class="center-col-50-50-right">
                              <input type="password" name="password">
                           </DIV>
                           
                           <DIV class="center-col-50-50-left" >&nbsp;</DIV>
                           <DIV  class="center-col-50-50-right">
                              <input type="submit" name="<fmt:message key='login'/>" value="<fmt:message key='login'/>">
                            </DIV>
                            <DIV class="center-col-50-50-left" >&nbsp;</DIV>
                           <DIV  class="center-col-50-50-right">
                             &nbsp;
                            </DIV>
   						</DIV> 
						   </form>								
					   </div>
				
					   <!-- <div>
						 <div class="row-body-1-div">&nbsp;</div>
						 <div class="row-body-1-div">&nbsp;</div>					
			    	  </div> --> 
	      	  </div>
         </DIV>
  	     <DIV>&nbsp;</DIV>
      </DIV> 
	</body>
</fmt:bundle>
</html>