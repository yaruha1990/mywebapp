<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<title>Users</title>
<body>

<h1>Users page</h1>

     <c:forEach var="user" items="${users}">
        <ul>
            <li><c:out value="${user.id}"></c:out></li>
            <li><c:out value="${user.login}"></c:out></li>
            <li><c:out value="${user.password}"></c:out></li>
            <form method="post" action="<c:url value="delete" />">
                <input type="submit" value="<spring:message code="delete"/>"/>
                <input type="hidden" name="id" value="${user.id}"/>
            </form>
            <form method="post" action="<c:url value="user_details" />">
                <input type="submit" value="<spring:message code="details"/>"/>
                <input type="hidden" name="id" value="${user.id}"/>
            </form>
            <hr/>
        </ul>
     </c:forEach><br>

     <c:url value="/" var="index" />
     <a href="${index}"/>Index page</a>

 </body>