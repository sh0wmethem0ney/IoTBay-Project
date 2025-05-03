<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/IoTBayCss.css">
    </head>
     
    <body>
        
        <a href="index.jsp">
            <img src="images/IoTBay Logo.png" alt="IoTBay Logo" class="logo" style="width: 300px; height: atuo; margin: 0 auto; display:block;">
        </a>
        
        <header>
            <h1>Register</h1>
        </header>
       
        <main>
            <div class="container">
                <form action="RegisterServlet" method="post">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" id="email" name="email" required />
                    </div>

                    <div class="form-group">
                        <label for="userName">Name:</label>
                        <input type="text" id="userName" name="userName" required />
                    </div>

                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="text" id="password" name="password" required />
                    </div>
                    
                    <div class="form-group">
                        <label for="address">Address:</label>
                        <input type="text" id="address" name="address" required />
                    </div>

                    <div class="form-group" style="display: flex; align-itmes: center;">
                        <label style="width: 80px;">Gender:</label>
                        <div style="display: inline-block;">
                            <label style="display: flex; align-items: center;">
                                <input type="radio" name="gender" value="male" required />
                                <span style="margin-left: 5px;">Male</span>
                                <input type="radio" name="gender" value="female" required />
                                <span style="margin-left: 5px;">Female</span>
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="dateOfBirth">Date of Birth:</label>
                        <input type="date" id="dateOfBirth" name="dateOfBirth" required />
                    </div>

                    <div class="form-group">
                        <label for="phoneNumber">Phone Number:</label>
                        <input type="tel" id="phoneNumber" name="phoneNumber" required/>
                    </div>

                    <div class="form-group">
                        <label for="tos">ToS & EULA:
                            <input type="checkbox" id="tos" name="tos" required/>agree
                        </label>
                    </div>

                    <div class="form-group" style="text-align: center;">
                        
                        <a href="index.jsp" class="btn">Back</a>
                        <button type="submit" class="btn">Register</button>
                    </div>
                </form>
            </div>
        </main>

        
    </body>
</html>
