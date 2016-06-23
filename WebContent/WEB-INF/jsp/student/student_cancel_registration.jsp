<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Student's faculties</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
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
	<fmt:message bundle="${lang}" key="title.cancel.registration"
		var="mainMessage" />
	<fmt:message bundle="${lang}" key="feedback.faculty" var="idFacultyn" />
	<fmt:message bundle="${lang}" key="faculty.name" var="facultyName" />
	<fmt:message bundle="${lang}" key="faculty.startDate"
		var="facultyStartDate" />
	<fmt:message bundle="${lang}" key="faculty.endDate"
		var="facultyEndDate" />
	<fmt:message bundle="${lang}" key="faculty.teacher"
		var="facultyTeacher" />
	<fmt:message bundle="${lang}" key="faculty.status" var="facultyStatus" />
	<fmt:message bundle="${lang}" key="title.myFaculties.cancel"
		var="cancelMessage" />
	<fmt:message bundle="${lang}" key="feedback.faculty.info"
		var="facultyInfo" />

	<div id="wrapper">
		<div id="bg"></div>
		<div id="overlay"></div>
		<div id="main">
			<header id="header">
			<h2>${mainMessage}</h2>
			<br />
			<form action="Controller" id="faculty-form" method="post"
				novalidate="novalidate">
				<table>
					<tbody id="prodReportTable">
						<tr>
							<td></td>
							<td>${idFacultyn}</td>
							<td>${facultyName}</td>
							<td>${facultyStartDate}</td>
							<td>${facultyEndDate}</td>
							<td>${facultyTeacher}</td>
							<td>${facultyStatus}</td>
						</tr>
						<c:forEach items="${sessionScope.allFaculties}" var="allFaculties">
							<tr>
								<td>
									<div class="radio">
										<label> <input type="radio" name="idFaculty"
											value="${allFaculties.id}"></label>
									</div>
								</td>
								<td>${allFaculties.id}</td>
								<td>${allFaculties.name}</td>
								<td>${allFaculties.startDate}</td>
								<td>${allFaculties.endDate}</td>
								<td>${allFaculties.teacher}</td>
								<td>${allFaculties.status}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br /> <input type="hidden" name="command"
					value="cancel-sign-up-to-faculty" /> <input type="submit"
					value="${cancelMessage}" />
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