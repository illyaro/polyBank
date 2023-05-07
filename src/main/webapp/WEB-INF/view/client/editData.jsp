<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Nombre" uri="http://www.springframework.org/tags/form" %>
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
<form:form action="/client/edit" modelAttribute="client" method="post">
    <form:hidden path="id"/>
    <form:hidden path="dni"/><br>
    Name: <form:input path="name" size="30" maxlength="30"  /><br>
    Surnames: <form:input path="surname" size="40"  maxlength="40"/> <br>
    <form:hidden path="creationDate"/><br>
    <form:button>Save</form:button>
</form:form>
</body>
</html>
