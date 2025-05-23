<%@ page import="beans.UserBean" %>
<%@ page import="beans.CartBean" %>
<%@ page import="beans.OrderBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>

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
    <title>My Cart - IotBay</title>
    <link rel="stylesheet" href="css/IoTBayCss.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    
    <a href="main.jsp">
        <img src="images/IoTBay Logo.png" alt="IoTBay Logo" style="width: 300px; height: auto; margin: 0 auto; display:block;">
    </a>
    
    <header>
        <h1>My Cart</h1>
    </header>

    <table border="1">
        <tr>
            <th>Product ID</th><th>Quantity</th><th>Price</th><th>Total</th><th>Action</th>
        </tr>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.productID}</td>
                <td>
                    <form action="OrderServlet" method="post">
                        <input type="hidden" name="action" value="updateItem">
                        <input type="hidden" name="itemID" value="${item.itemID}">
                        <input type="hidden" name="orderID" value="${order.orderID}">
                        <input type="hidden" name="userName" value="<%= user.getUserName()%>">
                        <input type="number" name="quantity" value="${item.quantity}" min="1">
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>${item.price}</td>
                <td>${item.quantity * item.price}</td>
                <td>
                    <form action="OrderServlet" method="post">
                        <input type="hidden" name="action" value="deleteItem">
                        <input type="hidden" name="itemID" value="${item.itemID}">
                        <input type="hidden" name="orderID" value="${order.orderID}">
                        <input type="hidden" name="userName" value="<%= user.getUserName()%>">
                        <input type="submit" value="Remove">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <p><strong>Total Price: </strong> ${order.totalPrice}</p>

    <form action="OrderServlet" method="post">
        <input type="hidden" name="action" value="clearCart">
        <input type="hidden" name="orderID" value="${order.orderID}">
        <input type="hidden" name="userName" value="<%= user.getUserName()%>">
        <input type="submit" class="btn" style="background: red;" value="Clear Cart">
    </form>
    
    <form action="OrderServlet" method="post">
        <input type="hidden" name="action" value="checkout">
        <input type="hidden" name="orderID" value="${order.orderID}">
        <input type="hidden" name="userName" value="<%= user.getUserName()%>">
        <input type="submit" class="btn" value="Proceed to Checkout">
    </form>

    <a href="orderHistory.jsp" class="btn" >Order History</a>
    <a href="catalog.jsp" class="btn" >Continue Shopping</a>
    <a href="checkout.jsp" class="btn" >Checkout</a>
    
    <footer>
        <p>&copy; 2025 IoTBay-ISD. All rights reserved.</p>
    </footer>
</body>
</html>