<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.dto.MessageDTO" %>
<%@ page import="com.taw.polybank.dto.ChatDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Javier Jordán Luque
  Date: 27/03/23
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ChatDTO chat = (ChatDTO) request.getAttribute("chat");
    List<MessageDTO> messageList = (List<MessageDTO>) request.getAttribute("messageList");
%>

<html>
<head>
    <title>Polybank - Assistence - Chat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<h1>Assistance Chat (Assistant <%= chat.getAssistant().getName() %>)</h1>
<table class="table table-bordered">
    <tr>
        <th>ME</th>
        <th>ASSISTANT</th>
    </tr>
        <%
            for (MessageDTO message : messageList) {
                if (message.getClient() != null) {
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
    <form action="/client/assistance/send" method="post">
        <input hidden="true" name="chatId" value="<%= chat.getId() %>">
        <textarea name="content" cols="50" rows="5" maxlength="1000"></textarea>
        <br><br>
        <button class="btn btn-primary">Send</button>
    </form>
        <%
            }
        %>
    <form action="/client/assistance/" method="get">
        <button class="btn btn-danger">Back</button>
    </form>
</body>
</html>