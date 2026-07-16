<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>

</title>
</head>
<body>
<table>
<tr>
   <td>Message:</td>
   <td><spring:message /></td>
   
</tr>
<tr>
 <td><form:button path=""/></td>
   <td>&nbsp;</td>
</tr>
</table>

</body>
</html>