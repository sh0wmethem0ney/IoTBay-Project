package controller;

import dao.CreditCardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DeleteCardServlet", urlPatterns = {"/DeleteCardServlet"})
public class DeleteCardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cardID = request.getParameter("cardID");

        if (cardID != null && !cardID.isEmpty()) {
            CreditCardDAO dao = new CreditCardDAO();
            dao.deleteCardByID(cardID);
        }

        response.sendRedirect("creditCardManagement.jsp");
    }
}
