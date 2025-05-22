<%@page import="beans.UserBean"%>
<%@page import="beans.ProductBean"%>
<%@page import="dao.ProductDAO"%>
<%@page import="java.util.List"%>
<%
    UserBean user = (UserBean) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    ProductDAO productDAO = new ProductDAO();
    List<ProductBean> featuredProducts = productDAO.getAllProducts();
    boolean isStaff = user.getRole() == 's' || user.getRole() == 'a';
%>
<!DOCTYPE html>
<html>
<head>
    <title>Main - IoTBay</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/IoTBayCss.css">
</head>
<body>

<a href="main.jsp">
    <img src="images/IoTBay Logo.png" alt="IoTBay Logo" class="logo">
</a>

<header class="page-header">
    <div>
        <h1>Welcome, <%= user.getUserName() %>!</h1>
    </div>
    <div class="header-links">
        <a href="accountManagement.jsp" class="btn">Your Account</a>
        <% if (isStaff) { %>
            <a href="product?action=list" class="btn">Manage Catalog</a>
        <% } else { %>
            <a href="cart.jsp" class="btn">Your Cart</a>
        <% } %>
        <a href="logout" class="btn">Sign Out</a>
    </div>
</header>

<div class="promo-banner">
    <img src="images/bottomImage.jpeg" class="promo-img">
    <div class="promo-overlay">
        <h2>BIG SALE!</h2>
        <p>UP TO 50% OFF THIS WEEK! GRAB NOW!</p>
    </div>
</div>

<h2 class="section-title">Featured Products</h2>

<main>
    <div class="product-section">
        <%
            int displayCount = Math.min(featuredProducts.size(), 3);
            for (int i = 0; i < displayCount; i++) {
                ProductBean product = featuredProducts.get(i);
        %>
        <div class="product-card">
            <img src="<%= product.getImageUrl() %>" alt="<%= product.getProductName() %>">
            <h3><%= product.getProductName() %></h3>
            <p><%= product.getFormattedPrice() %></p>
            <% if (!isStaff) { %>
                <a href="cart?action=add&id=<%= product.getProductID() %>" class="btn">Add to Cart</a>
            <% } %>
        </div>
        <% } %>
    </div>

    <a href="product?action=list" class="btn-catalog">View Full Catalog</a>
</main>

<footer>
    <p>&copy; 2025 IoTBay-ISD. All rights reserved.</p>
</footer>

</body>
</html>
