<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<h2>
    Oops, error occurred<br/>
    <c:out value="${requestScope['javax.servlet.error.exception']}"></c:out>
</h2>
<input type="button" value="Back" onclick="window.history.back()" /><br>
<c:url value="/" var="index" />
<a href="${index}"/>Index page</a>

</body>
</html>