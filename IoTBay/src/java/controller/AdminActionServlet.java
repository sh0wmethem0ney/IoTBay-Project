/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import beans.UserBean;
import dao.UserDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ashwin
 */
@WebServlet(name = "AdminActionServlet", urlPatterns = {"/AdminActionServlet"})
public class AdminActionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Default: just redirect to the manage page
        response.sendRedirect("manageCustomerServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdStr = request.getParameter("user_id");
        String newAction = request.getParameter("newRole");
//        System.out.println("the userID is " + userIdStr + " and the role is "+ newRole);

        if (userIdStr != null && newAction != null) {
            try {
                int userId = Integer.parseInt(userIdStr);

                switch (newAction) {
//                    promote to admin/staff/customer (just passes the variable along)
                    case "a": 
                    case "s":
                    case "c":
                        {
                            UserDAO userDAO = new UserDAO();
                            userDAO.updateUserRole(userId, newAction.charAt(0));
                            break;
                        }
//                    deactivate user
                    case "d":
                        {
                            UserDAO userDAO = new UserDAO();
                            userDAO.deactivateUser(userId);
                            System.out.println("Deactivated user " + userId);
                            break;
                        }
//                    reactivate user
                    case "r":
                        {
                            UserDAO userDAO = new UserDAO();
                            userDAO.reactivateUser(userId);
                            System.out.println("Reactivated user " + userId);
                            break;
                        }
//                    edit user (passes variable for where it came from)
                    case "e":
                            {
                               String role = request.getParameter("currentRole");
                               response.sendRedirect("adminEditForm.jsp?user_id=" + userId + "&role=" + role);
                               return;
                            }
//                    delete user
                    case "x":
                        {
                            UserDAO userDAO = new UserDAO();
                            userDAO.adminDeleteUser(userId);
                            System.out.println("Deleted user " + userId);
                            break;
                        }
                    default:
                        break;
                }

            } catch (NumberFormatException e) {
            }
        }

        // dodgy but genius code so i only have to use 1 action servlet and can redirect appropriately
        String redirectTo = request.getParameter("redirectTo");
        if (redirectTo != null && !redirectTo.isEmpty()) {
            response.sendRedirect(redirectTo);
        } else {
            response.sendRedirect("manageStaffServlet");
        }

            }
}

