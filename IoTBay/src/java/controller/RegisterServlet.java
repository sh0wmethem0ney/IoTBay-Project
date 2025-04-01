package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import model.CustomerBean;

public class RegisterServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //form data receiving
        String email = request.getParameter("email");
        String name = request.getParameter("customerName");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String phone = request.getParameter("phoneNumber");
        
        //instantiate a new javabean object
        CustomerBean customer = new CustomerBean();
        customer.setEmail(email);
        customer.setCustomerName(name);
        customer.setPassword(password);
        customer.setGender(gender);
        customer.setDateOfBirth(dateOfBirth);
        customer.setPhoneNumber(phone);
        
       //adding a new session and store info
        HttpSession session = request.getSession();
        session.setAttribute("user", customer);
        
        //redirect to main.jsp page -> all info need to be utilised here
        response.sendRedirect("main.jsp");
    }
}