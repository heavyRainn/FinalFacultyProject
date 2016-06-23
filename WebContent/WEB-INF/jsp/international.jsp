<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body class="loading">
	<ul class="inter">
		<li><a
			href="${pageContext.request.contextPath}/Controller?command=change-locale&language=en_EN">
				<img class="img-responsive flag" width="36px" height="26px"
				src="img/english_flag.png" />
		</a></li>
		<li><a
			href="${pageContext.request.contextPath}/Controller?command=change-locale&language=ru_RU">
				<img class="img-responsive flag" width="36px" height="26px"
				src="img/russian_flag.png" />
		</a></li>
	</ul>
</body>
</html>