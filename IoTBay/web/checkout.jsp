<%-- 
    Document   : checkout
    Created on : 9 May 2025, 1:14:22 pm
    Author     : Ayden
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.UserBean"%>
<%@page import="beans.CreditCardBean"%>
<%@page import="dao.CreditCardDAO"%>
<%@page import="java.util.*" %>


<%    
    UserBean user = (UserBean) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    boolean paymentUnlocked = false;
    if (request.getMethod().equalsIgnoreCase("POST")) {
        paymentUnlocked = true;
    }
    
    boolean showSummary = false;
    
    String paymentMethod = request.getParameter("paymentMethod");
    String addressOption = request.getParameter("deliveryAddressOption");

    if ("credit".equals(paymentMethod) || "paypal".equals(paymentMethod)) {
        if ("saved".equals(addressOption) || "new".equals(addressOption)) {
            showSummary = true;
        }
    }
        
    CreditCardDAO cardDAO = new CreditCardDAO();
    List<CreditCardBean> savedCards = cardDAO.getCreditCardsByUserID(user.getUserID());

    if (request.getMethod().equalsIgnoreCase("POST")) {
        //String paymentMethod = request.getParameter("paymentMethod"); ADD BACK IF NOT WORKING

        if ("credit".equals(paymentMethod)) {
            String creditOption = request.getParameter("creditOption");

            if ("new".equals(creditOption)) {
                String cardNumber = request.getParameter("cardNumber");
                String cardName = request.getParameter("cardName");
                String expiryDate = request.getParameter("expiryDate");
                String cvv = request.getParameter("cvv");

                int userID = user.getUserID();

                CreditCardBean creditCard = new CreditCardBean();
                creditCard.setUserID(userID);
                creditCard.setCardNumber(cardNumber);
                creditCard.setCardName(cardName);
                creditCard.setExpiryDate(expiryDate);
                creditCard.setCvv(cvv);

                //CreditCardDAO cardDAO = new CreditCardDAO();
                cardDAO.insertCreditCard(creditCard);

                out.println("Credit card has been registered successfully.");
            }
        }
    }
%>



<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="css/IoTBayCss.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        #paymentSection {
            opacity: <%= paymentUnlocked ? "1" : "0.5" %>;
            pointer-events: <%= paymentUnlocked ? "auto" : "none" %>;
            transition: opacity 0.3s ease;
        }
        <%--#orderSummary {
            opacity: 0.5;
            pointer-events: none;
            transition: opacity 0.3s ease;
        }<%--just greys out the payment and summary section when info isnt complete--%>--%>
        
        <%--#deliverySection,
        #paymentSection {
            opacity: <%= showSummary ? "0.5" : "1" %>;
            pointer-events: <%= showSummary ? "none" : "auto" %>;
            transition: opacity 0.3s ease;
        }--%>
    </style>
    
</head>
<body>
    
    <a href="main.jsp">
        <img src="images/IoTBay Logo.png" alt="IoTBay Logo" style="width: 300px; height: atuo; margin: 0 auto; display:block;">
    </a>
    

    
    <header>
        <h1>Checkout</h1>
    </header>    
    
    
    <div class="widecontainer">
    <div id="deliverySection">
    
    <h1>1. Delivery Info</h1>
    
    <form action="checkout.jsp" method="post">
        <h3>Select Delivery Address:</h3>

        <label>
            <input type="radio" name="deliveryAddressOption" value="saved" onclick="toggleNewAddress()" />
            Use Saved Address: <strong><%= user.getAddress() %></strong>
        </label><br><br>

        <label>
            <input type="radio" name="deliveryAddressOption" value="new" id="newAddressOption" onclick="toggleNewAddress()" />
            Enter New Address:
        </label>

        <div id="newAddressSection" style="display: none; margin-top: 10px;">
            <textarea name="newAddress" rows="3" cols="40" placeholder="Enter your new delivery address here..."></textarea>
        </div>

        <br>
        <button type="button" onclick="unlockPayment()" class="btn">Proceed to Payment</button>
    </div>
    <%-- delivery options--%>
    
    
    
    
    
    <div id="paymentSection">
        <h1>2. Payment Info</h1>

            <h3>Select Payment Method:</h3>

            <label>
                <input type="radio" name="paymentMethod" value="credit" onclick="toggleCreditSection()" />
                Credit Card
            </label><br>

            <label>
                <input type="radio" name="paymentMethod" value="paypal" onclick="toggleCreditSection()" />
                PayPal
            </label><br><br>
            
            
            
            <div id="creditCardOptions" style="display: none; margin-left: 20px;">
                <label>
                    <input type="radio" name="creditOption" value="existing" onclick="toggleCardForm()" />
                    Use Existing Credit Card
                </label><br><br>

                <select id="existingCards" name="existingCard" style="display: none;">
                    <% for (CreditCardBean card : savedCards) {
                        String maskedNumber = "**** **** **** " + card.getCardNumber().substring(card.getCardNumber().length() - 4);
                    %>
                        <option value="<%= card.getCardID() %>"><%= maskedNumber %> - <%= card.getCardName() %></option>
                    <% } %>
                </select><br><br>


                <label>
                    <input type="radio" name="creditOption" value="new" onclick="toggleCardForm()" />
                    Register New Credit Card
                </label><br><br>

                <div id="creditCardDetails" style="display: none;">
                    <p>Card Number</p>
                    <input type="text" name="cardNumber" placeholder="Card Number" style="width: 50%;"><br><br>
                    <table>
                    <tr>
                      <td>Expiration Date</td>
                      <td></td>
                      <td>Security Code (CVV)</td>
                    </tr>
                    <tr>
                      <td>
                        <input type="text" name="expiryDate" placeholder="MM/YY">
                      </td>
                      <td style="width: 30px;"></td><%--creates gap between expiry and cvv--%>
                      <td>
                        <input type="text" name="cvv" placeholder="CVV">
                      </td>
                    </tr>
                  </table>
                    <p>Card Holder Name</p>
                    <input type="text" name="cardName" placeholder=""><br><br>
                </div>
            </div>
            
            

            <button type="submit" onclick="confirmPayment();" class="btn">Confirm Payment</button>
        </form>
    </div>
    
    <%--<h1 id="orderSummary">3. Order Summary</h1>--%>
    
    <%
        //String paymentMethod = request.getParameter("paymentMethod");
        paymentMethod = request.getParameter("paymentMethod");
        if ("credit".equals(paymentMethod) || "paypal".equals(paymentMethod)) {
    %>

    <% if (showSummary) { %>
    <div id="orderSummary">
        <h1>3. Order Summary</h1>
        <p><strong>Delivery Address:</strong><br>
        <%
            //String addressOption = request.getParameter("deliveryAddressOption");
            addressOption = request.getParameter("deliveryAddressOption");
            if ("saved".equals(addressOption)) {
                out.print(user.getAddress());
            } else if ("new".equals(addressOption)) {
                String newAddress = request.getParameter("newAddress");
                out.print(newAddress != null ? newAddress : "No address provided");
            } else {
                out.print("No delivery address selected.");
            }
        %>
        </p>

        <p><strong>Payment Method:</strong><br>
        <%= paymentMethod.equals("credit") ? "Credit Card" : "PayPal" %></p>
        

        <h3>Cart Items:</h3>
        <ul>
            <li>item 1 - $10</li>
            <li>Item 2 - $15</li>
        </ul>
        <p><strong>Total: $25.00</strong></p>
        
        <form action="checkout.jsp" method="get" style="display: inline;">
            <button type="submit" class="btn" style="background-color: red;">Back</button>
        </form>

        <form action="confirmOrder.jsp" method="post" style="display: inline;">
            <button type="submit" class="btn">Place Order</button>
        </form>
    </div>
    <% } %>

        
        
    <% 
        } 
    %>
    </div>
    

    
    
    
    
    <script>
        function toggleNewAddress() {
            var newAddressSection = document.getElementById("newAddressSection");
            var newAddressRadio = document.getElementById("newAddressOption");
            newAddressSection.style.display = newAddressRadio.checked ? "block" : "none";
        } 
        
        function unlockPayment() {
            const paymentSection = document.getElementById("paymentSection");
            paymentSection.style.opacity = "1";               // makes it fully visible
            paymentSection.style.pointerEvents = "auto";      // enables clicking          
        }
        
        //function unlockSummary() {
        //    const summarySection = document.getElementById("orderSummary");
        //    summarySection.style.opacity = "1";     
        //}
        
        function disablePaymentAndDelivery() {
            const deliverySection = document.getElementById("deliverySection");
            const paymentSection = document.getElementById("paymentSection");

            deliverySection.style.opacity = "0.5";
            deliverySection.style.pointerEvents = "none"; 

            paymentSection.style.opacity = "0.5";
            paymentSection.style.pointerEvents = "none";
        }

        function confirmPayment() {
            disablePaymentAndDelivery(); // Disable both sections
        }
        
        
        function toggleCreditSection() {
            const selected = document.querySelector('input[name="paymentMethod"]:checked').value;
            const options = document.getElementById("creditCardOptions");
            const creditDetails = document.getElementById("creditCardDetails");
            const existingDropdown = document.getElementById("existingCards");

            if (selected === "credit") {
                options.style.display = "block";
            } else {
                options.style.display = "none";
                creditDetails.style.display = "none";
                existingDropdown.style.display = "none";
            }
        }

        
        function toggleCardForm() {
            const selectedOption = document.querySelector('input[name="creditOption"]:checked').value;
            const form = document.getElementById("creditCardDetails");
            const dropdown = document.getElementById("existingCards");

            if (selectedOption === "existing") {
                form.style.display = "none";
                dropdown.style.display = "inline";
            } else if (selectedOption === "new") {
                form.style.display = "block";
                dropdown.style.display = "none";
            }
        }
        
    </script>
    
</body>
</html>