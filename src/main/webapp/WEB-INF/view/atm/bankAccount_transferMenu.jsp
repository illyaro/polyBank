<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.dto.BankAccountDTO" %>
<%@ page import="com.taw.polybank.dto.BadgeDTO" %><%--
  Created by IntelliJ IDEA.
  User: lucia
  Date: 01/04/2023
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BankAccountDTO bankAccount = (BankAccountDTO) session.getAttribute("bankAccount");
    BadgeDTO actualBadge = (BadgeDTO) session.getAttribute("badge");
%>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<head>
    <title>PolyBank - Transfer money</title>
</head>
<body>
<h1>Transfer money</h1>
<h3>Balance: <%= bankAccount.getBalance()%> <%=actualBadge.getName()%>
</h3>
<form method="post" action="/atm/makeTransfer">
    <label for="amount">Amount: </label>
    <input type="text" maxlength="6" name="amount" id="amount">
    <label for="receiver">Receiver IBAN: </label>
    <input type="text" maxlength="34" size="34" id="receiver" name="receiver"> <br>
    <label for="receiverName">Receiver name: </label>
    <input type="text" maxlength="45" size="45" id="receiverName" name="receiverName"> <br>
    <button type="submit">Transfer</button>
</form>
<form action="/atm/enumerarAcciones" method="post">
    <button>Go back</button>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
