<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User Details</title>
</head>
<body>
    <form:form method="POST" commandName="user" action="update_user">
        <fieldset>
            <form:label path="id">Id</form:label>
            <form:input path="id" readonly="true" />

            <form:label path="login">Login</form:label>
            <form:input path="login" />

            <form:label path="password">Password</form:label>
            <form:input path="password" />
        </fieldset>

        <footer>
            <input type="submit" value="Update" />
        </footer>
    </form:form>

</body>
</html>
