<%@ page import="com.taw.polybank.entity.BankAccountEntity" %><%--
  Created by IntelliJ IDEA.
  User: lucia
  Date: 01/04/2023
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BankAccountEntity bankAccount = (BankAccountEntity) request.getAttribute("bankAccount");
%>
<html>
<head>
    <title>PolyBank - Account Actions</title>
</head>
<body>
<h1>Account actions</h1>
    <form method="post">
        <input type="hidden" name="bankAccount" id="bankAccount" value="<%=bankAccount.getId()%>">
        <button type="submit" formaction="/atm/makeTransfer">Transfer money</button> <br>
        <button type="submit" formaction="#">Take out money</button> <br>
        <button type="submit" formaction="#">Check previous transactions</button> <br>
        <button type="submit" formaction="#">Request unban</button> <br>
    </form>
</body>
</html>
