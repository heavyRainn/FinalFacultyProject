<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Create feedback final</title>
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
	<fmt:message bundle="${lang}" key="title.signup.feedback.label.final"
		var="mainMessage" />
	<fmt:message bundle="${lang}" key="title.signup.feedback"
		var="signMessage" />
	<fmt:message bundle="${lang}" key="feedback.student" var="idStudent" />
	<fmt:message bundle="${lang}" key="feedback.faculty" var="idFaculty" />
	<fmt:message bundle="${lang}" key="feedback.mark" var="mark" />
	<fmt:message bundle="${lang}" key="feedback.description"
		var="description" />
	<fmt:message bundle="${lang}" key="title.signup.feedback"
		var="giveFeed" />
	<fmt:message bundle="${lang}" key="title.feedback.show" var="show" />

	<fmt:message bundle="${lang}" key="faculty.name" var="facultyName" />
	<fmt:message bundle="${lang}" key="faculty.startDate"
		var="facultyStartDate" />
	<fmt:message bundle="${lang}" key="faculty.endDate"
		var="facultyEndDate" />
	<fmt:message bundle="${lang}" key="faculty.teacher"
		var="facultyTeacher" />
	<fmt:message bundle="${lang}" key="faculty.status" var="facultyStatus" />

	<fmt:message bundle="${lang}" key="title.id" var="studentId" />
	<fmt:message bundle="${lang}" key="name" var="studentName" />
	<fmt:message bundle="${lang}" key="surname" var="studentSurname" />
	<fmt:message bundle="${lang}" key="mail" var="studentMail" />

	<div id="wrapper">
		<div id="bg"></div>
		<div id="overlay"></div>
		<div id="main">

			<header id="header">
			<h2>${mainMessage}</h2>
			<br>
			<form action="Controller" id="create-form" method="post"
				novalidate="novalidate">
				<table>
					<tbody id="prodReportTable">
						<tr>
							<td></td>
							<td>${studentId}</td>
							<td>${studentName}</td>
							<td>${studentSurname}</td>
							<td>${studentMail}</td>
						</tr>
						<c:forEach items="${sessionScope.facultyUsers}" var="facultyUsers">
							<tr>
								<td>
									<div class="radio">
										<label> <input type="radio" name="idStudent"
											value="${facultyUsers.id}"></label>
									</div>
								</td>
								<td>${facultyUsers.id}</td>
								<td>${facultyUsers.name}</td>
								<td>${facultyUsers.surname}</td>
								<td>${facultyUsers.mail}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br /> <input type="hidden" name="command" value="sign-up-feedback" />
				<c:out value="${mark}" />
				<br /> <input type="text" name="mark" value="" /> <br />
				<c:out value="${description}" />
				<br /> <input type="text" name="description" value="" /> <br /> <br />
				<input type="submit" value="${giveFeed}" />
			</form>
			<fmt:message bundle="${lang}" key="title.goback" var="back" /> <br />
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