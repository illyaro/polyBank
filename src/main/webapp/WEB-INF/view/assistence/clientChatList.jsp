<%@ page import="com.taw.polybank.entity.ChatEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Javier Jordán Luque
  Date: 27/03/2023
  Time: 13:25
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
        <th>ASSISTANT</th>
        <th>CLOSED</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for (ChatEntity chat : chatList) {
    %>
    <tr>
        <td><%= chat.getEmployeeByAssistantId().getName() %></td>
        <td>
            <%
                if (chat.getClosed() == 1) {
            %>
            Yes
            <%
                } else {
            %>
            No
            <%
                }
            %>
        </td>
        <td><a href="/client/assistence/chat?id=<%= chat.getId() %>">Open</a></td>
        <%
            if (chat.getClosed() == 0) {
        %>
        <td>
            <form action="/client/assistence/close" method="post">
                <input hidden="true" name="chatId" value="<%= chat.getId() %>">
                <button>Close</button>
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
<form action="/client/assistence/newChat" method="post">
    <button>New Chat</button>
</form>
</body>
</html>