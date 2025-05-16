<%-- 
    Document   : creditCardManagement
    Created on : 14 May 2025, 8:37:56 pm
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
    CreditCardDAO cardDAO = new CreditCardDAO();
    List<CreditCardBean> savedCards = cardDAO.getCreditCardsByUserID(user.getUserID());%>


<!DOCTYPE html>
<html>
    <head>
        <title>Credit Card Information</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/IoTBayCss.css">
    </head>

    <body>
        <header>
            <h1>Manage Your Account</h1>
        </header>
        
        <div class="container">
            <h1>Credit Card Information</h1>
            
            <select id="existingCards" name="existingCard" onchange="showDeleteButton()">
                <% for (CreditCardBean card : savedCards) {
                    String maskedNumber = "**** **** **** " + card.getCardNumber().substring(card.getCardNumber().length() - 4);
                %>
                    <option value="<%= card.getCardID() %>"
                            data-number="<%= card.getCardNumber() %>"
                            data-name="<%= card.getCardName() %>"
                            data-expiry="<%= card.getExpiryDate() %>"
                            data-cvv="<%= card.getCvv() %>">
                        <%= maskedNumber %> - <%= card.getCardName() %>
                    </option>
                <% } %>
            </select>
            <br><br>
            
            <div id="cardInfo" style="margin-top: 20px; display: none;">
                <p><strong>Card Number:</strong> <span id="displayNumber"></span></p>
                <p><strong>Name:</strong> <span id="displayName"></span></p>
                <p><strong>Expiration Date:</strong> <span id="displayExpiry"></span></p>
                <p><strong>CVV:</strong> <span id="displayCVV"></span></p>
            </div>
            
            <form id="deleteCardForm" action="DeleteCardServlet" method="post" style="display: none;">
                <input type="hidden" id="cardToDelete" name="cardID" value="">
                <button type="submit" class="btn" style="background-color: red;">Delete Card</button>
            </form>
            
            
            <br><br>
            
            
            
            
            
            <label>
                <input type="checkbox" id="newCardCheckbox" onchange="toggleCardForm()">
                Register New Credit Card
            </label><br><br>

            <div id="creditCardDetails" style="display: none;">
                <form id="addCardForm" action="AddCardServlet" method="post">
                <p>Card Number</p>
                <input type="text" name="cardNumber" placeholder="Card Number" style="width: 100%;"><br><br>
                <table>
                <tr>
                  <td>Expiration Date</td>
                  <td></td>
                  <td>Security Code (CVV)</td>
                </tr>
                <tr>
                  <td>
                    <input type="text" name="expiryDate" placeholder="MM/YY" style="width: 100%;">
                  </td>
                  <td style="width: 30px;"></td>
                  <td>
                    <input type="text" name="cvv" placeholder="CVV" style="width: 100%;">
                  </td>
                </tr>
              </table>
                <p>Card Holder Name</p>
                <input type="text" name="cardName" placeholder=""><br><br>
                
                <button type="submit" class="btn">Add Card</button>
                
                </form><br>
            </div>
            
            <a href="accountManagement.jsp" class="btn">Back</a>
        </div>
        
            
            
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                const dropdown = document.getElementById('existingCards');
                const hiddenInput = document.getElementById('cardToDelete');
                const cardInfoDiv = document.getElementById('cardInfo');
                const deleteForm = document.getElementById('deleteCardForm');

                function updateDisplay() {
                    const selected = dropdown.options[dropdown.selectedIndex];
                    if (!selected || selected.value === "") {
                        cardInfoDiv.style.display = "none";
                        deleteForm.style.display = "none";
                        hiddenInput.value = "";
                        return;
                    }

                    // Update card info display
                    document.getElementById('displayNumber').innerText = selected.getAttribute('data-number');
                    document.getElementById('displayName').innerText = selected.getAttribute('data-name');
                    document.getElementById('displayExpiry').innerText = selected.getAttribute('data-expiry');
                    document.getElementById('displayCVV').innerText = selected.getAttribute('data-cvv');

                    // Show card info and delete button
                    cardInfoDiv.style.display = "block";
                    deleteForm.style.display = "block";

                    // Update hidden input for deletion
                    hiddenInput.value = selected.value;
                }

                dropdown.addEventListener('change', updateDisplay);

                // Run once on page load to set initial state
                updateDisplay();
            });
            
            function toggleCardForm() {
                const checkbox = document.getElementById("newCardCheckbox");
                const form = document.getElementById("creditCardDetails");
                const dropdown = document.getElementById("existingCards");

                if (checkbox.checked) {
                    form.style.display = "block";
                //    dropdown.style.display = "none";
                } else {
                    form.style.display = "none";
                    dropdown.style.display = "inline";
                }
            }
            
            function showDeleteButton() {
                const dropdown = document.getElementById("existingCards");
                const selectedCardId = dropdown.value;

                if (selectedCardId) {
                    document.getElementById("deleteCardForm").style.display = "block";
                    document.getElementById("cardToDelete").value = selectedCardId;
                } else {
                    document.getElementById("deleteCardForm").style.display = "none";
                }
            }


            const dropdown = document.getElementById("existingCards");
            const hiddenInput = document.getElementById("cardToDelete");

            function updateCardId() {
                hiddenInput.value = dropdown.value;
            }

            dropdown.addEventListener("change", updateCardId);
            window.addEventListener("load", updateCardId);
            
            
            
        </script>
        
    </body>
</html>
