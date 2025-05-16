<%@page import="beans.UserBean"%>
<%@page import="beans.ProductBean"%>
<%@page import="java.util.List"%>
<%
    // extracting user info from session
    UserBean user = (UserBean) session.getAttribute("user");
    // back to index if not logged in
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    
    boolean isStaff = user.getRole() == 's' || user.getRole() == 'a';
    List<ProductBean> products = (List<ProductBean>) request.getAttribute("products");
    String keyword = (String) request.getAttribute("keyword");
    if (keyword == null) {
        keyword = "";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>IoT Device Catalog - IoTBay</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/IoTBayCss.css">
    </head>
    <body>
        <a href="main.jsp">
            <img src="images/IoTBay Logo.png" alt="IoTBay Logo" class="logo" style="width: 300px; height: auto; margin: 0 auto; display:block;">
        </a>
        
        <!-- header -->
        <header style="display: flex; justify-content: space-between; align-items: center; padding: 20px;">
            <div>
                <h1>IoT Device Catalog</h1>
            </div>
            <div style="float: right;">
                <a href="main.jsp" class="btn" style="background: darkgreen;">Home</a>
                <% if (isStaff) { %>
                <a href="account.jsp" class="btn" style="background: darkgreen;">Your Account</a>
                <% } else { %>
                <a href="account.jsp" class="btn" style="background: darkgreen;">Your Account</a>
                <a href="cart.jsp" class="btn" style="background: darkgreen;">Your Cart</a>
                <% } %>
                <a href="logout" class="btn" style="background: darkgreen;">Sign Out</a>
            </div>
        </header>
        
        <!-- Search Bar -->
        <div class="search-container">
            <form action="product" method="post" style="display: flex; width: 100%;">
                <input type="hidden" name="action" value="search">
                <input type="text" name="keyword" placeholder="Search by name or type..." value="<%= keyword %>">
                <button type="submit">Search</button>
            </form>
        </div>
        
        <% if (isStaff) { %>
        <!-- Add New Product Button (only visible to staff) -->
        <div class="add-button">
            <a href="product-form.jsp" class="btn" style="background: darkgreen;">Add New Product</a>
        </div>
        
        <!-- Product Table (for staff) -->
        <div style="padding: 0 20px;">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Image URL</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (products != null && !products.isEmpty()) {
                        for (ProductBean product : products) { %>
                    <tr>
                        <td><%= product.getProductID() %></td>
                        <td><%= product.getProductName() %></td>
                        <td><%= product.getProductType() %></td>
                        <td><%= product.getFormattedPrice() %></td>
                        <td><%= product.getQuantity() %></td>
                        <td><%= product.getImageUrl() %></td>
                        <td>
                            <a href="product?action=edit&id=<%= product.getProductID() %>" class="btn" style="background: darkblue; padding: 5px 10px;">Edit</a>
                            <a href="javascript:confirmDelete(<%= product.getProductID() %>)" class="btn" style="background: darkred; padding: 5px 10px;">Delete</a>
                        </td>
                    </tr>
                    <% }
                    } else { %>
                    <tr>
                        <td colspan="7" style="text-align: center;">No products found</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <% } else { %>
        <!-- customers product grid -->
        <div class="product-grid">
            <% if (products != null && !products.isEmpty()) {
                for (ProductBean product : products) { %>
            <div class="product-card">
                <img src="<%= product.getImageUrl() %>" alt="<%= product.getProductName() %>">
                <h3><%= product.getProductName() %></h3>
                <p><strong>Type:</strong> <%= product.getProductType() %></p>
                <p><strong>Price:</strong> <%= product.getFormattedPrice() %></p>
                <% if (product.getQuantity() > 0) { %>
                <p><strong>In Stock:</strong> <%= product.getQuantity() %></p>
                <div class="action-buttons">
                    <a href="cart?action=add&id=<%= product.getProductID() %>" class="btn" style="background: darkgreen; padding: 5px 10px;">Add to Cart</a>
                </div>
                <% } else { %>
                <p><strong>Out of Stock</strong></p>
                <% } %>
            </div>
            <% }
            } else { %>
            <div style="grid-column: 1 / -1; text-align: center;">
                <p>No products found</p>
            </div>
            <% } %>
        </div>
        <% } %>
        
        <footer>
            <p>&copy; 2025 IoTBay-ISD. All rights reserved.</p>
        </footer>
        
        <script>
            function confirmDelete(id) {
                if (confirm("Are you sure you want to delete this product?")) {
                    window.location.href = "product?action=delete&id=" + id;
                }
            }
        </script>
    </body>
</html>
