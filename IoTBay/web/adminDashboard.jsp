<%-- 
    Document   : adminView
    Created on : 7 May 2025, 9:22:08 pm
    Author     : Ashwin
--%>
<%@page import="beans.UserBean"%>
<%@page import="beans.ProductBean"%>
<%@page import="dao.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserBean user = (UserBean) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/IoTBayCss.css">
    </head>
    <body>
        <a href="index.jsp">
            <img src="images/IoTBay Logo.png" alt="IoTBay Logo" class="logo" style="width: 300px; height: auto; margin: 0 auto; display:block;">
        </a>
        
        <header class="page-header">
            <div>
                <h1>Welcome, <%= user.getUserName() %>!</h1>
            </div>
            <div class="header-links">
                <a href="adminDashboardServlet" class="btn">Admin Dashboard</a>
                <a href="manageCustomerServlet" class="btn">Manage Customers</a>
                <a href="manageStaffServlet" class="btn">Manage Staff</a>
                <a href="manageDeactivatedUserServlet" class="btn">Deactivated Users</a>
                <a href="logout" class="btn">Sign Out</a>
            </div>
        </header>
    
    <body>
        <h1>Admin Dashboard</h1>
        
        <div style="text-align: left;">
            <h2>Current Active Users: ${dashboard.totalUsers}</h2>
            <h2>Total Customers Accounts: ${dashboard.totalCustomers}</h2>
            <h2>Total Employee Accounts: ${dashboard.totalEmployees}</h2>
            <h2>Currently Deactivated Accounts: ${dashboard.totalDeactivatedUsers}</h2>
        </div>

        
    </body>
</html>