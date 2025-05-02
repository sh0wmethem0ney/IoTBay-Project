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
import beans.CustomerBean;
import dao.CustomerDAO;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // receiving data from register.jsp
        String email = request.getParameter("email");
        String name = request.getParameter("customerName");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String phone = request.getParameter("phoneNumber");

        //check if there is null value passed from View
        if (email == null || email.trim().isEmpty() || name == null || name.trim().isEmpty() || password == null || password.trim().isEmpty() ||
        gender == null || gender.trim().isEmpty() || dateOfBirth == null || dateOfBirth.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All fields must be filled.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        
        // instantiate an object and store data
        CustomerBean customer = new CustomerBean();
        customer.setEmail(email);
        customer.setCustomerName(name);
        customer.setPassword(password);
        customer.setGender(gender);
        customer.setDateOfBirth(dateOfBirth);
        customer.setPhoneNumber(phone);

        try {
            // ID setting from DAO
            CustomerDAO dao = new CustomerDAO();
            String newId = dao.getNextCustomerID();
            customer.setCustomerID(newId);

            // Insert it into DB
            dao.insertCustomer(customer);

            // storing in a session
            HttpSession session = request.getSession();
            session.setAttribute("user", customer);

            // redirect to main.jsp
            response.sendRedirect("welcome.jsp");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error occured: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}