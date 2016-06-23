<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>New student</title>
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script src="js/skel.min.js"></script>
<script src="js/init.js"></script>
<script src="js/goBack.js"></script>
<script src="js/validator.js"></script>
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
	<fmt:message bundle="${lang}" key="title.entity.student"
		var="signEntity" />
	<fmt:message bundle="${lang}" key="title.signup" var="signMessage" />
	<fmt:message bundle="${lang}" key="login" var="logIn" />
	<fmt:message bundle="${lang}" key="password" var="logPass" />
	<fmt:message bundle="${lang}" key="toSignUp" var="signUpBtn" />
	<fmt:message bundle="${lang}" key="name" var="singName" />
	<fmt:message bundle="${lang}" key="surname" var="singSurname" />
	<fmt:message bundle="${lang}" key="mail" var="singMail" />

	<div id="wrapper">
		<div id="bg"></div>
		<div id="overlay"></div>
		<div id="main">

			<header id="header">

			<h1>${signEntity}</h1>
			<form action="Controller" id="register-form-user" method="post"
				novalidate="novalidate">
				<br /> <input type="hidden" name="command" value="sign-up-student" />
				<c:out value="${logIn}" />
				<br /> <input type="text" name="login" value="" /> <br />
				<c:out value="${logPass}" />
				<br /> <input type="password" name="password" value="" /> <br />
				<c:out value="${singMail}" />
				<br /> <input type="text" name="mail" value="" /> <br />
				<c:out value="${singName}" />
				<br /> <input type="text" name="name" value="" /> <br />
				<c:out value="${singSurname}" />
				<br /> <input type="text" name="surname" value="" /> <br /> <br />
				<input type="submit" value="${signUpBtn}" />
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