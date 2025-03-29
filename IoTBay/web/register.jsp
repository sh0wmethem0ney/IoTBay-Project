<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    
    
    
    <body>
        <h2>Register</h2>
       
        <form action="welcome.jsp" method="post">
            <table>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="email" id="email" name="email" required></td>
                </tr>

                <tr>
                    <td><label for="name">Name:</label></td>
                    <td><input type="text" id="customerName" name="customerName" required></td>
                </tr>

                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" id="password" name="password" required></td>
                </tr>

                <tr>
                    <td><label>Gender:</label></td>
                    <td><input type="radio" id="male" name="gender" value="male" required>
                    <label for="male">Male</label>
                    <input type="radio" id="female" name="gender" value="female" required>
                    <label for="female">Female</label></td>
                </tr>

                <tr>
                    <td><label>Date of birth:</label></td>
                    <td><input type="date" id="dateOfBirth" name="dateOfBirth" required>
                </tr>
                
                <tr>
                    <td><label>Phone number:</label></td>
                    <td><input type="tel" id="phoneNumber" name="phoneNumber"></td>
                </tr>

                <tr>
                    <td><label for="tos">Agree to TOS:</label></td>
                    <td><input type="checkbox" id="tos" name="tos"></td>
                </tr>
                
                <tr>
                    <td><button type="submit">Register</button></td>
                </tr>
            </table>
        </form>
        
    </body>
</html>
