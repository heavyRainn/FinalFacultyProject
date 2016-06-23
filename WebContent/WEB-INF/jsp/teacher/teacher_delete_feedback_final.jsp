<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Delete feedback final</title>
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
	<fmt:message bundle="${lang}" key="title.delete.feedback.label.final"
		var="mainMessage" />
	<fmt:message bundle="${lang}" key="title.delete.feedback"
		var="signMessage" />
	<fmt:message bundle="${lang}" key="feedback.id" var="idFeedback" />
	<fmt:message bundle="${lang}" key="title.delete.feedback" var="delete" />

	<fmt:message bundle="${lang}" key="feedback.student"
		var="feedbackStudentId" />
	<fmt:message bundle="${lang}" key="feedback.faculty"
		var="feedbackFacultyId" />
	<fmt:message bundle="${lang}" key="feedback.mark" var="feedbackMark" />
	<fmt:message bundle="${lang}" key="feedback.description"
		var="feedbackDescr" />

	<div id="wrapper">
		<div id="bg"></div>
		<div id="overlay"></div>
		<div id="main">

			<header id="header">
			<h2>${mainMessage}</h2>

			<form action="Controller" id="feedback-form" method="post"
				novalidate="novalidate">
				<table>
					<tbody id="prodReportTable">
						<tr>
							<td></td>
							<td>${idFeedback}</td>
							<td>${feedbackStudentId}</td>
							<td>${feedbackFacultyId}</td>
							<td>${feedbackDescr}</td>
							<td>${feedbackMark}</td>
						</tr>
						<c:forEach items="${sessionScope.facultyFeedbacks}"
							var="facultyFeedbacks">
							<tr>
								<td>
									<div class="radio">
										<label> <input type="radio" name="idFeedback"
											value="${facultyFeedbacks.id}"></label>
									</div>
								</td>
								<td>${facultyFeedbacks.id}</td>
								<td>${facultyFeedbacks.studentId}</td>
								<td>${facultyFeedbacks.trainingId}</td>
								<td>${facultyFeedbacks.description}</td>
								<td>${facultyFeedbacks.mark}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br /> <input type="hidden" name="command" value="delete-feedback" />
				<input type="submit" value="${delete}" />
			</form>
			<br />
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