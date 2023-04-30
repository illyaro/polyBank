<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.dto.BankAccountDTO" %>
<%@ page import="com.taw.polybank.dto.BadgeDTO" %><%--
  Created by IntelliJ IDEA.
  User: lucia
  Date: 01/04/2023
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
  BankAccountDTO bankAccount = (BankAccountDTO) session.getAttribute("bankAccount");
  BadgeDTO actualBadge = (BadgeDTO) session.getAttribute("badge");
  List<BadgeDTO> badges = (List<BadgeDTO>) request.getAttribute("badges");
%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<head>
    <title>PolyBank - TakeOut</title>
</head>
<body>
<h1>Take out money</h1>
<h3>Balance: <%= bankAccount.getBalance()%> <%=actualBadge.getName()%></h3>
<form method="post" action="/atm/takeOut">
  <label for="amount">Amount: </label>
  <input type="text" maxlength="6" name="amount" id="amount">
  <select name="badge">
    <%
      for (BadgeDTO badge : badges){
    %>
    <option value="<%=badge.getId()%>" <%if(badge.getId() == actualBadge.getId()){%> selected <%}%> ><%=badge.getName()%></option>
    <%
      }
    %>
  </select> <br>
  <button type="submit">TakeOut</button>
</form>
<form action="/atm/enumerarAcciones" method="post">
  <button>Go back</button>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
