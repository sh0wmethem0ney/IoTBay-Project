<%@ page import="model.CustomerBean" %>

<%
    CustomerBean user = (CustomerBean) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
    <head>
    <title>IoTBay - Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/IoTBayCss.css">
</head>

<body>
    
    <a href="index.jsp">
        <img src="images/IoTBay Logo.png" alt='IoTBay Logo' class='logo'>
    </a>
    
    <header>
        <h1>
            Welcome, <%= user.getCustomerName() %>!</h1>
    </header>
</body>
</html>