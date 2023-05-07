<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.dto.ChatDTO" %><%--
  Created by IntelliJ IDEA.
  User: Javier Jordán Luque
  Date: 27/03/2023
  Time: 13:25
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
<table class="table table-bordered">
    <tr>
        <th>ASSISTANT</th>
        <th>CLOSED</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for (ChatDTO chat : chatList) {
    %>
    <tr>
        <td><%= chat.getAssistant().getName() %></td>
        <td><%= chat.isClosedToString() %></td>
        <td><a class="btn btn-primary" href="/client/assistance/chat?id=<%= chat.getId() %>">Open</a></td>
        <%
            if (!chat.isClosed()) {
        %>
        <td>
            <form action="/client/assistance/close" method="post">
                <input hidden="true" name="chatId" value="<%= chat.getId() %>">
                <button class="btn btn-danger">Close</button>
            </form>
        </td>
        <%
            } else {
        %>
        <td></td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
</table>
<br>
<form action="/client/assistance/newChat" method="post">
    <button class="btn btn-primary">New Chat</button>
</form>
</body>
</html>