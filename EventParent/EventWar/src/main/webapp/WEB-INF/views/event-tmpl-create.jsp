<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<!-- <script type="text/javascript">
	function changeValue(element, value) {
		document.getElementById(element).value = value;
	}
</script>
 --><html>
<head>

<title>EventTemplate</title>
<!-- <link rel="stylesheet" href="public/styles/cols.css"> -->
<link rel="stylesheet" href="public/styles/event.css">
</head>
<body>
	<fmt:bundle
		basename="dk.schioler.event.base.resources.EventListResources">

		<%@ include file="include/menu.jsp"%>
		<%@ include file="include/status.jsp"%>

		<h3>
			<fmt:message key="event-template.entity"></fmt:message>
		</h3>
		<hr color="blue">
      <form method="post" action="./event-tmpl-create.do">
   	
   	<div class="container-3-col">
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>

				<div>
               <span> Name</span>
            </div>
            <div>
               <input type="hidden" name="event-type-id"    value="${sesEventTemplate.parentId}"/> 
               <input type="hidden" name="login-id"  value="${sesEventTemplate.loginId}"/>
               <input type="text" id="name" name="name" class="text-input" />
            </div>
            <div>&nbsp;</div>

            <div>
               <span>Short Name</span>
            </div>
            <div>
               <input type="text" id="short-name" name="short-name" class="text-input" />
            </div>
            <div>&nbsp;</div>

            <div>Dose</div>
            <div>
               <input type="text" name="dose" id="dose" class="text-input" />
            </div>
            <div>&nbsp;</div>

            <div>Unit</div>
            <div>
               <input type="text" id="unit" name="unit" class="text-input"/>
            </div>
            <div>&nbsp;</div>

            <div>Description</div>
            <div>
               <input class="text-input" type="text" id="description" name="description" />
            </div>
            <div>&nbsp;</div>

            <div>Is Favorite</div>
            <div style="text-align: right;">
               <select name="is-favorite" class="text-input" >
                  <option value="Yes">Yes</option>
                  <option selected value="No">No</option>
               </select>
             </div>
            <div>&nbsp;</div>

			      <div>&nbsp;</div>
            <div style="text-align:right;">                      
               <input  class="text-input-btn" type="submit" value="Create" />
            </div>
			   <div>&nbsp;</div>
			   
			               <div>&nbsp;</div>
            <div>&nbsp;</div>
            <div>&nbsp;</div>
			      
   	</div>
		</form>


	</fmt:bundle>
	<%@ include file="include/printSesVars.jsp"%>
</body>
</html>