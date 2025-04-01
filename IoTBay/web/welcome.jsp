<%@ page import="beans.CustomerBean" %>
<%
    CustomerBean user = (CustomerBean) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp"); // redirect to login.jsp if not logged in
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Main - IoTBay</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/IoTBayCss.css">
</head>
<body>

    <a href="index.jsp">
        <img src="images/IoTBay Logo.png" alt="IoTBay Logo" class="logo">
    </a>

    <header>
        <h1>Welcome, <%= user.getCustomerName() %>!</h1>
    </header>

    <main>
        <div class="container">
            <h2>Your Info</h2>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>Phone:</strong> <%= user.getPhoneNumber() %></p>
            <p><strong>Gender:</strong> <%= user.getGender() %></p>
            <p><strong>Birthdate:</strong> <%= user.getDateOfBirth() %></p>

            <div style="text-align: center;">
                <a href="logout.jsp" class="btn">Log Out</a>
            </div>
        </div>
    </main>

    <footer>
        <p>&copy; 2025 IoTBay Assignment1. All rights reserved.</p>
    </footer>
</body>
</html>