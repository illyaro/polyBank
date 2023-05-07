<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.polybank.entity.BankAccountEntity" %>
<%@ page import="com.taw.polybank.dto.BankAccountDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: Pablo Ruiz-Cruces
  Date: 07/04/2023
  Time: 23:34
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
<% if (account.isActive()) {%>
<h1>Make a transaction</h1>
<p>Balance: <%=account.getBalance()%> <%=account.getBadgeByBadgeId().getName()%></p>
<form action="/client/account/processTransfer" method="post">
    <input type="hidden" id="account" name="account" value="<%=account.getId()%>">
    <label for="beneficiary">Name of recipient: </label>
    <input id="beneficiary" type="text" name="beneficiary" maxlength="45" size="45" class="formElement" />
    <br/>
    <label for="iban">IBAN of recipient: </label>
    <input id="iban" type="text" name="iban" maxlength="34" size="34" class="formElement" />
    <br/>
    <label for="amount">Amount: </label>
    <input id="amount" type="text" name="amount" maxlength="10" size="10" class="formElement" />
    <br/>
    <button class="btn btn-primary" type="submit" >Make transaction</button>
</form>
<% } else { %>
<h3>Your account is not active</h3>
<% } %>
<a href="/client/account?id=<%=account.getId()%>" class="btn btn-danger">Return</a><br>
</body>
</html>
