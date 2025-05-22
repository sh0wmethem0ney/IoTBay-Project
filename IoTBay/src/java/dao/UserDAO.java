package dao;

import beans.UserBean;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final String jdbcURL = "jdbc:derby://localhost:1527/iotbaydb";
    // ******************-> DB User Name / Password are equal "app"***********************
    private final String dbUser = "app";   
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
    
    //insert into LOGIN LOGS table
    public void insertLoginLog(int userId) {
    String sql = "INSERT INTO LOGIN_LOGS (user_id, login_time) VALUES (?, CURRENT_TIMESTAMP)";
    try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, userId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    // tracking of login logs
    public List<Timestamp> getLoginLogs(int userId) {
        List<Timestamp> logs = new ArrayList<>();
        String sql = "SELECT login_time FROM LOGIN_LOGS WHERE user_id = ? ORDER BY login_time DESC";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                logs.add(rs.getTimestamp("login_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }
    
    
    //Admin Dashboard Functionality
    
        public void adminUpdateUser(UserBean user) {
        String sql = "UPDATE USERS SET email = ?, phone_number = ?, address = ?  WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPhoneNumber());
            stmt.setString(3, user.getAddress());
            stmt.setInt(4, user.getUserID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update user info", e);
        }
    }
        
        public void adminDeleteUser(int userID) {
        String sql = "DELETE FROM DeactivatedUsers WHERE user_id = ?";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    public UserBean getUserById(int userId) {
        UserBean user = null;

        String sql = "SELECT * FROM Users WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new UserBean();
                    user.setUserID(rs.getInt("user_id"));
                    user.setEmail(rs.getString("email"));
                    user.setUserName(rs.getString("name"));
                    user.setAddress(rs.getString("address"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setGender(rs.getString("gender"));
                    user.setDateOfBirth(rs.getString("date_of_birth"));
                    // Add any other fields your UserBean has
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    
    
    public int getTotalUsers(){
        String sql = "SELECT COUNT(*) FROM USERS";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getTotalCustomers(){
//      //Get from the usertype column if they are c (customer), e (employee), a(admin)
        String sql = "SELECT COUNT(*) FROM USERS WHERE role = 'c'";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getTotalEmployees(){
        String sql = "SELECT COUNT(*) FROM USERS WHERE role = 's'";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;  
    }
    
    public int getTotalDeactivatedUsers(){
        String sql = "SELECT COUNT(*) FROM DeactivatedUsers";
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<UserBean> getAllCustomers() {
        List<UserBean> customerList = new ArrayList<>();
        String sql = "SELECT user_id, name, date_of_birth, email, phone_number, gender, role FROM USERS WHERE role = 'c'";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserBean user = new UserBean();
                Date dob = rs.getDate("date_of_birth");
                String dobString = null;
                if (dob != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // or your preferred format
                        dobString = formatter.format(dob);
                }
                user.setDateOfBirth(dobString);
                user.setUserID(rs.getInt("user_id"));
                user.setUserName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role").charAt(0));
                
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setGender(rs.getString("gender"));
                customerList.add(user);
                
            }
//            for (UserBean u : customerList) {
//                System.out.println("Current Customer: " + u.getUserName());
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    
    }
    
       public List<UserBean> getAllStaff() {
            List<UserBean> StaffList = new ArrayList<>();
            String sql = "SELECT user_id, name, date_of_birth, email, phone_number, gender, role FROM USERS WHERE role = 's'";

            try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    UserBean user = new UserBean();
                    Date dob = rs.getDate("date_of_birth");
                    String dobString = null;
                    if (dob != null) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // or your preferred format
                        dobString = formatter.format(dob);
                    }
                    user.setDateOfBirth(dobString);
                    user.setUserID(rs.getInt("user_id"));
                    user.setUserName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role").charAt(0));
                    
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setGender(rs.getString("gender"));
                    StaffList.add(user);

                }
//                for (UserBean u : StaffList) {
//                    System.out.println("Current Customer: " + u.getUserName());
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return StaffList;
        }
       
       
        public List<UserBean> getAllDeactivatedUsers() {
        List<UserBean> DeactivatedList = new ArrayList<>();
        String sql = "SELECT user_id, name, date_of_birth, email, phone_number, gender, role, dateDeactivated FROM DeactivatedUsers";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserBean user = new UserBean();
                
                Date DeactivatedDate = rs.getDate("dateDeactivated");
                    String DDString = null;
                    if (DeactivatedDate != null) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // or your preferred format
                        DDString = formatter.format(DeactivatedDate);
                    }
                Date dob = rs.getDate("date_of_birth");
                    String dobString = null;
                    if (dob != null) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // or your preferred format
                        dobString = formatter.format(dob);
                    }
                user.setDateOfBirth(dobString);
                user.setDateOfBirth(dobString); 
                user.setDateDeactivated(DDString);   
                user.setUserID(rs.getInt("user_id"));
                user.setUserName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role").charAt(0));
                
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setGender(rs.getString("gender"));
                
                
                
                DeactivatedList.add(user);
                
            }
//            for (UserBean u : DeactivatedList) {
////                System.out.println("Current Customer: " + u.getUserName());
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DeactivatedList;
    
    }
       
//    admin level changes
    public void updateUserRole(int userID, char newRole) {
        String sql = "UPDATE USERS SET role = ? WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, String.valueOf(newRole));
            stmt.setInt(2, userID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
//    moves user to deactivate table with timestamp
    public void deactivateUser(int userId) {
    String selectSql = "SELECT user_id, name, date_of_birth, phone_number, address, password, email, gender, role FROM Users WHERE user_id = ?";
    String insertSql = "INSERT INTO DeactivatedUsers (name, date_of_birth, phone_number, address, password, email, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

//    String insertSql = "INSERT INTO DeactivatedUsers (user_id, name, date_of_birth, phone_number, address, password, email, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String deleteSql = "DELETE FROM Users WHERE user_id = ?";
//    System.out.println("Deactivate function ran with userID " + userId);
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            conn.setAutoCommit(false);

            // gets the record
            try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        try (PreparedStatement ins = conn.prepareStatement(insertSql)) {
//                            inserts record into deactivated table
                            ins.setString(1, rs.getString("name"));
                            // Correct DATE type
                            Date dob = rs.getDate("date_of_birth");
                            ins.setDate(2, dob);                                 

                            ins.setString(3, rs.getString("phone_number")); 
                            ins.setString(4, rs.getString("address"));
                            ins.setString(5, rs.getString("password"));
                            ins.setString(6, rs.getString("email"));
                            ins.setString(7, rs.getString("gender"));
                            ins.setString(8, rs.getString("role"));  
                            ins.executeUpdate();
                        }
                    }
                }
            }

            // deletes the record from users table
            try (PreparedStatement del = conn.prepareStatement(deleteSql)) {
                del.setInt(1, userId);
                del.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
//    does the opposite of deactivate
    public void reactivateUser(int userId) {
        String selectSql = "SELECT user_id, name, date_of_birth, phone_number, address, password, email, gender, role FROM DeactivatedUsers WHERE user_id = ?";
        String insertSql = "INSERT INTO USERS (name, date_of_birth, phone_number, address, password, email, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    //    String insertSql = "INSERT INTO DeactivatedUsers (user_id, name, date_of_birth, phone_number, address, password, email, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String deleteSql = "DELETE FROM DeactivatedUsers WHERE user_id = ?";
//        System.out.println("Deactivate function ran with userID " + userId);
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            conn.setAutoCommit(false);

            // gets the record
            try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        try (PreparedStatement ins = conn.prepareStatement(insertSql)) {
    //                      inserts record into deactivated table
                            ins.setString(1, rs.getString("name"));
                            // Correct DATE type
                            Date dob = rs.getDate("date_of_birth");
                            ins.setDate(2, dob);                                 
                                
                            ins.setString(3, rs.getString("phone_number")); 
                            ins.setString(4, rs.getString("address"));
                            ins.setString(5, rs.getString("password"));
                            ins.setString(6, rs.getString("email"));
                            ins.setString(7, rs.getString("gender"));
                            ins.setString(8, rs.getString("role"));  
                            ins.executeUpdate();
                            }
                        }
                    }
                }

                // deletes the record from users table
                try (PreparedStatement del = conn.prepareStatement(deleteSql)) {
                    del.setInt(1, userId);
                    del.executeUpdate();
                }

                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    

}
