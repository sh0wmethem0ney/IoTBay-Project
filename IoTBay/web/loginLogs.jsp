<%@ page import="java.util.*, java.sql.Timestamp, dao.UserDAO, beans.UserBean" %>
<%
    UserBean user = (UserBean) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UserDAO dao = new UserDAO();
    List<Timestamp> logs = dao.getLoginLogs(user.getUserID());
%>

<!DOCTYPE html>
<html>
<head>
    <title>Login Logs</title>
    <link rel="stylesheet" href="css/IoTBayCss.css">
</head>
<body>
    <header>
        <h1>Login Logs for <%= user.getUserName() %></h1>
    </header>

    <main>
        <div class="container">
            <ul>
                <% for (Timestamp log : logs) { %>
                    <li><%= log %></li>
                <% } %>
            </ul>
            <a href="accountManagement.jsp" class="btn">Back to Account</a>
        </div>
    </main>
</body>
</html>