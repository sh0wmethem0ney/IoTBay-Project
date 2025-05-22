package dao;

/**
 *
 * @Irffan
 */
import beans.OrderBean;
import beans.ProductBean;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {
    private final String jdbcURL = "jdbc:derby://localhost:1527/iotbaydb";
    private final String dbUser = "app";
    private final String dbPassword = "app";
    
    public boolean saveOrder(OrderBean order) {
        boolean status = false;
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "INSERT INTO orders (user_id, order_date, status) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getUserID());
            ps.setInt(2, order.getOrderID());
            ps.setString(3, order.getOrderDate());
            ps.setString(4, order.getOrderStatus());

            int result = ps.executeUpdate();
            status = (result > 0);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}