<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Update faculty</title>
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
	<fmt:message bundle="${lang}" key="title.update.faculty.label"
		var="mainMessage" />
	<fmt:message bundle="${lang}" key="title.update.faculty"
		var="signMessage" />
	<fmt:message bundle="${lang}" key="feedback.faculty" var="idFaculty" />
	<fmt:message bundle="${lang}" key="title.update.faculty" var="show" />

	<fmt:message bundle="${lang}" key="faculty.name" var="facultyName" />
	<fmt:message bundle="${lang}" key="faculty.startDate"
		var="facultyStartDate" />
	<fmt:message bundle="${lang}" key="faculty.endDate"
		var="facultyEndDate" />
	<fmt:message bundle="${lang}" key="faculty.teacher"
		var="facultyTeacher" />
	<fmt:message bundle="${lang}" key="faculty.status" var="facultyStatus" />

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
							<td>${idFaculty}</td>
							<td>${facultyName}</td>
							<td>${facultyStartDate}</td>
							<td>${facultyEndDate}</td>
							<td>${facultyTeacher}</td>
							<td>${facultyStatus}</td>
						</tr>
						<c:forEach items="${sessionScope.teachersFaculties}"
							var="teachersFaculties">
							<tr>
								<td>
									<div class="radio">
										<label> <input type="radio" name="idFaculty"
											value="${teachersFaculties.id}"></label>
									</div>
								</td>
								<td>${teachersFaculties.id}</td>
								<td>${teachersFaculties.name}</td>
								<td>${teachersFaculties.startDate}</td>
								<td>${teachersFaculties.endDate}</td>
								<td>${teachersFaculties.teacher}</td>
								<td>${teachersFaculties.status}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<input type="hidden" name="command" value="update-faculty" /> <br />
				<c:out value="${facultyName}" />
				<br /> <input type="text" name="name" value="" /> <br />
				<c:out value="${facultyStartDate}" />
				<br /> <input type="text" id="datepicker" name="startDate" value="" />
				<br />
				<c:out value="${facultyEndDate}" />
				<br /> <input type="text" id="datepicker1" name="endDate" value="" />
				<br /> <br /> <input type="submit" value="${show}" />
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