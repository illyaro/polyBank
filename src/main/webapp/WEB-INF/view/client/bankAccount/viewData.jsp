<%@ page import="com.taw.polybank.dto.BankAccountDTO" %>
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
    <title>Polybank - Bank Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<%
    BankAccountDTO account = (BankAccountDTO) session.getAttribute("account");
%>
<h1>Account data:</h1>
<table class="table table-bordered">
    <tr>
        <th scope="row">IBAN:</th>
        <td><%=account.getIban()%></td>
    </tr>
    <tr>
        <th scope="row">Balance:</th>
        <td><%=account.getBalance()%> <%=account.getBadgeByBadgeId().getName()%></td>
    </tr>
    <tr>
        <th scope="row">Owner:</th>
        <td><%=account.getClientByClientId().getName()%> <%=account.getClientByClientId().getSurname()%></td>
    </tr>
</table>
<% if (account.isActive()) { %>
<a class="btn btn-primary" href='/client/account/moneyExchange'>Money exchange</a>
<a class="btn btn-primary" href='/client/account/transaction'>Make a transaction</a>
<a class="btn btn-primary" href='/client/account/operationHistory'>Operation history</a>
<% } else { %>
<h3>Your account is not active</h3>
<a class="btn btn-warning" href='/client/request'>Request activation</a>
<% } %>
<a class="btn btn-danger" href='/client/view'>Return</a>
</body>
</html>
