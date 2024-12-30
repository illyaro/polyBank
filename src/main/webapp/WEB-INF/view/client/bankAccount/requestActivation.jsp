<%@ page import="com.taw.polybank.dto.BankAccountDTO" %><%--
  Created by IntelliJ IDEA.
  User: Pablo Ruiz-Cruces
  Date: 07/05/2023
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PolyBank - Bank Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
</head>
<body>
<%
    Integer hasRequest = (Integer) request.getAttribute("hasRequest");
    BankAccountDTO account = (BankAccountDTO) session.getAttribute("account");

    if (hasRequest <= 0) {
%>
<h1>Bank account activation request</h1>
<form method="get" action="/client/account/makeRequest">
    <h3>Request description:</h3>
    <textarea cols="65" rows="3" maxlength="100" id="description" name="description"></textarea><br><br>
    <button class="btn btn-primary">Send</button>
</form>
<% } else { %>
<h1>Request sent correctly</h1>
<h2>Answer from an employee pending</h2>
<% } %>
<a href="/client/account?id=<%=account.getId()%>" class="btn btn-danger">Return</a>
</body>
</html>
