<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<title>Login</title>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        function doAjax() {
            $.ajax({
                url:'checkStrength',
                type:'POST',
                data:({password:$('#password').val()}),
                success:function (data) {
                    $('#strengthValue').html(data);
                }
            })
        }
    </script>
</head>
<body>
    <form:form method="POST" commandName="user" action="add_user">
        <fieldset>
            <form:label path="login"><spring:message code="login"/></form:label>
            <form:input path="login" /><br>
            <form:errors path="login" cssStyle="color: red"/><br>

            <form:label path="password"><spring:message code="password"/></form:label>
            <form:input path="password" onkeyup="doAjax()" /><br>
            <form:errors path="password" cssStyle="color: red"/>
            <span style="float: left" id="strengthValue"></span>
        </fieldset>

        <footer>
            <input type="submit" value="<spring:message code="create"/>"/>
        </footer>
    </form:form>
</body>
</html>