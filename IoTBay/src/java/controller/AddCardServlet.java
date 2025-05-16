package controller;

import dao.CreditCardDAO;
import beans.CreditCardBean;
import beans.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AddCardServlet", urlPatterns = {"/AddCardServlet"})
public class AddCardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp"); // user not logged in
            return;
        }

        // Get credit card details from form
        String cardNumber = request.getParameter("cardNumber");
        String cardName = request.getParameter("cardName");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        // Validate fields
        if (cardNumber == null || cardNumber.trim().isEmpty() ||
            cardName == null || cardName.trim().isEmpty() ||
            expiryDate == null || expiryDate.trim().isEmpty() ||
            cvv == null || cvv.trim().isEmpty()) {

            request.setAttribute("errorMessage", "Please fill in all credit card fields.");
            request.getRequestDispatcher("creditCardManagement.jsp").forward(request, response);
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
            CreditCardDAO dao = new CreditCardDAO();
            dao.insertCreditCard(card);

            session.setAttribute("addCardSuccess", "Credit card added successfully.");

            response.sendRedirect("creditCardManagement.jsp");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error adding credit card: " + e.getMessage());
            request.getRequestDispatcher("creditCardManagement.jsp").forward(request, response);
        }
    }
}
