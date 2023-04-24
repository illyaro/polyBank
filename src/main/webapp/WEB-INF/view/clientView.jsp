<%@ page import="com.taw.polybank.entity.ClientEntity" %>
<%@ page import="com.taw.polybank.entity.BankAccountEntity" %><%--
  Created by IntelliJ IDEA.
  User: jmsan
  Date: 13/03/2023
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClientEntity cliente = (ClientEntity) request.getAttribute("client");
%>
<html>
<head>
    <title>Polybank - Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<h1>Información del cliente:</h1>
<table class="table table-bordered">
    <tr>
        <th scope="row">DNI:</th>
        <td><%=cliente.getDni()%></td>
    </tr>
    <tr>
        <th scope="row">Nombre:</th>
        <td><%=cliente.getName()%></td>
    </tr>
    <tr>
        <th scope="row">Apellidos:</th>
        <td><%=cliente.getSurname()%></td>
    </tr>
    <tr>
        <th scope="row">Fecha de creación:</th>
        <td><%=cliente.getCreationDate()%></td>
    </tr>
    <tr>
        <th scope="row">Cuentas bancarias:</th>
        <td>
            <% for (BankAccountEntity banco : cliente.getBankAccountsById()) { %>
            <a href="/client/account?id=<%=banco.getId()%>"><%=banco.getIban()%></a><br>
            <% } %>
        </td>
    </tr>
</table>
<a class="btn btn-primary" href='/client/edit?id=<%=cliente.getId()%>'>Editar mis datos</a>
</body>
</html>
