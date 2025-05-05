package controller;

import beans.UserBean;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            UserBean user = (UserBean) session.getAttribute("user");
            if (user != null) {
                UserDAO dao = new UserDAO();
                dao.deleteUser(user.getUserID());  // delete from DB!
                session.invalidate();              // session end
            }
        }

        response.sendRedirect("deletedUser.jsp");  // mesg after deleted
    }
}
