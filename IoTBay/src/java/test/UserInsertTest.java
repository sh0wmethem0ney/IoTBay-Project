package test;

import beans.UserBean;
import dao.UserDAO;

public class UserInsertTest {
    public static void main(String[] args) {
        // User info
        UserBean user = new UserBean();
        user.setUserName("Admin");
        user.setDateOfBirth("1995-12-20");
        user.setPhoneNumber("0411222333");
        user.setAddress("123 Test Street, Sydney");
        user.setPassword("admin");
        user.setEmail("admin@admin.com");
        user.setGender("female");
        user.setRole('a'); // 'c' = customer

        // db insert (DAO)
        UserDAO dao = new UserDAO();
        dao.insertUser(user);  // user id setting

        // output
        System.out.println("Inserted user ID: " + user.getUserID());
    }
}
