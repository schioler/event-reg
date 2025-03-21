<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
   <fmt:bundle basename="dk.schioler.event.base.resources.EventListResources">
<head>
   <meta name="viewport" content="width=device-width, initial-scale=1.0" />
   <link rel="stylesheet" href="./styles/login.css"/>
</head>
<body>
	<div class="col-body">
		<div class="row-body">
			<div >&nbsp;</div>
			<div >&nbsp;</div>
			
			 <div  >  
			   <div class="center-area-wide">
			       <div>
			         &nbsp;
                </div>
                <div>
                
		               <form action="user-authenticate.do" method="post">
		               <div class="login-grid-container">
		               <div class="login-head">
		                     
		                           <fmt:message key="login" />
		               </div>
		               <div class="login-col1">
		                    <div class="login-row">
		                        <div>   <label>
		                           <fmt:message key="username" />:
		                           </label>
		                            
		                           </div>
		                           <div>
                                 <label>
		                           <fmt:message key="password" />:
                                 </label>		                           
			                                </div>
		                        </div>
		               </div>
		               <div class="login-col2">
		                    <div class="login-row">
		                         <div ><input type="text" name="username" autofocus="true"></div>
		                           <div><input type="password" name="password"></div>
		                        </div> 
		               </div>
		               <div class="login-foot">
		                        <input type="submit" name="<fmt:message key='login'/>"
		                           value="<fmt:message key='login'/>">
		               </div>
		               </div>
		               </form>
		         </div>  
               <div>
                  &nbsp;
               </div>
             
			   </div>
			</div>
				<!-- <div class="div-rb">Status</div>
				<div class="div-rb">footer</div> -->
		</div>
	</div>
</body>
</fmt:bundle>
</html>