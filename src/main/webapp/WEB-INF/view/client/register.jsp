<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Pablo Ruiz-Cruces
  Date: 13/03/2023
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Polybank - Client</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<h1>Client data:</h1>
<form:form action="/client/register" modelAttribute="client" method="post">
    <form:label path="dni">DNI: </form:label>
    <form:input path="dni" size="9" maxlength="9"/><br>
    <form:label path="name">Name: </form:label>
    <form:input path="name" size="30" maxlength="30"  /><br/>
    <form:label path="surname">Surname: </form:label>
    <form:input path="surname" size="40"  maxlength="40"/> <br/>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" size="20" maxlength="64"/>
    <form:button>Save</form:button>
</form:form>
</body>
</html>
