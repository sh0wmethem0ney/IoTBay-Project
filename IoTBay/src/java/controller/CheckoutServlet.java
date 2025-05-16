package controller;

import dao.CreditCardDAO;


import beans.CreditCardBean;
import beans.UserBean;
//import dao.CreditCardDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp"); // user not logged in
            return;
        }

        // Get credit card details
        String cardNumber = request.getParameter("cardNumber");
        String cardName = request.getParameter("cardName");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        // Optional: Get new address if provided
        String newAddress = request.getParameter("newAddress");

        // Validation
        if (cardNumber == null || cardNumber.trim().isEmpty() ||
            cardName == null || cardName.trim().isEmpty() ||
            expiryDate == null || expiryDate.trim().isEmpty() ||
            cvv == null || cvv.trim().isEmpty()) {

            request.setAttribute("errorMessage", "Please fill in all credit card fields.");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
            return;
        }

        // Create CreditCardBean
        CreditCardBean card = new CreditCardBean();
        card.setCardNumber(cardNumber);
        card.setCardName(cardName);
        card.setExpiryDate(expiryDate);
        card.setCvv(cvv);
        card.setUserID(user.getUserID());

        try {
            // Store card in database
            CreditCardDAO dao = new CreditCardDAO();
            dao.insertCreditCard(card);

            // Optional: update user's address if new address is provided
            //if (newAddress != null && !newAddress.trim().isEmpty()) {
            //    user.setAddress(newAddress);
                // You’d also update the address in the database if needed
                // dao.updateUserAddress(user.getUserID(), newAddress);
            //}

            session.setAttribute("checkoutSuccess", true);
            response.sendRedirect("confirmation.jsp");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Something went wrong: " + e.getMessage());
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }
    }
}
