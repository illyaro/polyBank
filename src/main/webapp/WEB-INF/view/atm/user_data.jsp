<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.dto.ClientDTO" %>
<%@ page import="com.taw.polybank.dto.BankAccountDTO" %><%--
  Created by IntelliJ IDEA.
  User: lucia
  Date: 27/03/2023
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClientDTO cliente = (ClientDTO) session.getAttribute("client");
    List<BankAccountDTO> bankAccounts = (List<BankAccountDTO>) request.getAttribute("bankAccounts");
%>
<html>
<head>
    <title>Polybank - Cliente</title>
</head>
<body>
<h1>Welcome, <%=cliente.getName()%> <%=cliente.getSurname()%>
</h1>
<h3>What do you want to do?</h3>
<form action="/atm/editarDatos" method="get">
    <button type="submit">Modify my data</button> <br>
</form>
<form action="/atm/enumerarAcciones" method="post">
    <label for="bankAccount">Select a bank account:</label>
    <select id="bankAccount" name="bankAccount">
        <%
            for (BankAccountDTO bankAccount : bankAccounts){
        %>
        <option value=<%=bankAccount.getId()%>><%=bankAccount.getIban()%></option>
        <%
            }
        %>
    </select>
    <button type="submit">Make/audit transactions</button>
</form>
</body>
</html>
