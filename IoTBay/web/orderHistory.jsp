<%@ page import="beans.UserBean" %>
<%@ page import="beans.OrderBean" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserBean user = (UserBean) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String userName = user.getUserName();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Order History - IotBay</title>
    <link rel="stylesheet" href="css/IoTBayCss.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    
    <a href="main.jsp">
        <img src="images/IoTBay Logo.png" alt="IoTBay Logo" style="width: 300px; height: auto; margin: 0 auto; display:block;">
    </a>
    
    <header>
        <h1>Order History</h1>
    </header>
    
    <table border="1">
        <tr>
            <th>Order ID</th>
            <th>Status</th>
            <th>Total Price</th>
        </tr>
        <%
            List<OrderBean> orders = (List<OrderBean>) request.getAttribute("orders");
            if (orders != null) {
                for (OrderBean order : orders) {
        %>
        <tr>
            <td><%= order.getOrderID() %></td>
            <td><%= order.getOrderStatus() %></td>
            <td><%= order.getTotalPrice() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="3">No order history found.</td></tr>
        <%
            }
        %>
    </table>

    <a href="catalog.jsp" class="btn" >Continue Shopping</a>
    
    <footer>
        <p>&copy; 2025 IoTBay-ISD. All rights reserved.</p>
    </footer>
</body>
</html>