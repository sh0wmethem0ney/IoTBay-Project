<%-- 
    Document   : cart
    Created on : 9 May 2025, 3:34:20?am
    Author     : Ayden
--%>

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
   
    <%
        List<CartBean> cart = (List<CartBean>) session.getAttribute("cart");
        double total = 0;
        if (cart != null && !cart.isEmpty()) {
    %>
    <table border="1">
        <tr><th>Product</th><th>Price</th><th>Quantity</th><th>Total</th><th>Action</th></tr>
        <% for (CartBean item : cart) {
               total += item.getTotalPrice();
        %>
        <tr>
            <td><%= item.getProductName() %></td>
            <td><%= item.getProductName().getUnitPrice() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getTotalPrice() %></td>
            <td>
                <form action="CartServlet" method="post">
                    <input type="hidden" name="action" value="remove" />
                    <input type="hidden" name="productID" value="<%= item.getProductName().getProductID() %>" />
                    <input type="submit" class="btn" style="background-color: red;" value="Remove" />
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <h3>Total: <%= total %></h3>
    <% } else { %>
        <p>Your cart is empty.</p>
    <% } %>
    <a href="orderForm.jsp" class="btn" >Continue Order</a>
    <a href="catalog.jsp" class="btn" >Continue Shopping</a>
    <a href="checkout.jsp" class="btn" >Checkout</a>
    
    <footer>
        <p>&copy; 2025 IoTBay-ISD. All rights reserved.</p>
    </footer>
</body>
</html>