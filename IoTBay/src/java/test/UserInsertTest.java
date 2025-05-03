package test;

import beans.UserBean;
import dao.UserDAO;

public class UserInsertTest {
    public static void main(String[] args) {
        // User info
        UserBean user = new UserBean();
        user.setUserName("Alice Test");
        user.setDateOfBirth("1995-12-20");
        user.setPhoneNumber("0411222333");
        user.setAddress("123 Test Street, Sydney");
        user.setPassword("test1234");
        user.setEmail("alice@test.com");
        user.setGender("female");
        user.setRole('c'); // 'c' = customer

        // db insert (DAO)
        UserDAO dao = new UserDAO();
        dao.insertUser(user);  // 자동으로 user_id까지 세팅됨

        // output
        System.out.println("Inserted user ID: " + user.getUserID());
    }
}
