/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import beans.UserBean;
import dao.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ashwin
 */
@WebServlet(name = "manageStaffServlet", urlPatterns = {"/manageStaffServlet"})
public class manageStaffServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println("manageStaffServlet doGet() called");

        UserDAO userDAO = new UserDAO();
        List<UserBean> staffList = userDAO.getAllStaff();

        request.setAttribute("Employees", staffList);
        request.getRequestDispatcher("manageStaff.jsp").forward(request, response);
    }
    
}
