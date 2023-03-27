<%@ page import="com.taw.polybank.entity.MessageEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.entity.ChatEntity" %>
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
    ChatEntity chat = (ChatEntity) request.getAttribute("chat");
    List<MessageEntity> messageList = (List<MessageEntity>) request.getAttribute("messageList");
%>

<html>
<head>
    <title>Polybank - Assistence - Chat</title>
</head>
<body>
<h1>Assistence Chat -> Assistant <%= chat.getAssistantId() %></h1>
<table border="1">
    <tr>
        <th>ME</th>
        <th>ASSISTANT</th>
    </tr>
        <%
            for (MessageEntity message : messageList) {
                if (message.getClientId() > 0) {
        %>
    <tr>
        <td><%= message.getContent()%></td>
        <td></td>
    </tr>
        <%
                } else {
        %>
    <tr>
        <td></td>
        <td><%= message.getContent()%></td>
    </tr>
        <%
                }
            }
            if (chat.getClosed() == 0) {
        %>
    <form action="/client/assistence/send" method="post">
        <input hidden="true" name="chatId" value="<%= chat.getId() %>">
        <textarea name="content" cols="50" rows="50" maxlength="1000"/><br/>
        <button>Send</button>
    </form>
        <%
            }
        %>
</body>
</html>