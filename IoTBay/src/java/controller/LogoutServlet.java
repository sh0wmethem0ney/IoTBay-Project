package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // call the session and discard it
        HttpSession session = request.getSession(false); // false: if no session, do not create a new one
        if (session != null) {
            session.invalidate(); // terminate the session
        }

        // redirect to logout.jsp
        response.sendRedirect("logout.jsp");
    }
}