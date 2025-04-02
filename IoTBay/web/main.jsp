<%@page import="beans.CustomerBean"%>
<%
    // extracting user info from session
    CustomerBean customer = (CustomerBean) session.getAttribute("user");

    // back to index if not logged in
    if (customer == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Main - IoTBay</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/IoTBayCss.css">
    </head>
    <body>

        <a href="index.jsp">
            <img src="images/IoTBay Logo.png" alt="IoTBay Logo" class="logo" style="width: 300px; height: atuo; margin: 0 auto; display:block;">
        </a>

        <!-- welcome message -->
        <header style="clear: both; text-align: center; padding: 20px;">
            <div>
                <h1>Welcome, <%= customer.getCustomerName() %>!</h1>
            </div>

            <div style="float: right; margin: 20px;">
                <a href="account.jsp" class="btn">Your Account</a>
                <a href="cart.jsp" class="btn">Your Cart</a>
                <a href="logout" class="btn">Sign Out</a>
            </div>
        </header>

        <br>
        <br>
        <br>

        <!-- product images area -->
        <main>
            <div style="display: flex; justify-content: center; gap: 40px; flex-wrap: wrap; padding: 20px;">
        
        <!-- Product section -->
            <div style="text-align: center;">
                <img src="images/speaker.jpeg" style="width: 200px; height: auto; border-radius: 10px;">
                <h3>Smart Sensor</h3>
                <p>Price: $29.99</p>
                <button class="btn">Add to Cart</button>
            </div>

            <!-- Product 2 -->
            <div style="text-align: center;">
                <img src="images/product2.jpg" style="width: 200px; height: auto; border-radius: 10px;">
                <h3>B</h3>
                <p>Price: $19.99</p>
                <button class="btn">Add to Cart</button>
            </div>

            <!-- Product 3 -->
            <div style="text-align: center;">
                <img src="images/product3.jpg" style="width: 200px; height: auto; border-radius: 10px;">
                <h3>C</h3>
                <p>Price: $24.99</p>
                <button class="btn">Add to Cart</button>
            </div>
        
    </div>
            
    <div class="promo-banner" style="">
        <img src="images/bottom image.jpeg" style="">
        <div class="promo-text">
            <h2>Spring IoT Sale!</h2>
            <p>Get up to 50% off on selected smart devices. Limited time only!</p>
        </div>
    </div>
        </main>

        <br>
        <br>
        <br>

        <footer>
            <p>&copy; 2025 IoTBay-ISD. All rights reserved.</p>
        </footer>

    </body>
</html>