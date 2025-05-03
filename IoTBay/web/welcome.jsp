<%@ page import="beans.UserBean" %>
<%
    //asking user attribute to the session
    UserBean user = (UserBean) session.getAttribute("user");
    
    //back to index if not logged in
        if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Main - IoTBay</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/IoTBayCss.css">
    </head>
    <body>
        <a href="index.jsp">
            <img src="images/IoTBay Logo.png" alt="IoTBay Logo" class="logo" style="width: 300px; height: auto; margin: 0 auto; display:block;">
        </a>

        <header>
            <h1>Welcome, <%= user.getCustomerName() %>!</h1>
        </header>

        <main>
            <div class="container">
                <p><strong>Email:</strong> <%= user.getEmail() %></p>
                <p><strong>Phone:</strong> <%= user.getPhoneNumber() %></p>
                <p><strong>Gender:</strong> <%= user.getGender() %></p>
                <p><strong>Date of Birth:</strong> <%= user.getDateOfBirth() %></p>

                <div style="text-align: center; margin-top: 30px;">
                    <a href="main.jsp" class="btn">Go to Main</a>
                </div>
            </div>
        </main>

        <footer>
            <p>&copy; 2025 IoTBay Assignment1. All rights reserved.</p>
        </footer>
    </body>
</html>