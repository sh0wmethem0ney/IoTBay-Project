package dao;

import beans.UserBean;
import java.sql.*;

public class UserDAO {

    private final String jdbcURL = "jdbc:derby://localhost:1527/iotbaydb";
    private final String dbUser = "app";  // -> User Name / Password are equal "app" 
    private final String dbPassword = "app";

    public void insertUser(UserBean user) {
        String sqlState = "INSERT INTO USERS (name, date_of_birth, phone_number, address, password, email, gender, role) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sqlState, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getCustomerName());
            stmt.setDate(2, Date.valueOf(user.getDateOfBirth()));
            stmt.setString(3, user.getPhoneNumber());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getGender());
            stmt.setString(8, String.valueOf(user.getRole()));

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newUserID = generatedKeys.getInt(1);
                        user.setUserID(newUserID);  // store generated ID
                    }
                }
                System.out.println("User registered with ID: " + user.getUserID());
            }

        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }
    
    public int getNextUserID() {
        int nextId = 1;
        String query = "SELECT MAX(user_id) FROM USERS";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nextId;
    }
}