<%@ page import="com.taw.polybank.entity.BankAccountEntity" %>
<%@ page import="com.taw.polybank.dto.ClientDTO" %>
<%@ page import="com.taw.polybank.dto.BankAccountDTO" %>
<%@ page import="java.util.List" %><%--
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
<%
    ClientDTO client = (ClientDTO) request.getAttribute("client");
    List<BankAccountDTO> accounts = (List<BankAccountDTO>) request.getAttribute("accounts");
%>
<h1>Client data:</h1>
<table class="table table-bordered">
    <tr>
        <th scope="row">DNI:</th>
        <td><%=client.getDni()%></td>
    </tr>
    <tr>
        <th scope="row">Name:</th>
        <td><%=client.getName()%></td>
    </tr>
    <tr>
        <th scope="row">Surname:</th>
        <td><%=client.getSurname()%></td>
    </tr>
    <tr>
        <th scope="row">Sign-up date:</th>
        <td><%=client.getCreationDate()%></td>
    </tr>
    <tr>
        <th scope="row">Bank accounts:</th>
        <td>
            <% for (BankAccountDTO account : accounts) { %>
            <a href="/client/account?id=<%=account.getId()%>"><%=account.getIban()%></a><br>
            <% } %>
        </td>
    </tr>
</table>
<a class="btn btn-primary" href='/client/edit?id=<%=client.getId()%>'>Edit my data</a>
<a class="btn btn-primary" href='/client/assistance'>Assistance</a>
<a class="btn btn-danger" href='/client/logout'>Log out</a>
</body>
</html>
