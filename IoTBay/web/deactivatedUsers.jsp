<%-- 
    Document   : deactivatedUsers
    Created on : 20 May 2025, 8:09:37 pm
    Author     : Ashwin
--%>
<%@page import="beans.UserBean"%>
<%@page import="beans.UserBean"%>
<%@page import="beans.ProductBean"%>
<%@page import="dao.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Manage Customers</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/IoTBayCss.css">
        <script>
            let selectedUserId = null;
            function filterTable() {
                const input = document.getElementById("searchInput");
                const filter = input.value.toLowerCase();
                const table = document.getElementById("customerTable");
                const trs = table.getElementsByTagName("tr");

                for (let i = 1; i < trs.length; i++) {
                    const tds = trs[i].getElementsByTagName("td");
                    let rowText = "";
                    for (let j = 0; j < tds.length; j++) {
                        rowText += tds[j].textContent.toLowerCase() + " ";
                    }
                    trs[i].style.display = rowText.indexOf(filter) > -1 ? "" : "none";
                }
                clearSelection();
            }
        </script>
        <script>
            function openRoleModal(userId) {
                document.getElementById("modalUserId").value = userId;
                document.getElementById("roleModal").style.display = "block";
            }

            function closeRoleModal() {
                document.getElementById("roleModal").style.display = "none";
            }

            // Optional: close modal if user clicks outside it
            window.onclick = function(event) {
                let modal = document.getElementById("roleModal");
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            }
    </script>
    </head>
    <body>
        <a href="index.jsp">
            <img src="images/IoTBay Logo.png" alt="IoTBay Logo" class="logo" style="width: 300px; height: auto; margin: 0 auto; display:block;">
        </a>
        
        <header class="page-header">
            <div>
                <h1>Deactivated Accounts Page</h1>
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
        <input type="text" id="searchInput" placeholder="Search Records by Properties.." onkeyup="filterTable()" />
        <table id="customerTable" class="table-standard">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>DoB</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Gender</th>
                    <th>Role</th>
                    <th>Deactivation Date</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${DeactivatedUsers}">
                    <tr>
                        <td>${user.userID}</td>
                        <td>${user.userName}</td>
                        <td>${user.dateOfBirth}</td>
                        <td>${user.email}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.gender}</td>
                        <td>${user.role}</td>
                        <td>${user.dateDeactivated}</td>
                        <td>
                            <button type="button" class="btn" onclick="openRoleModal(${user.userID})">Manage</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="roleModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeRoleModal()">&times;</span>
                <h3>Change User Role</h3>
                <form id="changeRoleForm" method="post" action="AdminActionServlet">
                <input type="hidden" name="action" value="changeRole">
                <input type="hidden" name="user_id" id="modalUserId">
                <input type="hidden" name="redirectTo" value="manageDeactivatedUserServlet" />

                <label><input type="radio" name="newRole" value="x"> Delete</label><br>
                <label><input type="radio" name="newRole" value="r"> Reactivate</label><br>
                <button type="submit" class="btn">Confirm</button>
              </form>
            </div>
        </div>
     
    </body>

</html>