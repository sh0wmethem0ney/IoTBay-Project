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

        // instantiate an object and store data
        CustomerBean customer = new CustomerBean();
        customer.setEmail(email);
        customer.setCustomerName(name);
        customer.setPassword(password);
        customer.setGender(gender);
        customer.setDateOfBirth(dateOfBirth);
        customer.setPhoneNumber(phone);

        // storing in a session
        HttpSession session = request.getSession();
        session.setAttribute("user", customer);

        // redirect to main.jsp
        response.sendRedirect("welcome.jsp");
    }
}