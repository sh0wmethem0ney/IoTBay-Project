<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - IoTBay</title>
    <link rel="stylesheet" href="css/footerCss.css">
</head>
<body>
    <header>
        <h1>Login</h1>
    </header>
    <main>
        <form action="LoginServlet" method="post">
            <div>
                <label for="id">ID:</label>
                <input type="text" id="userid" name="id" required />
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required />
            </div>
            <div>
                <input type="submit" value="Login" />
            </div>
        </form>
    </main>
    <footer>
        <p>&copy; 2025 IoTBay Assignment1. All rights reserved.</p>
    </footer>
</body>
</html>