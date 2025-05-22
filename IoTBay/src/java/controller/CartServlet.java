package controller;

import dao.OrderDAO;
import beans.CartBean;
import beans.OrderBean;
import beans.ProductBean;
import dao.ProductDAO;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author user
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        List<CartBean> cart = (List<CartBean>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        if ("add".equals(action)) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Fetch product info (mocked for now)
            ProductDAO dao = new ProductDAO();
            ProductBean productName = dao.getProductById(productID);

            boolean found = false;
            for (CartBean item : cart) {
                if (item.getProductName().getProductID() == productID) {
                    item.setQuantity(item.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cart.add(new CartBean(productName, quantity));
            }

        } else if ("remove".equals(action)) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            cart.removeIf(item -> item.getProductName().getProductID() == productID);
        }

        response.sendRedirect("cart.jsp");
    }
}
