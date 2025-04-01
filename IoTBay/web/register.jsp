<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
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
                <form action="welcome.jsp" method="post">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required />
                    </div>

                    <div class="form-group">
                        <label for="customerName">Name:</label>
                        <input type="text" id="customerName" name="customerName" required />
                    </div>

                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required />
                    </div>

                    <div class="form-group">
                        <label>Gender:</label>
                        <input type="radio" id="male" name="gender" value="male" required />
                        <label for="male">Male</label>
                        <input type="radio" id="female" name="gender" value="female" required />
                        <label for="female">Female</label>
                    </div>

                    <div class="form-group">
                        <label for="dateOfBirth">Date of Birth:</label>
                        <input type="date" id="dateOfBirth" name="dateOfBirth" required />
                    </div>

                    <div class="form-group">
                        <label for="phoneNumber">Phone Number:</label>
                        <input type="tel" id="phoneNumber" name="phoneNumber" />
                    </div>

                    <div class="form-group">
                        <label for="tos">Agree to TOS:</label>
                        <input type="checkbox" id="tos" name="tos" />agree
                    </div>

                    <div class="form-group" style="text-align: center;">
                        
                        <a href="index.jsp" class="btn" style="font-family:Arial, sans-serif; width: 25%; padding: 10px; margin: 10px 0; background-color: #4CAF50; color: white; border: none; text-decoration: none;" >Back</a>
                        <button type="submit" class="btn">Register</button>
                    </div>
                </form>
            </div>
        </main>

        
    </body>
</html>
