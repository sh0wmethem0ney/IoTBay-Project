package dao;

import beans.UserBean;
import java.sql.*;

public class UserDAO {

    private final String jdbcURL = "jdbc:derby://localhost:1527/iotbaydb";
    private final String dbUser = "app";  // -> User Name / Password are equal "app" 
    private final String dbPassword = "app";

    //user insert is done here
    public void insertUser(UserBean user) {
        String sqlState = "INSERT INTO USERS (name, date_of_birth, phone_number, address, password, email, gender, role) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sqlState, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getUserName());
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
    
    //this method will bring a unique user ID
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
    
    //find user for login -> looking for email and password match
    public UserBean findUserToLogIn(String email, String password){
        String sqlState = "SELECT * FROM USERS WHERE email = ? AND password = ?";
        try (Connection con = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement stmt = con.prepareStatement(sqlState)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet re = stmt.executeQuery();
            
            if(re.next()){
                UserBean user = new UserBean();
                user.setUserID(re.getInt("user_id"));
                user.setUserName(re.getString("name"));
                user.setDateOfBirth(re.getString("date_of_birth"));
                user.setPhoneNumber(re.getString("phone_number"));
                user.setAddress(re.getString("address"));
                user.setPassword(re.getString("password"));
                user.setEmail(re.getString("email"));
                user.setGender(re.getString("gender"));
                user.setRole(re.getString("role").charAt(0));
                return user;
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    // User update method
    public void updateUserInfo(UserBean user) {
        String sql = "UPDATE USERS SET date_of_birth = ?, phone_number = ?, address = ?, password = ?, gender = ? WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(user.getDateOfBirth()));
            stmt.setString(2, user.getPhoneNumber());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getGender());
            stmt.setInt(6, user.getUserID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update user info", e);
        }
    }
    
    public void deleteUser(int userID) {
        String sql = "DELETE FROM USERS WHERE user_id = ?";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            stmt.executeUpdate();
            System.out.println("User deleted: " + userID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //check if email already exist
    public UserBean findUserByEmail(String email) {
    String sql = "SELECT * FROM USERS WHERE email = ?";
    
    try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            UserBean user = new UserBean();
            user.setUserID(rs.getInt("user_id"));
            user.setUserName(rs.getString("name"));
            user.setDateOfBirth(rs.getString("date_of_birth"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setAddress(rs.getString("address"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setRole(rs.getString("role").charAt(0));
            return user;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null; // if there is no email exist in DB
}

}
