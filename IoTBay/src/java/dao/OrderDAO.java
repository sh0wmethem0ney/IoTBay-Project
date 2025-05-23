package dao;


import beans.OrderBean;
import beans.CartBean;
import java.sql.*;
import java.util.*;

public class OrderDAO {
    private final String jdbcURL = "jdbc:derby://localhost:1527/iotbaydb";
    private final String dbUser = "app";
    private final String dbPassword = "app";
    
    private Connection conn() throws SQLException {
        return DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
    }
    
    public OrderBean getActiveOrder(String userName) throws SQLException {
        String sql = "SELECT * FROM Orders WHERE user_name = ? AND status = 'Saved'";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new OrderBean(
                    rs.getInt("order_Id"), 
                    rs.getString("user_name"), 
                    rs.getDouble("total_price"),
                    rs.getString("status"));
            }
        }
        return null;
    }
    
    public int createNewOrder(String userName) throws SQLException {
        String sql = "INSERT INTO Orders (user_name, total_price, status) VALUES (?, ?, 'Saved')";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, userName);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }
    
    public void addOrUpdateItem(int orderID, int productID, int quantity, double price) throws SQLException {
        String find = "SELECT * FROM Cart WHERE order_Id = ? AND product_Id = ?";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(find)) {
            ps.setInt(1, orderID);
            ps.setInt(2, productID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int existingQty = rs.getInt("quantity");
                int newQty = existingQty + quantity;
                String update = "UPDATE Cart SET quantity = ? WHERE order_Id = ? AND product_Id = ?";
                try (PreparedStatement ups = conn.prepareStatement(update)) {
                    ups.setInt(1, newQty);
                    ups.setInt(2, orderID);
                    ups.setInt(3, productID);
                    ups.executeUpdate();
                }
            } else {
                String insert = "INSERT INTO Cart (order_Id, product_Id, quantity, price) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ins = conn.prepareStatement(insert)) {
                    ins.setInt(1, orderID);
                    ins.setInt(2, productID);
                    ins.setInt(3, quantity);
                    ins.setDouble(4, price);
                    ins.executeUpdate();
                }
            }
        }
    }
    
    public List<CartBean> getOrderItems(int orderID) throws SQLException {
        List<CartBean> items = new ArrayList<>();
        String sql = "SELECT * FROM Cart WHERE order_Id = ?";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(new CartBean(
                    rs.getInt("item_Id"),
                    rs.getInt("order_Id"),
                    rs.getInt("product_Id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                ));
            }
        }
        return items;
    }
    
    public void updateItemQuantity(int item_Id, int newQty) throws SQLException {
        String sql = "UPDATE Cart SET quantity = ? WHERE item_Id = ?";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newQty);
            ps.setInt(2, item_Id);
            ps.executeUpdate();
        }
    }
    
    // Total price
    public void updateTotalPrice(int orderID) throws SQLException {
        String sql = "SELECT SUM(quantity * price) AS total FROM Cart WHERE order_Id = ?";
        double total = 0;
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        }
        String update = "UPDATE Orders SET totalPrice = ? WHERE order_Id = ?";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setDouble(1, total);
            ps.setInt(2, orderID);
            ps.executeUpdate();
        }
    }
    // Clear product/order
    public void deleteItem(int itemID) throws SQLException {
        String sql = "DELETE FROM Cart WHERE item_Id = ?";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemID);
            ps.executeUpdate();
        }
    }
    
    public void clearOrder(int orderID) throws SQLException {
        String sql = "DELETE FROM Cart WHERE order_Id = ?";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ps.executeUpdate();
        }
    }
    // Submit order
    public void finalizeOrder(int orderID) throws SQLException {
        String sql = "UPDATE Orders SET status = 'Finalized' WHERE order_Id = ?";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ps.executeUpdate();
        }
    }
    
    public List<OrderBean> getOrderHistory(String userName) throws SQLException {
        List<OrderBean> orderList = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE user_name = ? AND status <> 'Saved' ORDER BY order_ID DESC";

        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderBean order = new OrderBean();
                order.setOrderID(rs.getInt("order_Id"));
                order.setUserName(rs.getString("user_name"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setOrderStatus(rs.getString("OrderStatus"));
                orderList.add(order);
            }
        }
        return orderList;
    }
}
    /*public void createOrder(OrderBean order) throws SQLException {
        String sql = "INSERT INTO Orders (user_Id, total_price, status) VALUES (?, ?, ?)"; //add date if can get working
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getOrderID());
            ps.setString(2, order.getUserName());
            ps.setInt(3, order.getProductName());
            ps.setInt(4, order.getQuantity());
            ps.setDouble(5, order.getPrice());
            ps.setString(6, order.getOrderStatus());
            ps.executeUpdate();
        }
    }
    
    public List<OrderBean> getOrdersByUserName(String userName) throws SQLException {
        String sql = "SELECT * FROM Orders WHERE user_Id = ?";
        List<OrderBean> orders = new ArrayList<>();
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderBean o = new OrderBean(
                    rs.getInt("order_Id"),
                    rs.getString("user_Id"),
                    rs.getInt("product_Id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("status")
                );
                orders.add(o);
            }
        }
        return orders;
    }
    
    public void updateOrder(OrderBean order) throws SQLException {
        String sql = "UPDATE Orders SET product_Id=?, quantity=?, price=?, status=? WHERE orderId=?";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getProductName());
            ps.setInt(2, order.getQuantity());
            ps.setDouble(3, order.getPrice());
            ps.setString(4, order.getOrderStatus());
            ps.setInt(5, order.getOrderID());
            ps.executeUpdate();
        }
    }
    
    public void deleteOrder(int orderID) throws SQLException {
        String sql = "DELETE FROM Orders WHERE order_Id=?";
        try (Connection conn = conn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ps.executeUpdate();
        }
    }
    
}*/