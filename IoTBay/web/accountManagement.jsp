<%@page import="beans.UserBean"%>
<!-- send back to login if user = null -->
<%
    UserBean user = (UserBean) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>

<html>
    
    
<head>
    <title>Account Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/IoTBayCss.css">
</head>


<body>
    <header>
        <h1>Manage Your Account</h1>
    </header>

    <!-- return to main.jsp if success -->
    <%
    String success = request.getParameter("success");
    if ("true".equals(success)) {
    %>
    <script>
        <!-- change notification mesg -->
        alert("Information changed.");
        window.location.href = "main.jsp";
    </script>
    <%
    }
    %>

    <!-- info update container -->
    <main>
        <div class="container">
            <form action="UpdateAccountServlet" method="post">
                <p><strong>User ID:</strong> <%= user.getUserID() %></p>
                <p><strong>Name:</strong> <%= user.getUserName() %></p>
                <p><strong>Email:</strong> <%= user.getEmail() %></p>

                <label for="dateOfBirth">Date of Birth:</label>
                <input type="date" id="dateOfBirth" name="dateOfBirth" value="<%= user.getDateOfBirth() %>" required /><br><br>

                <label for="phoneNumber">Phone Number:</label>
                <input type="tel" id="phoneNumber" name="phoneNumber" value="<%= user.getPhoneNumber() %>" required /><br><br>

                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="<%= user.getAddress() %>" required /><br><br>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="<%= user.getPassword() %>" required /><br><br>

                <label>Gender:</label>
                <input type="radio" name="gender" value="male" <%= user.getGender().equals("male") ? "checked" : "" %> required /> Male
                <input type="radio" name="gender" value="female" <%= user.getGender().equals("female") ? "checked" : "" %> required /> Female<br><br>

                <button type="submit" class="btn">Update Info</button>
                <a href="main.jsp" class="btn">Cancel</a>
            </form>
                
            <!-- this redirects to deleteUser.jsp -->
            <form action="DeleteAccountServlet" method="post" onsubmit="return confirm('Are you sure deleting your account? Irrversiable');">
                <button type="submit" class="btn" style="background-color: red;">Delete my account</button>
            </form>
        </div>             
    </main>
                
</body>

</html>