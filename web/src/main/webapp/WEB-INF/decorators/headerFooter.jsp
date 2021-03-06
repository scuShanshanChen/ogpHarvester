<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Open GeoPortal Harvester</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<spring:url value="/webjars/bootstrap/3.0.2/css/bootstrap.min.css"
	var="bootstrapCss" />
<link rel="stylesheet" href="${bootstrapCss}">

<!-- Optional theme -->
<spring:url value="/webjars/bootstrap/3.0.2/css/bootstrap-theme.min.css"
	var="bootstrapTheme" />
<link rel="stylesheet" href="${bootstrapTheme}">



<spring:url value="/static/css/main.css" var="mainCss" />
<link rel="stylesheet" href="${mainCss}">

<spring:url value="/webjars/jquery/1.10.2/jquery.min.js" var="jQuery" />
<script src="${jQuery}"></script>
<!-- Latest compiled and minified JavaScript -->
<spring:url value="/webjars/bootstrap/3.0.2/js/bootstrap.min.js"
	var="bootstrapJs" />
<script src="${bootstrapJs}"></script>
<decorator:head />
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- Container -->
	<div class="container">
		<decorator:body />
	</div>
	<!-- //container -->
</body>
</html>