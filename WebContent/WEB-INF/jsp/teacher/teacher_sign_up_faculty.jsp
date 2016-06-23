<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Create faculty</title>
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/init.js"></script>
<script src="js/goBack.js"></script>
<script src="js/validator.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			showOtherMonths : true,
		});
	});
</script>
<script>
	$(function() {
		$("#datepicker1").datepicker({
			showOtherMonths : true,
		});

	});
</script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="css/jquery-ui.css" />
<noscript>
	<link rel="stylesheet" href="css/skel.css" />
	<link rel="stylesheet" href="css/style.css" />
	<link rel="stylesheet" href="css/style-wide.css" />
	<link rel="stylesheet" href="css/style-noscript.css" />
</noscript>
</head>
<body class="loading">
	<c:set var="language"
		value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
		scope="session" />

	<fmt:setLocale value="${language}" />
	<fmt:setBundle basename="resources.locale" var="lang" />
	<fmt:message bundle="${lang}" key="title.signup.faculty"
		var="signMessage" />
	<fmt:message bundle="${lang}" key="faculty.name" var="name" />
	<fmt:message bundle="${lang}" key="faculty.startDate" var="startDate" />
	<fmt:message bundle="${lang}" key="faculty.endDate" var="endDate" />
	<fmt:message bundle="${lang}" key="faculty.signUpBtn" var="signUpBtn" />

	<div id="wrapper">
		<div id="bg"></div>
		<div id="overlay"></div>
		<div id="main">

			<header id="header">
			<h2>${signMessage}</h2>
			<form action="Controller" id="register-form" method="post"
				novalidate="novalidate">
				<br /> <input type="hidden" name="command" value="sign-up-faculty" />
				<c:out value="${name}" />
				<br /> <input type="text" name="name" value="" /> <br />
				<c:out value="${startDate}" />
				<br /> <input type="text" id="datepicker" name="startDate" value="" />
				<br />
				<c:out value="${endDate}" />
				<br /> <input type="text" id="datepicker1" name="endDate" value="" />
				<br /> <br /> <input type="submit" value="${signUpBtn}" />
			</form>
			<br>

			<fmt:message bundle="${lang}" key="title.goback" var="back" />

			<button onclick="goBack()" value="${back}">
				<img src="img/back.png" alt="Зонтик"
					style="width: 20px; height: 20px">${back}</button>

			</header>
			<!-- Footer -->
			<footer id="footer"> <span class="copyright"> &copy;
				Fedorov Andrew | Faculty system | Java 2 , 2016 . </span> </footer>
		</div>
	</div>
</body>
</html>