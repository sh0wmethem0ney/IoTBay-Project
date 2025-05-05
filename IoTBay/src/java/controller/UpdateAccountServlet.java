package controller;

import beans.UserBean;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        UserBean user = (UserBean) session.getAttribute("user");

        //send back if user = null
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // update
        String newDob = request.getParameter("dateOfBirth");
        String newPhone = request.getParameter("phoneNumber");
        String newAddress = request.getParameter("address");
        String newPassword = request.getParameter("password");
        String newGender = request.getParameter("gender");

        // set bean with new info
        user.setDateOfBirth(newDob);
        user.setPhoneNumber(newPhone);
        user.setAddress(newAddress);
        user.setPassword(newPassword);
        user.setGender(newGender);

        // update -> DAO method 'updateUserInfo' call
        try {
            UserDAO dao = new UserDAO();
            dao.updateUserInfo(user);

            session.setAttribute("user", user);  // session updated
            response.sendRedirect("accountManagement.jsp?success=true");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error updating account: " + e.getMessage());
            request.getRequestDispatcher("accountManagement.jsp").forward(request, response);
        }
    }
}
