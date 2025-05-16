package controller;

import beans.ProductBean;
import dao.ProductDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    
    private ProductDAO productDAO;
    
    @Override
    public void init() {
        productDAO = new ProductDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "search":
                searchProducts(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }
    
    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products", productDAO.getAllProducts());
        request.getRequestDispatcher("catalog.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            ProductBean product = productDAO.getProductById(productId);
            
            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("product-form.jsp").forward(request, response);
            } else {
                response.sendRedirect("product?action=list");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("product?action=list");
        }
    }
    
    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String productName = request.getParameter("productName");
            String productType = request.getParameter("productType");
            double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String imageUrl = request.getParameter("imageUrl");
            
            ProductBean product = new ProductBean(productName, productType, unitPrice, quantity, imageUrl);
            productDAO.insertProduct(product);
            
            response.sendRedirect("product?action=list");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input format");
            request.getRequestDispatcher("product-form.jsp").forward(request, response);
        }
    }
    
    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("productName");
            String productType = request.getParameter("productType");
            double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String imageUrl = request.getParameter("imageUrl");
            
            ProductBean product = new ProductBean(productName, productType, unitPrice, quantity, imageUrl);
            product.setProductID(productId);
            
            productDAO.updateProduct(product);
            
            response.sendRedirect("product?action=list");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input format");
            request.getRequestDispatcher("product-form.jsp").forward(request, response);
        }
    }
    
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            productDAO.deleteProduct(productId);
            
            response.sendRedirect("product?action=list");
        } catch (NumberFormatException e) {
            response.sendRedirect("product?action=list");
        }
    }
    
    private void searchProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            request.setAttribute("products", productDAO.searchProducts(keyword));
        } else {
            request.setAttribute("products", productDAO.getAllProducts());
        }
        
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("catalog.jsp").forward(request, response);
    }
}
