<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>
</head>
<body>
    <c:set var="language" scope="session" value="en_EN" />
	<c:set var="url" scope="session" value="WEB-INF/jsp/login.jsp" />
	<jsp:forward page="WEB-INF/jsp/login.jsp" />
</body>
</html>