package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import beans.UserBean;
import dao.UserDAO;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // user input reciving
        String email = request.getParameter("id");
        String password = request.getParameter("password");

        // Login will not be procedd when there is an empty fields
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {

//           // dummy created -> must be figured on R1 release
//           UserBean customer = new UserBean();
//           customer.setEmail(email);
//           customer.setPassword(password);
//           customer.setUserName("Guest User"); // dummy data is here 
//           HttpSession session = request.getSession();
//           session.setAttribute("user", customer);
//           // redirect to main.jsp
//           response.sendRedirect("main.jsp");

            UserDAO dao = new UserDAO();
            UserBean user = dao.findUserToLogIn(email, password);
            
            if(user != null){
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                //confirm user type if (c/a/s) -> whoever works on Admin and Staff pages, redirect pages from here
                switch(user.getRole()){
                    case 'c': // -> if user is Customer <-
                        response.sendRedirect("main.jsp");
                        break;
                    
                    case 'a': // -> if user is Admin <-
//                        response.sendRedirect("???");
                        break;
                        
                    case 's': // -> if user is Staff <-
//                        response.sendRedirect("???");
                        break;
                        
                    default: // -> if none
                        request.setAttribute("errorMessage", "User role is not matched");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        break;
                }
            } else{
                request.setAttribute("errorMessage", "Invalid email or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
              
        } else {
            // if fail, back to login.jsp
            request.setAttribute("errorMessage", "Email and password must not be empty.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}