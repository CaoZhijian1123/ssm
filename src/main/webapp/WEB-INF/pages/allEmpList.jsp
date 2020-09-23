<%--
  Created by IntelliJ IDEA.
  User: caozj
  Date: 2020/9/22
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>所有员工</title>

    <h1>所有员工</h1>
    <table>
        <tr>

            <th>id</th>
            <th>lastname</th>
            <th>gender</th>
            <th>email</th>
        </tr>

        <c:forEach items="${employees }" var="emp">
            <tr>
                <td>${emp.id }</td>
                <td>${emp.lastName }</td>
                <td>${emp.gender }</td>
                <td>${emp.email }</td>
            </tr>
        </c:forEach>

    </table>
</head>
<body>

</body>
</html>
