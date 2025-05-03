<%@page import="beans.UserBean"%>
<%
    // extracting user info from session
    UserBean user = (UserBean) session.getAttribute("user");

    // back to index if not logged in
    if (user == null) {
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

        <a href="main.jsp">
            <img src="images/IoTBay Logo.png" alt="IoTBay Logo" class="logo" style="width: 300px; height: auto; margin: 0 auto; display:block;">
        </a>

        <!-- welcome message -->
        <header style="display: flex; justify-content: space-between; align-items: center; padding: 20px;">
            <div>
                <h1>Welcome, <%= user.getUserName() %>!</h1>
            </div>

            <div style="float: right;">
                <a href="account.jsp" class="btn" style="background: darkgreen;">Your Account</a>
                <a href="cart.jsp" class="btn" style="background: darkgreen;">Your Cart</a>
                <a href="logout" class="btn" style="background: darkgreen;">Sign Out</a>
            </div>
            
        </header>
            
            <div class="promo-banner" style="">
                <a href="main.jsp"><img src="images/bottomImage.jpeg" style="width: 100%;height: 300px;object-fit: cover;display: block;"></a>
                <div style="position: absolute; top: 25%; left: 50%; transform: translate(-50%, -50%); background: rgba(255,255,255,0.3); color: #002222; padding: 20px 40px; border-radius: 12px; text-align: center; backdrop-filter: blur(6px); box-shadow: 0 4px 10px rgba(0,0,0,0.2);">
                    <h2 style="margin: 0; font-size: 28px;">BIG SALE!</h2>
                    <p style="margin-top: 10px; font-size: 18px;">UPTO 50% OFF THIS WEEK! GRAB NOW!</p>
                </div>
            
            </div>

        <br>
        <br>
        <br>

        <!-- product images area -->
        <main>
            <div style="display: flex; justify-content: center; gap: 40px; flex-wrap: wrap; padding: 20px;">
        
        <!-- Product section -->
            <div style="text-align: center;">
                <img src="images/speaker.jpeg" style="width: 200px; height: 300px; border-radius: 10px;">
                <h3>Smart Speaker</h3>
                <p>$59.99</p>
                <button class="btn">Add to Cart</button>
            </div>

            <div style="text-align: center;">
                <img src="images/smartBlind.png" style="width: 200px; height: 300px; border-radius: 10px;">
                <h3>Smart Blind System</h3>
                <p>$169.99</p>
                <button class="btn">Add to Cart</button>
            </div>

            <div style="text-align: center;">
                <img src="images/smartCam.png" style="width: 200px; height: 300px; border-radius: 10px;">
                <h3>Smart Home Cam</h3>
                <p>$124.99</p>
                <button class="btn">Add to Cart</button>
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