<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.dto.ChatDTO" %>
<%@ page import="com.taw.polybank.dto.MessageDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Javier Jordán Luque
  Date: 27/03/23
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ChatDTO chat = (ChatDTO) request.getAttribute("chat");
    List<MessageDTO> messageList = (List<MessageDTO>) request.getAttribute("messageList");
%>

<html>
<head>
    <title>Polybank - Assistant - Chat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<h1>Assistance Chat (Client <%= chat.getClient().getDni() + " - " + chat.getClient().getName() %>)</h1>
<table class="table table-bordered">
    <tr>
        <th>ME</th>
        <th>CLIENT</th>
    </tr>
        <%
            for (MessageDTO message : messageList) {
                if (message.getAssistant() != null) {
        %>
    <tr>
        <td><%= message.getContentAndDate() %></td>
        <td></td>
    </tr>
        <%
                } else {
        %>
    <tr>
        <td></td>
        <td><%= message.getContentAndDate() %></td>
    </tr>
        <%
                }
            }
        %>
</table>
<br>
        <%
            if (!chat.isClosed()) {
        %>
    <form action="/employee/assistance/send" method="post">
        <input hidden="true" name="chatId" value="<%= chat.getId() %>">
        <textarea name="content" cols="50" rows="5" maxlength="1000"></textarea>
        <br><br>
        <button class="btn btn-primary">Send</button>
    </form>
        <%
            }
        %>
    <form action="/employee/assistance/" method="get">
        <button class="btn btn-danger">Back</button>
    </form>
</body>
</html>