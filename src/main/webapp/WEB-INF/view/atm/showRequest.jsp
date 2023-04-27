<%@ page import="java.util.List" %>
<%@ page import="com.taw.polybank.dto.RequestDTO" %><%--
  Created by IntelliJ IDEA.
  User: lucia
  Date: 15/04/2023
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RequestDTO> requests = (List<RequestDTO>) request.getAttribute("requests");
%>
<html>
<head>
    <title>Polybank - Unban Request</title>
</head>
<body>
    <table style="border-collapse: collapse; border: 1px solid">
        <tr style="border: 1px solid">
            <th style="border: 1px solid">Client who requested unban</th>
            <th style="border: 1px solid">Bank account banned</th>
            <th style="border: 1px solid">Employee managing the petition</th>
            <th style="border: 1px solid">Timestamp</th>
            <th style="border: 1px solid">Description</th>
            <th style="border: 1px solid">Approved</th>
            <th style="border: 1px solid">Solved</th>
        </tr>
        <%
            for (RequestDTO unbanRequest : requests){
        %>
        <tr style="border: 1px solid">
            <td style="border: 1px solid"><%=unbanRequest.getClientByClientId().getName() +" "+ unbanRequest.getClientByClientId().getSurname()%></td>
            <td style="border: 1px solid"><%=unbanRequest.getBankAccountByBankAccountId().getIban()%></td>
            <td style="border: 1px solid"><%=unbanRequest.getEmployeeByEmployeeId().getName()%></td>
            <td style="border: 1px solid"><%=unbanRequest.getTimestamp()%></td>
            <td style="border: 1px solid"><%=unbanRequest.getDescription()%></td>
            <td style="border: 1px solid">
            <%
                if(unbanRequest.isSolved()){
            %>
                <%=unbanRequest.isApproved() ? "True" : "False"%>
            <%
                }
            %>
            </td>
            <td style="border: 1px solid"><%=unbanRequest.isSolved() ? "True" : "False"%></td>
        </tr>
        <%
            }
        %>
    </table>
    <form action="/atm/enumerarAcciones" method="post">
        <button>Go back</button>
    </form>
</body>
</html>
