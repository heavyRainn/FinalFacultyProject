<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Teacher's page</title>
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
			<fmt:message bundle="${lang}" key="title.teachersFaculty"
				var="teachersFaculty" />
			<form action="Controller" method="post">
				<input type="hidden" name="command"
					value="move-to-teachers-faculties" /> <input type="submit"
					value="${teachersFaculty}" />
			</form>
			<br />
			<fmt:message bundle="${lang}" key="title.signup.faculty"
				var="signFaculty" />
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="move-to-sign-up-faculty" />
				<input type="submit" value="${signFaculty}" />
			</form>
			<br />

			<fmt:message bundle="${lang}" key="title.update.faculty"
				var="upFaculty" />
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="move-to-update-faculty" />
				<input type="submit" value="${upFaculty}" />
			</form>
			<br />

			<fmt:message bundle="${lang}" key="title.delete.faculty"
				var="deleteFaculty" />
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="move-to-delete-faculty" />
				<input type="submit" value="${deleteFaculty}" />
			</form>
			<br />

			<fmt:message bundle="${lang}" key="title.signup.feedback"
				var="signFeedback" />
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="move-to-sign-up-feedback" />
				<input type="submit" value="${signFeedback}" />
			</form>
			<br />

			<fmt:message bundle="${lang}" key="title.delete.feedback"
				var="deleteFeedback" />
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="move-to-delete-feedback" />
				<input type="submit" value="${deleteFeedback}" />
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

			<!-- Footer -->
			<footer id="footer"> <span class="copyright"> &copy;
				Fedorov Andrew | Faculty system | Java 2 , 2016 . </span> </footer>
		</div>
	</div>
</body>
</html>