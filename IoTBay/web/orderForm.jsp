<%-- 
    Document   : OrderForm
    Created on : 21 May 2025, 6:04:37 pm
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order - IotBay</title>
    <link rel="stylesheet" href="css/IoTBayCss.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    
    <a href="main.jsp">
        <img src="images/IoTBay Logo.png" alt="IoTBay Logo" style="width: 300px; height: auto; margin: 0 auto; display:block;">
    </a>
    
    <header>
        <h1>Create an Order</h1>
    </header>
    <form action="OrderServlet" method="post">
        <input type="hidden" name="action" value="create" />
        User ID: <input type="text" name="userID" required /><br/><br/>
        <input type="submit" class="btn" value="Save Order" />
    </form>
    <a href="cart.jsp" class="btn" >Back</a>
    <footer>
        <p>&copy; 2025 IoTBay-ISD. All rights reserved.</p>
    </footer>
</body>
</html>