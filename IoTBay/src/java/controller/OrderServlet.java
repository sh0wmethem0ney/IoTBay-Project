package controller;

import beans.CartBean;
import beans.OrderBean;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
/**
 *
 * @Irffan
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;
    
    @Override
    public void init() {
        orderDAO = new OrderDAO();
        productDAO = new ProductDAO();
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "addToCart":
                    addToCart(request, response);
                    break;
                case "updateItem":
                    updateItem(request, response);
                    break;
                case "deleteItem":
                    deleteItem(request, response);
                    break;
                case "clearCart":
                    clearCart(request, response);
                    break;
                case "checkout":
                    checkout(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("viewCart".equals(action)) {
                viewCart(request, response);
            }
            else if ("orderHistory".equals(action)) {
                orderHistory(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String userName = request.getParameter("user_Id");
        int productID = Integer.parseInt(request.getParameter("product_Id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        double price = productDAO.getProductById(productID).getUnitPrice();
        OrderBean activeOrder = orderDAO.getActiveOrder(userName);

        if (activeOrder == null) {
            int newOrderId = orderDAO.createNewOrder(userName);
            activeOrder = new OrderBean(newOrderId, userName, 0.0, "Saved");
        }

        orderDAO.addOrUpdateItem(activeOrder.getOrderID(), productID, quantity, price);
        orderDAO.updateTotalPrice(activeOrder.getOrderID());

        response.sendRedirect("OrderServlet?action=viewCart&user_name=" + userName);
    }

    private void viewCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String userName = request.getParameter("user_name");
        OrderBean order = orderDAO.getActiveOrder(userName);
        List<CartBean> items = orderDAO.getOrderItems(order.getOrderID());
        request.setAttribute("order", order);
        request.setAttribute("items", items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
        dispatcher.forward(request, response);
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int itemID = Integer.parseInt(request.getParameter("item_Id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int orderID = Integer.parseInt(request.getParameter("order_Id"));
        String userName = request.getParameter("user_name");

        orderDAO.updateItemQuantity(itemID, quantity);
        orderDAO.updateTotalPrice(orderID);

        response.sendRedirect("OrderServlet?action=viewCart&user_name=" + userName);
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int itemID = Integer.parseInt(request.getParameter("item_Id"));
        int orderID = Integer.parseInt(request.getParameter("order_Id"));
        String userName = request.getParameter("user_name");

        orderDAO.deleteItem(itemID);
        orderDAO.updateTotalPrice(orderID);

        response.sendRedirect("OrderServlet?action=viewCart&user_name=" + userName);
    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int orderID = Integer.parseInt(request.getParameter("order_Id"));
        String userName = request.getParameter("user_name");

        orderDAO.clearOrder(orderID);
        orderDAO.updateTotalPrice(orderID);

        response.sendRedirect("OrderServlet?action=viewCart&user_name=" + userName);
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int orderID = Integer.parseInt(request.getParameter("order_Id"));
        String userName = request.getParameter("user_name");

        orderDAO.finalizeOrder(orderID);

        response.sendRedirect("checkout.jsp");
    }
    
    private void orderHistory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String userName = (String) request.getSession().getAttribute("userName");
        List<OrderBean> orders = orderDAO.getOrderHistory(userName);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
    }
}

        /*String userID = request.getParameter("userID");
        try {
            List<OrderBean> orders = orderDAO.getOrdersByUserID(userID);
            request.setAttribute("orders", orders);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        OrderBean order = new OrderBean();
        order.setOrderID(Integer.parseInt(request.getParameter("order_Id")));
        order.setUserID(request.getParameter("user_Id"));
        order.setProductID(Integer.parseInt(request.getParameter("product_Id")));
        order.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        order.setPrice(Double.parseDouble(request.getParameter("price")));
        order.setOrderStatus("Saved");
        orderDAO.createOrder(order);
        response.sendRedirect("order.jsp");
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        OrderBean order = new OrderBean();
        order.setOrderID(Integer.parseInt(request.getParameter("order_Id")));
        order.setProductID(Integer.parseInt(request.getParameter("product_Id")));
        order.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        order.setPrice(Double.parseDouble(request.getParameter("price")));
        order.setOrderStatus(request.getParameter("status"));
        orderDAO.updateOrder(order);
        response.sendRedirect("OrderServlet?user_Id=" + request.getParameter("customer_Id"));
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        orderDAO.deleteOrder(orderId);
        response.sendRedirect("OrderServlet?customerId=" + request.getParameter("customerId"));
    }
    
}*/