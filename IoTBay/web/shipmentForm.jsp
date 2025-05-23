<%--
    Document   : shipmentForm
    Created on : 21/05/2025, 4:47:11 PM
    Author     : 61451
--%>

<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="beans.UserBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Redirect to login if user not in session
    UserBean user = (UserBean) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Shipment Details - IoTBay</title>
  <link rel="stylesheet" href="css/IoTBayCss.css">
</head>
<body>
  <!-- Logo -->
  <a href="main.jsp">
    <img src="images/IoTBay Logo.png"
         alt="IoTBay Logo"
         style="width:300px; height:auto; margin:0 auto; display:block;">
  </a>

  <!-- Header / Navigation -->
  <header style="display: flex; justify-content: space-between; align-items: center; padding: 20px;">
    <div><h1>Welcome, <%= user.getUserName() %>!</h1></div>
    <div style="float:right;">
      <a href="accountManagement.jsp" class="btn" style="background: darkgreen;">Your Account</a>
      <a href="cart.jsp" class="btn" style="background: darkgreen;">Your Cart</a>
      <a href="logout" class="btn" style="background: darkgreen;">Sign Out</a>
    </div>
  </header>

  <!-- Main content area -->
  <div style="padding: 20px;">
    <h2>Shipment Details</h2>

    <form action="SaveShipmentServlet" method="post">
      <input type="hidden" name="orderId" value="${param.orderId}" />

      <!-- Shipment Method -->
      <label>Method:</label><br/>
      <input type="radio" name="method" value="Home Delivery" checked/> Home Delivery<br/>
      <input type="radio" name="method" value="Post Office Pickup"/> Post Office Pickup<br/><br/>

      <!-- Ship Date -->
      <label>Ship Date:</label>
      <select name="shipDate">
        <%
          LocalDate today = LocalDate.now();
          for (int i = 1; i <= 7; i++) {
            out.print("<option value='" + today.plusDays(i) + "'>" +
              today.plusDays(i) + "</option>");
          }
        %>
      </select><br/><br/>

      <!-- Preferred Time Slot -->
      <label>Preferred Delivery Time:</label>
      <select name="timeSlot">
        <option>Morning (8 am-12 pm)</option>
        <option>Afternoon (12 pm-4 pm)</option>
        <option>Evening (4 pm-8 pm)</option>
      </select><br/><br/>

      <!-- Additional Comments -->
      <label>Additional Comments:</label><br/>
      <textarea name="comments" rows="4" cols="50"
                placeholder="e.g. Leave at back door if not home..."></textarea>
      <br/><br/>

      <button type="button" class="btn">Save Shipment Details</button>
     
    </form>

    <p>
      <a href="checkout.jsp" class="btn">Back to Checkout</a>
      <a href="accountManagement.jsp" class="btn">Back to My Account</a>
    </p>
  </div>

  <!-- Optional footer -->
  <footer style="text-align:center; margin-top:40px;">
    <p>&copy; 2025 IoTBay Inc.</p>
  </footer>
</body>
</html>