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
</body>
</html>
