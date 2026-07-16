<!DOCTYPE html>
<%@ page language="java" contentType="text/html; 
charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
   <head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="styles/bootstrap-5.3.8-dist/css/bootstrap.css" rel="stylesheet">
<script src="styles/bootstrap-5.3.8-dist/js/bootstrap.bundle.js"></script>
   </head>
   <body>
      <DIV class="pt-5">&nbsp;</DIV>
      <DIV class="container pt-5 text-bg-secondary text-center rounded" style="width: 40%;">
         <div>
            <DIV>
               <DIV>
                  <h2>
                     <fmt:message key="login" />
                  </h2>
               </DIV>
            </DIV>
         </DIV>
         <form action="user-authenticate.do" method="post" autocomplete="off">
            <DIV>
               <div>
                  <fmt:message key="username" />
                  :
               </DIV>
               <DIV>
                  <input type="text" name="req-login-token" autofocus="autofocus" autocomplete="off">
               </DIV>
               <DIV>
                  <fmt:message key="password" />
                  :
               </DIV>
               <DIV>
                  <input type="password" name="req-password">
               </DIV>
               <DIV>&nbsp;</DIV>
               <DIV>
                  <input type="submit" name="<fmt:message key='login'/>" value="<fmt:message key='login'/>">
               </DIV>
            </div>
         </form>
         <DIV>&nbsp;</DIV>
      </DIV>
   </body>
</fmt:bundle>
</html>