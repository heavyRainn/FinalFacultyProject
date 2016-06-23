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
			<header id="header"> <fmt:message bundle="${lang}"
				key="title.logout" var="logOut" /> <fmt:message bundle="${lang}"
				key="faculty.info.id" var="id" /> <fmt:message bundle="${lang}"
				key="faculty.info.name" var="name" /> <fmt:message bundle="${lang}"
				key="faculty.info.startDate" var="sDate" /> <fmt:message
				bundle="${lang}" key="faculty.info.endDate" var="eDate" /> <fmt:message
				bundle="${lang}" key="faculty.info.teacher" var="teacher" /> <fmt:message
				bundle="${lang}" key="faculty.info.status" var="status" /> <fmt:message
				bundle="${lang}" key="faculty.info.link" var="link" />
			<ul>
				<li>${id }:<c:out value="${sessionScope.faculty.id }" /></li>
				<li>${name }:<c:out value="${sessionScope.faculty.name }" /></li>
				<li>${sDate }:<c:out value="${sessionScope.faculty.startDate }" /></li>
				<li>${eDate }:<c:out value="${sessionScope.faculty.endDate }" /></li>
				<li>${teacher }:<c:out value="${sessionScope.faculty.teacher }" /></li>
				<li>${status }:<c:out value="${sessionScope.faculty.status }" /></li>
				<li>${link }:<a href="${sessionScope.faculty.link }">Book.pdf</a></li>
			</ul>
			<br />
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