<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Student page</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script src="js/skel.min.js"></script>
<script src="js/init.js"></script>
<script src="js/goBack.js"></script>
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
	<fmt:message bundle="${lang}" key="title.main" var="mainMessage" />
	<div id="wrapper">
		<div id="bg"></div>
		<div id="overlay"></div>
		<div id="main">
			<header id="header">
			<h2>
				<ctg:info name="${sessionScope.login.name}"
					surname="${sessionScope.login.surname}"
					locale="${sessionScope.language}" />
			</h2>
			<br />
			<fmt:message bundle="${lang}" key="title.signFaculty"
				var="signFaculty" />
			<form action="Controller" method="post">
				<input type="hidden" name="command"
					value="move-to-user-page-registration" /> <input type="submit"
					value="${signFaculty}" />
			</form>
			<br />
			<fmt:message bundle="${lang}" key="title.myFaculties.cancel"
				var="cancelMessage" />
			<form action="Controller" method="post">
				<input type="hidden" name="command"
					value="move-to-cancel-registration" /> <input type="submit"
					value="${cancelMessage}" />
			</form>

			<br />
			<fmt:message bundle="${lang}" key="title.myFaculties"
				var="myFaculties" />
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="move-to-user-faculties" />
				<input type="submit" value="${myFaculties}" />
			</form>
			<br />
			<fmt:message bundle="${lang}" key="title.myFeedbacks"
				var="myFeedbacks" />
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="move-to-user-feedbacks" />
				<input type="submit" value="${myFeedbacks}" />
			</form>
			<br />
			<fmt:message bundle="${lang}" key="title.logout" var="logOut" />
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="logout" /> <input
					type="submit" value="${logOut}" />
			</form>
			<br />
			<fmt:message bundle="${lang}" key="title.goback" var="back" />
			<button onclick="goBack()" value="${back}">
				<img src="img/back.png" alt="Зонтик"
					style="width: 20px; height: 20px">${back}</button>
			</header>
			<footer id="footer"> <span class="copyright"> &copy;
				Fedorov Andrew | Faculty system | Java 2 , 2016 . </span> </footer>
		</div>
	</div>
</body>
</html>