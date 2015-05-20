<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Сервер терминалов Томас Мюнц</h1>

<%--<h3>Test message : ${msg}</h3>--%>

<form action="<c:url value="/processingQueue/"/>" method="get">
    <button type="submit">START PROCCESS Messages...</button>
</form>

<h3>Начало : ${startDate}</h3>
<h3>Завершение : ${endDate}</h3>
<h3>Затрачено : ${totalTime}</h3>
</body>
</html>
