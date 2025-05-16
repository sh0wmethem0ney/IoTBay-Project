package dao;

import beans.ProductBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final String jdbcURL = "jdbc:derby://localhost:1527/iotbaydb";
    private final String dbUser = "app";
    private final String dbPassword = "app";
    
    // Create a new product in the database
    public void insertProduct(ProductBean product) {
        String sql = "INSERT INTO PRODUCTS (product_name, product_type, unit_price, quantity, image_url) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductType());
            stmt.setDouble(3, product.getUnitPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setString(5, product.getImageUrl());
            
            int rowsInserted = stmt.executeUpdate();
            
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newProductID = generatedKeys.getInt(1);
                        product.setProductID(newProductID);
                    }
                }
                System.out.println("Product added with ID: " + product.getProductID());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Get all products
    public List<ProductBean> getAllProducts() {
        List<ProductBean> products = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ProductBean product = new ProductBean();
                product.setProductID(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductType(rs.getString("product_type"));
                product.setUnitPrice(rs.getDouble("unit_price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("image_url"));
                
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    // Search products by name and type
    public List<ProductBean> searchProducts(String keyword) {
        List<ProductBean> products = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS WHERE LOWER(product_name) LIKE ? OR LOWER(product_type) LIKE ?";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + keyword.toLowerCase() + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProductBean product = new ProductBean();
                    product.setProductID(rs.getInt("product_id"));
                    product.setProductName(rs.getString("product_name"));
                    product.setProductType(rs.getString("product_type"));
                    product.setUnitPrice(rs.getDouble("unit_price"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setImageUrl(rs.getString("image_url"));
                    
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    // Get product by ID
    public ProductBean getProductById(int productId) {
        String sql = "SELECT * FROM PRODUCTS WHERE product_id = ?";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, productId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProductBean product = new ProductBean();
                    product.setProductID(rs.getInt("product_id"));
                    product.setProductName(rs.getString("product_name"));
                    product.setProductType(rs.getString("product_type"));
                    product.setUnitPrice(rs.getDouble("unit_price"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setImageUrl(rs.getString("image_url"));
                    
                    return product;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Update product
    public boolean updateProduct(ProductBean product) {
        String sql = "UPDATE PRODUCTS SET product_name = ?, product_type = ?, unit_price = ?, quantity = ?, image_url = ? WHERE product_id = ?";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductType());
            stmt.setDouble(3, product.getUnitPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setString(5, product.getImageUrl());
            stmt.setInt(6, product.getProductID());
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Delete product
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM PRODUCTS WHERE product_id = ?";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, productId);
            
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
