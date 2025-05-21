/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import beans.UserBean;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ashwin
 */
@WebServlet(name = "manageDeactivatedUserServlet", urlPatterns = {"/manageDeactivatedUserServlet"})
public class manageDeactivatedUserServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
//            System.out.println("manageDeactivatedUserServlet doGet() called");

            UserDAO userDAO = new UserDAO();
            List<UserBean> DeactivatedList = userDAO.getAllDeactivatedUsers();

            request.setAttribute("DeactivatedUsers", DeactivatedList);
            request.getRequestDispatcher("deactivatedUsers.jsp").forward(request, response);
    }
}
