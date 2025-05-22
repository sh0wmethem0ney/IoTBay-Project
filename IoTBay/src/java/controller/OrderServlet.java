package controller;

import dao.OrderDAO;
import beans.OrderBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @Irffan
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String orderDate = java.time.LocalDate.now().toString();
            String orderStatus = "Saved";

            OrderBean order = new OrderBean();
            order.setUserID(userID);
            order.setOrderDate(orderDate);
            order.setOrderStatus(orderStatus);
            
            OrderDAO dao = new OrderDAO();
            boolean result = dao.saveOrder(order);

            if (result) {
                request.setAttribute("message", "Order saved successfully!");
            } else {
                request.setAttribute("message", "Order failed to save.");
            }

            request.getRequestDispatcher("orderSummary.jsp").forward(request, response);
        }
    }
}