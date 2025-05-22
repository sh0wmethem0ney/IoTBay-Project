<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login - IoTBay</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/IoTBayCss.css">
    </head>
    <body>
        <a href='index.jsp'>
            <img src="images/IoTBay Logo.png" class="logo" style="width: 300px; height: auto; margin: 0 auto; display:block;">
        </a>

        <header>
            <h1>Sign In</h1>
        </header>

        <!-- error mesg when fail-->
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
            <% if (errorMessage != null) { %>
                <div class="error-message" style="color: red; text-align: center; margin-bottom: 20px;">
            <%= errorMessage %>
            </div>
        <% } %>
        
        <main>
            <br>
            <br>
            <br>

            <div class="container">
                <form action="LoginServlet" method="post">
                    <div class="form-group">
                        <label for="userid">Email:</label>
                        <input type="email" id="userid" name="id" required />
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required />
                    </div>
                    <div class="form-group" style="text-align: center;">
                        <a href="index.jsp" class="btn">Back</a>
                        <button class="btn" type="submit">Sign In</button>
                    </div>
                </form>
            </div>
        </main>

        <br>
        <br>
        <br>

        <footer>
            <p>&copy; 2025 IoTBay Assignment1. All rights reserved.</p>
        </footer>
    </body>
</html>