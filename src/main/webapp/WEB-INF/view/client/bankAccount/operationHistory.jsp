<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.dto.TransactionDTO" %>
<%@ page import="com.taw.polybank.dto.CurrencyExchangeDTO" %>
<%@ page import="com.taw.polybank.dto.BankAccountDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: Pablo Ruiz-Cruces
  Date: 08/04/2023
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PolyBank - Bank Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../../commonStyle.css">
</head>
<body>
<%
    List<TransactionDTO> transactionList = (List<TransactionDTO>) request.getAttribute("transactionList");
    BankAccountDTO account = (BankAccountDTO) session.getAttribute("account");
%>
<h1>Operation history</h1>
<h3>Filters</h3>
<form:form modelAttribute="transactionFilter" method="post" action="/client/account/operationHistory">
    <form:label path="senderId">Sender id:</form:label>
    <form:input path="senderId" size="45" maxlength="45" class="formElement" onclick="this.select()"/>
    <form:label path="recipientName">Recipient name</form:label>
    <form:input path="recipientName" size="45" maxlength="45" class="formElement" onclick="this.select()"/>
    <br/>
    <form:label path="minAmount">Amount send greater than</form:label>
    <form:input type="number" path="minAmount" size="10" maxlength="10" class="formElement" onclick="this.select()"/>
    <form:label path="maxAmount">Amount send smaller than</form:label>
    <form:input type="number" path="maxAmount" size="10" maxlength="10" class="formElement" onclick="this.select()"/>
    <br/>
    <form:label path="transactionAfter">Date after</form:label>
    <form:input type="date" path="transactionAfter"/>
    <form:label path="transactionAfter">Date before</form:label>
    <form:input type="date" path="transactionBefore"/>

    <br/>
    <form:button class="btn btn-primary" type="submit" value="filter" name="filter">Filter</form:button>
    <a href="/client/account/operationHistory" class="btn btn-primary">Reset</a><br/>
</form:form>

<table class="table table-bordered">
    <tr>
        <th rowspan="2">Time</th>
        <th colspan="5">Sender</th>
        <th colspan="4">Recipient</th>
    </tr>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>ID</th>
        <th>Amount send</th>
        <th>Currency</th>

        <th>Name</th>
        <th>Iban</th>
        <th>Amount</th>
        <th>Currency</th>

    </tr>

    <%
        for (TransactionDTO t : transactionList) {
    %>

    <tr>
        <td><%=t.getTimestamp()%></td>

        <td><%=t.getClientByClientId().getName()%></td>
        <td><%=t.getClientByClientId().getSurname()%></td>
        <td><%=t.getClientByClientId().getDni()%></td>
        <td><%=t.getPaymentByPaymentId().getAmount()%></td>
        <%
            CurrencyExchangeDTO currencyExchange = t.getCurrencyExchangeByCurrencyExchangeId();
            String currency = t.getPaymentByPaymentId().getBenficiaryByBenficiaryId().getBadge();
            if (currencyExchange!= null) {
                currency = currencyExchange.getBadgeByInitialBadgeId().getName();
            }
        %>
        <th><%=currency%></th>

        <td><%=t.getPaymentByPaymentId().getBenficiaryByBenficiaryId().getName()%></td>
        <td><%=t.getPaymentByPaymentId().getBenficiaryByBenficiaryId().getIban()%></td>
        <%
            Double receivedAmount = null;
            if(currencyExchange != null){
                receivedAmount = currencyExchange.getFinalAmount();
            }else{
                receivedAmount = t.getPaymentByPaymentId().getAmount();
            }

        %>
        <td><%=receivedAmount%></td>
        <%
            String finalBadge = "";
            if(currencyExchange != null){
                finalBadge = currencyExchange.getBadgeByFinalBadgeId().getName();
            }else {
                finalBadge = t.getPaymentByPaymentId().getBenficiaryByBenficiaryId().getBadge();
            }
        %>
        <td><%=finalBadge%></td>

    </tr>

    <%
        }
    %>
</table>
<a href="/client/account?id=<%=account.getId()%>" class="btn btn-danger">Return</a><br>
</body>
</html>
