<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<fmt:bundle basename="dk.schioler.shared.bits.language.LanguageResources">
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

            <DIV class="center-col-100" style="background-color: lightblue;">
              <DIV class="vp-text">
                <h2>Error</h2>
              </DIV>
            </DIV>


            <DIV class="center-col-50-50">
              <DIV class="center-col-50-50-left">Msg:</DIV>
              <DIV class="center-col-50-50-right"><%= exception.getMessage() %>></DIV>


              <DIV class="center-col-50-50-left">
                <a href="/event/">Login</a>
              </DIV>
              <DIV class="center-col-50-50-right">&nbsp;</DIV>
            </DIV>

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