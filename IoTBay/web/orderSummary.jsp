<%-- 
    Document   : OrderSummary
    Created on : 21 May 2025, 6:09:22 pm
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
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
        <h1>Order Summary</h1>
    </header>
    
    <h2>Order Result</h2>
    <p>${message}</p>
    <a href="orderForm.jsp" class="btn">Create another order</a>
    
    <footer>
        <p>&copy; 2025 IoTBay-ISD. All rights reserved.</p>
    </footer>
</body>
</html>