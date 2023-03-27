<%@ page import="com.taw.polybank.entity.ChatEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Javier Jordán Luque
  Date: 20/03/2023
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ChatEntity> chatList = (List<ChatEntity>) request.getAttribute("chatList");
%>

<html>
<head>
    <title>Polybank - Assistence</title>
</head>
<body>
<h1>Chats List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>CLIENT</th>
        <th>CLOSED</th>
        <th></th>
    </tr>
    <%
        for (ChatEntity chat : chatList) {
    %>
    <tr>
        <td><%= chat.getId() %></td>
        <td><%= chat.getClientId() %></td>
        <td><%= chat.getClosed() %></td>
        <td><a href="employee/assistence/chat?id=<%= chat.getId() %>">Open</a></td>
    </tr>
    <%
        }
    %>
</table border="1">
</body>
</html>