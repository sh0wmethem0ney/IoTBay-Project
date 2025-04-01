<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBayCss.css">
        <title>Welcome</title>
    </head>
    
    <body>
        <h1>Welcome to IoTBay, <%= request.getParameter("name")%>!</h1>
        
        <p>
            Good to have you on board!<br><br>
            <button onclick="window.location.href='main.jsp'" >Go to Main Page</button>
        </p> 
        
    </body>
</html>