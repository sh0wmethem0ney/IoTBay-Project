package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import beans.CustomerBean;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // user input reciving
        String email = request.getParameter("id");
        String password = request.getParameter("password");

        // Login won't be failed for R0 release
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {

            // dummy created -> must be figured on R1 release
            CustomerBean customer = new CustomerBean();
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setCustomerName("Guest User"); // dummy data is here 

            HttpSession session = request.getSession();
            session.setAttribute("user", customer);

            // redirect to main.jsp
            response.sendRedirect("main.jsp");
        } else {
            // if fail, back to login.jsp
            response.sendRedirect("login.jsp");
        }
    }
}