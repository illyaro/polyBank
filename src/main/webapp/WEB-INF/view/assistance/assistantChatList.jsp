<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.dto.ChatDTO" %><%--
  Created by IntelliJ IDEA.
  User: Javier Jordán Luque
  Date: 20/03/2023
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ChatDTO> chatList = (List<ChatDTO>) request.getAttribute("chatList");
%>

<html>
<head>
    <title>Polybank - Assistence</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<h1>Chats List</h1>
<strong>Filter:</strong>
<br>
<form:form modelAttribute="filter" method="post" action="/employee/assistance/filter">
    Client DNI: <form:input path="clientDni"></form:input>
    <br>
    Client Name: <form:input path="clientName"></form:input>
   <br>
    Order By Most Recent Messages: <form:checkbox path="recent"></form:checkbox>
    <br>
    <form:button class="btn btn-primary">Filter</form:button>
</form:form>
<br>
<table class="table table-bordered">
    <tr>
        <th>DNI</th>
        <th>CLIENT</th>
        <th>CLOSED</th>
        <th></th>
    </tr>
    <%
        for (ChatDTO chat : chatList) {
    %>
    <tr>
        <td><%= chat.getClient().getDni() %></td>
        <td><%= chat.getClient().getName() %></td>
        <td><%= chat.isClosedToString() %></td>
        <td><a class="btn btn-primary" href="/employee/assistance/chat?id=<%= chat.getId() %>">Open</a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>