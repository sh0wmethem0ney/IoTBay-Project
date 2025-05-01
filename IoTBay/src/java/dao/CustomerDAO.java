package dao;

import beans.CustomerBean;
import java.sql.*;

public class CustomerDAO {

    private final String jdbcURL = "jdbc:derby://localhost:1527/iotbaydb";
    private final String dbUser = "app";  // -> User Name / Password are equal "app" 
    private final String dbPassword = "app";

    public void insertCustomer(CustomerBean customer) {
        String sqlState = "INSERT INTO Customers (customer_id, customer_name, date_of_birth, phone_number, address, password, email, gender) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sqlState)) {

            stmt.setInt(1, Integer.parseInt(customer.getCustomerID()));
            stmt.setString(2, customer.getCustomerName());
            stmt.setDate(3, Date.valueOf(customer.getDateOfBirth()));
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getPassword());
            stmt.setString(7, customer.getEmail());
            stmt.setString(8, customer.getGender());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Customer has successfully registred!");
            }

        } catch (SQLException e) {
            System.out.println("Error occured" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public String getNextCustomerID() {
        String sql = "SELECT MAX(customer_id) FROM Customers";
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int nextId = 1;
            if (rs.next() && rs.getString(1) != null) {
                nextId = Integer.parseInt(rs.getString(1)) + 1;
            }
            return String.format("%06d", nextId);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}