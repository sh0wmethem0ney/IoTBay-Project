/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.UserBean;
import dao.UserDAO;

//@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
//public class RegisterServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // receiving data from register.jsp
//        String email = request.getParameter("email");
//        String name = request.getParameter("customerName");
//        String password = request.getParameter("password");
//        String gender = request.getParameter("gender");
//        String dateOfBirth = request.getParameter("dateOfBirth");
//        String phone = request.getParameter("phoneNumber");
//        String address = request.getParameter("address");
//
//        //check if there is null value passed from View
//        if (email == null || email.trim().isEmpty() || name == null || name.trim().isEmpty() || password == null || password.trim().isEmpty() ||
//        gender == null || gender.trim().isEmpty() || dateOfBirth == null || dateOfBirth.trim().isEmpty() || phone == null || phone.trim().isEmpty() || 
//                address == null || address.trim().isEmpty()) {
//            request.setAttribute("errorMessage", "All fields must be filled.");
//            request.getRequestDispatcher("register.jsp").forward(request, response);
//            return;
//        }
//        
//        
//        // instantiate an object and store data
//        UserBean customer = new UserBean();
//        customer.setEmail(email);
//        customer.setCustomerName(name);
//        customer.setPassword(password);
//        customer.setGender(gender);
//        customer.setDateOfBirth(dateOfBirth);
//        customer.setPhoneNumber(phone);
//
//        try {
//            // ID setting from DAO
//            UserDAO dao = new UserDAO();
//            int newId = dao.getNextUserID();
//            customer.setUserID(newId);
//
//            // Insert it into DB
//            dao.insertCustomer(customer);
//
//            // storing in a session
//            HttpSession session = request.getSession();
//            session.setAttribute("user", customer);
//
//            // redirect to main.jsp
//            response.sendRedirect("welcome.jsp");
//
//        } catch (Exception e) {
//            request.setAttribute("errorMessage", "Error occured: " + e.getMessage());
//            request.getRequestDispatcher("register.jsp").forward(request, response);
//        }
//    }
//}
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // receiving data from register.jsp
        String email = request.getParameter("email");
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String phone = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        //register will not be done if there is an empty field
        if (email == null || email.trim().isEmpty() || 
            name == null || name.trim().isEmpty() || 
            password == null || password.trim().isEmpty() ||
            gender == null || gender.trim().isEmpty() || 
            dateOfBirth == null || dateOfBirth.trim().isEmpty() || 
            phone == null || phone.trim().isEmpty() || 
            address == null || address.trim().isEmpty()) {

            //error case handle
            request.setAttribute("errorMessage", "All fields must be filled.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // instantiate a user object
        UserBean user = new UserBean();
        user.setEmail(email);
        user.setUserName(name);
        user.setPassword(password);
        user.setGender(gender);
        user.setDateOfBirth(dateOfBirth);
        user.setPhoneNumber(phone);
        user.setAddress(address);
        user.setRole('c'); // 'c' for customer

        try {
            //call DAO class
            UserDAO dao = new UserDAO();
            int newId = dao.getNextUserID();
            user.setUserID(newId);

            //call insert method
            dao.insertUser(user);

            //setting session attribute
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect("welcome.jsp");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error occurred: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}