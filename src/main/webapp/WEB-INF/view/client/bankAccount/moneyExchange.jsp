<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.dto.BadgeDTO" %>
<%@ page import="com.taw.polybank.dto.BankAccountDTO" %><%--
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
    List<BadgeDTO> badgeList = (List<BadgeDTO>) request.getAttribute("badgeList");
%>
<div id="transactionWindow">
    <h1>Money Exchange</h1>
    <p>Balance: <%=account.getBalance()%> <%=account.getBadgeByBadgeId().getName()%></p>

    <form action="/client/account/makeExchange" method="post">
        <input type="hidden" id="account" name="account" value="<%=account.getId()%>">
        <label for="badge">Desired currency: </label>
        <select id="badge" name="badge">
            <% for (BadgeDTO badge : badgeList) { %>
                <option value="<%=badge.getId() %>"><%= badge.getName() %></option>
            <% } %>
        </select>
        <input type="submit" class="btn btn-primary" value="Exchange">
    </form>
</div>
<a href="/client/account?id=<%=account.getId()%>" class="btn btn-danger">Return</a><br>
</body>
</html>
