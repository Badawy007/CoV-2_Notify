<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Login</title>
<link rel="stylesheet" href="style.css">

<body>
    <div class='bold-line'></div>
        <div class='container' >
            <div class='window'>
                <div class='overlay'></div>
                <div class='content'>
                    <div class='welcome'>Login</div>
                    <div class='input-fields'>
                        <form action="<%=request.getContextPath()%>/login" method="post">
                        <input type='text' placeholder='Username' name = 'username' class='input-line full-width'></input>
                        <input type='password' placeholder='Password' name = 'password' class='input-line full-width'></input>
                        <input type="checkbox" name = "remember" > Remember me </input>
                        <div class='spacinglogin'>Don't have an account ? <a href = 'signup.jsp' class='highlight'>Sign up !</a> <br>
                            Or return to<a href = 'index.jsp' class='spacinglogin'> Homepage</a>
                            <% String message  = (String) request.getAttribute("failmessage");
                            if (message != null) { %>
                            <h5><%= message %></h5>
                            <% } %> </div>
                        <input class='ghost-round full-width' type="submit" value="Login" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>