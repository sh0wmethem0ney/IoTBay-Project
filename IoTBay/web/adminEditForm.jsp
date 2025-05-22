<%@page import="beans.UserBean"%>
<!-- send back to login if user = null -->
<%@page import="beans.UserBean"%>
<%@page import="beans.ProductBean"%>
<%@page import="dao.UserDAO"%>
<%@page import="java.util.List"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String userIdStr = request.getParameter("user_id");
    int userId = (userIdStr != null) ? Integer.parseInt(userIdStr) : -1;

    UserDAO userDAO = new UserDAO();
    UserBean user = userDAO.getUserById(userId); // Ensure this method exists

    if (user == null) {
%>
    <p>User not found.</p>
<%
    return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/IoTBayCss.css">
</head>

<body>

    <a href="index.jsp">
        <img src="images/IoTBay Logo.png" alt="IoTBay Logo" class="logo" style="width: 300px; height: auto; margin: 0 auto; display:block;">
    </a>

    <header>
        <h1>Edit User Account</h1>
    </header>

    <!-- error message -->
    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %>
    <%
        String role = request.getParameter("role");
    %>

    <main>
        <div class="container">
            <form action="AdminUpdateInfoServlet" method="post">
                <input type="hidden" name="action" value="saveEdit">
                <input type="hidden" name="user_id" value="<%= user.getUserID() %>" >
                <!--This is for the redirect back to the correct pages-->
                <input type="hidden" name="role" value="<%= role %>">


                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" value="<%= user.getEmail() %>" required />
                </div>

                <div class="form-group">
                    <label for="userName">Name:</label>
                    <input type="text" id="userName" name="userName" value="<%= user.getUserName() %>" disabled />
                </div>

                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" value="<%= user.getAddress() %>" required />
                </div>

                <div class="form-group" style="display: flex; align-items: center;">
                    <label style="width: 80px;">Gender:</label>
                    <div style="display: inline-block;">
                        <label style="display: flex; align-items: center;">
                            <input type="radio" name="gender" value="male" <%= "male".equals(user.getGender()) ? "checked" : "" %> disabled />
                            <span style="margin-left: 5px;">Male</span>
                            <input type="radio" name="gender" value="female" <%= "female".equals(user.getGender()) ? "checked" : "" %> disabled />
                            <span style="margin-left: 5px;">Female</span>
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="dateOfBirth">Date of Birth:</label>
                    <input type="date" id="dateOfBirth" name="dateOfBirth" value="<%= user.getDateOfBirth() %>" disabled />
                </div>

                <div class="form-group">
                    <label for="phone_number">Phone Number:</label>
                    <input type="text" id="phone_number" name="phone_number" value="<%= user.getPhoneNumber() %>" required />
                </div>




                <div class="form-group" style="text-align: center;">
                    <a href="AdminUpdateInfoServlet" class="btn">Cancel</a>
                    <button type="submit" class="btn">Save Changes</button>
                </div>
            </form>
        </div>
    </main>

</body>
</html>
