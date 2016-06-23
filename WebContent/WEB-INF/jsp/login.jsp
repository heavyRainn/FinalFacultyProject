<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Faculties login</title>
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/init.js"></script>
<script src="js/validator.js"></script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
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
	<fmt:message bundle="${lang}" key="title.login" var="logMessage" />
	<fmt:message bundle="${lang}" key="title.signup" var="signMessage" />
	<fmt:message bundle="${lang}" key="login" var="logIn" />
	<fmt:message bundle="${lang}" key="logIn" var="logInBtn" />
	<fmt:message bundle="${lang}" key="password" var="logPass" />
	<fmt:message bundle="${lang}" key="title.entity.teacher"
		var="signUpBtnT" />
	<fmt:message bundle="${lang}" key="title.entity.student"
		var="signUpBtnS" />
	<fmt:message bundle="${lang}" key="name" var="singName" />
	<fmt:message bundle="${lang}" key="name" var="singName" />
	<fmt:message bundle="${lang}" key="surname" var="singSurname" />
	<fmt:message bundle="${lang}" key="mail" var="singMail" />

	<fmt:message bundle="${lang}" key="login.title.bestTeachers"
		var="bestTeachers" />
	<fmt:message bundle="${lang}" key="login.title.know" var="know" />
	<fmt:message bundle="${lang}" key="login.title.every" var="every" />

	<div id="wrapper">
		<div id="bg"></div>
		<div id="overlay"></div>
		<div id="main">

			<!-- Header -->
			<header id="header">
				<h1>${logMessage}</h1>
				<p>${bestTeachers }&nbsp;&bull;&nbsp;${know }
					&nbsp;&bull;&nbsp;${every }</p>
				<c:import url="international.jsp" />
				<form action="Controller" id="login-form" method="post"
					novalidate="novalidate">
					<br /> <input type="hidden" name="command" value="login" />
					<c:out value="${logIn}" />
					<br /> <input type="text" name="login" value="" /> <br />
					<c:out value="${logPass}" />
					<br /> <input type="password" name="password" value="" /> <br />
					<br /> <input type="submit" value="${logInBtn}" />
				</form>
				<br />
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="move-to-sign-up-student" />
					<input type="submit" value="${signUpBtnS}" />
				</form>
				<br />
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="move-to-sign-up-teacher" />
					<input type="submit" value="${signUpBtnT}" />
				</form>
			</header>

			<!-- Footer -->
			<footer id="footer">
				<span class="copyright"> &copy; Fedorov Andrew | Faculty
					system | Java 2 , 2016 . </span>
			</footer>

		</div>
	</div>
</body>
</html>