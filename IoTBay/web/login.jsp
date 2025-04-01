<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - IoTBay</title>
    <link rel="stylesheet" href="css/IoTBayCss.css">
</head>
<body>
    <a href='index.jsp'>
        <img src="images/IoTBay Logo.png" class="logo" style="width: 300px; height: atuo; margin: 0 auto; display:block;">
    </a>

    <header>
        <h1>Sign In</h1>
    </header>
    
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
                    <a href="index.jsp" class="btn-link">Back</a>
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