<%@ page import="com.taw.polybank.entity.MessageEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.entity.ChatEntity" %>
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
    ChatEntity chat = (ChatEntity) request.getAttribute("chat");
    List<MessageEntity> messageList = (List<MessageEntity>) request.getAttribute("messageList");
%>

<html>
<head>
    <title>Polybank - Assistant - Chat</title>
</head>
<body>
<h1>Assistence Chat -> Client <%= chat.getClientId() %></h1>
<table border="1">
    <tr>
        <th>ME</th>
        <th>CLIENT</th>
    </tr>
        <%
            for (MessageEntity message : messageList) {
                if (message.getOwner() == Assistant) {
        %>
    <tr>
        <td><%= message.getContent()%></td>
        <td></td>
    </tr>
        <%
                } else {
        %>
    <tr>
        <td><%= message.getContent()%></td>
        <td></td>
    </tr>
        <%
                }
            }
            if (chat.getClosed() == 0) {
        %>
    <form action="/employee/assistence/send" method="post">
        <input hidden="true" name="chatId" value="<%= chat.getId() %>">
        <textarea name="content" cols="50" rows="50" maxlength="1000"/><br/>
        <button>Send</button>
    </form>
        <%
            }
        %>
</body>
</html>