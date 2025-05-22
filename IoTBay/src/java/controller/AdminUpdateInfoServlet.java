package controller;

import beans.UserBean;
import dao.UserDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminUpdateInfoServlet", urlPatterns = {"/AdminUpdateInfoServlet"})
public class AdminUpdateInfoServlet extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        try {
            // Get values from the form
            int userId = Integer.parseInt(request.getParameter("user_id"));
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phone_number");
            System.out.println("AdminUpdateInfoServlet updated "+ userId);
//            System.out.println("Updating user:");
//            System.out.println("userID: " + userId);
//            System.out.println("phone: " + phoneNumber);
//            System.out.println("address: " + address);
//            System.out.println("email: " + email);

            UserBean user = new UserBean();
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);
            user.setUserID(userId);
            

            UserDAO dao = new UserDAO();
            dao.adminUpdateUser(user);
            request.setCharacterEncoding("UTF-8");

            String role = request.getParameter("role");
//            System.out.println("this is the role" + role);

            switch (role) {
                    case "s":
                    {
                        response.sendRedirect("manageStaffServlet");
                    }
                    case "c":
                        {
                            response.sendRedirect("manageCustomerServlet");
                        }
                    default:
                        response.sendRedirect("adminDashboardServlet");
                }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Redirect to a safe page, for example the manage page or a form page
        response.sendRedirect("adminDashboardServlet");
}
}
