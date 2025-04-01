package controller;

import model.CustomerBean;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // user input handle
        String email = request.getParameter("email");
        String name = request.getParameter("customerName");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String phone = request.getParameter("phoneNumber");

        // instantiate object
        CustomerBean customer = new CustomerBean();
        customer.setEmail(email);
        customer.setCustomerName(name);
        customer.setPassword(password);
        customer.setGender(gender);
        customer.setDateOfBirth(dateOfBirth);
        customer.setPhoneNumber(phone);

        // store in temp.session
        HttpSession session = request.getSession();
        session.setAttribute("user", customer);

        // redirect to main
        response.sendRedirect("main.jsp");
    }
}