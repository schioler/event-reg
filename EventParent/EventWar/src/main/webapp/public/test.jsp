<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<html>
<head>


<title>Test</title>

<link rel="stylesheet" href="styles/cols.css">
<link rel="stylesheet" href="styles/event.css">
</head>
<body>

	<div class="content-div">
		<br>
		<p>Test</p>
		<form action="public/test.do" method="get">
			<table>
				<tr>
					<td>public/test.do</td>
					<td><input type="submit" name="submit"></td>
				</tr>
			</table>
		</form>
		<br>
		<form action="public/test-re.do" method="get">
			<table>
				<tr>
					<td>public/test-re.do</td>
					<td><input type="submit" name="submit"></td>
				</tr>
			</table>
		</form>
		<br>
		<form action="test.do" method="GET">
			<table>
				<tr>
					<td>test.do</td>
					<td><input type="submit" name="submit"></td>
				</tr>
			</table>
		</form>
		<br>
		<form action="test-re.do" method="GET">
			<table>
				<tr>
					<td>test-re.do</td>
					<td><input type="submit" name="submit"></td>
				</tr>
			</table>
		</form>
	</div>


</body>
</html>