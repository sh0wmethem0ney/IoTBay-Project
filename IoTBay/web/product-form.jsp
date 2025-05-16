<%@page import="beans.UserBean"%>
<%@page import="beans.ProductBean"%>
<%
    // extracting user info from session
    UserBean user = (UserBean) session.getAttribute("user");
    // back to index if not logged in
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    
    // only staff can access this page
    if (user.getRole() != 's' && user.getRole() != 'a') {
        response.sendRedirect("main.jsp");
        return;
    }
    
    ProductBean product = (ProductBean) request.getAttribute("product");
    boolean isEdit = (product != null);
    
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
    <head>
        <title><%= isEdit ? "Edit" : "Add" %> Product - IoTBay</title>
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
                <h1><%= isEdit ? "Edit" : "Add New" %> Product</h1>
            </div>
            <div style="float: right;">
                <a href="product?action=list" class="btn" style="background: darkgreen;">Back to Catalog</a>
                <a href="main.jsp" class="btn" style="background: darkgreen;">Home</a>
                <a href="logout" class="btn" style="background: darkgreen;">Sign Out</a>
            </div>
        </header>
        
        <div class="form-container">
            <% if (error != null) { %>
            <div class="error-message">
                <p><%= error %></p>
            </div>
            <% } %>
            
            <form action="product" method="post">
                <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">
                <% if (isEdit) { %>
                <input type="hidden" name="productId" value="<%= product.getProductID() %>">
                <% } %>
                
                <div class="form-group">
                    <label for="productName">Product Name</label>
                    <input type="text" id="productName" name="productName" value="<%= isEdit ? product.getProductName() : "" %>" required>
                </div>
                
                <div class="form-group">
                    <label for="productType">Product Type</label>
                    <input type="text" id="productType" name="productType" value="<%= isEdit ? product.getProductType() : "" %>" required>
                </div>
                
                <div class="form-group">
                    <label for="unitPrice">Unit Price ($)</label>
                    <input type="number" id="unitPrice" name="unitPrice" step="0.01" min="0" value="<%= isEdit ? product.getUnitPrice() : "" %>" required>
                </div>
                
                <div class="form-group">
                    <label for="quantity">Quantity in Stock</label>
                    <input type="number" id="quantity" name="quantity" min="0" value="<%= isEdit ? product.getQuantity() : "" %>" required>
                </div>
                
                <div class="form-group">
                    <label for="imageUrl">Image URL</label>
                    <input type="text" id="imageUrl" name="imageUrl" value="<%= isEdit ? product.getImageUrl() : "images/" %>" required>
                </div>
                
                <div class="btn-container">
                    <a href="product?action=list" class="btn" style="background: #6c757d;">Cancel</a>
                    <button type="submit" class="btn" style="background: darkgreen;"><%= isEdit ? "Update" : "Create" %> Product</button>
                </div>
            </form>
        </div>
        
        <footer>
            <p>&copy; 2025 IoTBay-ISD. All rights reserved.</p>
        </footer>
    </body>
</html>