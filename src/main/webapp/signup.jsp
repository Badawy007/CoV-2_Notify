<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign up</title>
<link rel="stylesheet" href="style.css">
      
<body>
    <div class='bold-line'></div>
        <div class='container'>
            <div class='window'>
                <div class='overlay'></div>
                <div class='content'>
                    <div class='welcome'>Sign Up</div>
                    <div class='input-fields'>
                        <form action="<%=request.getContextPath()%>/register" method="post">
                        <input type='text' placeholder='Name' name = 'name' class='input-line full-width'>
                        <input type='text' placeholder='Username' name = 'username' class='input-line full-width'>
                        <input type='password' placeholder='Password' name = 'password' class='input-line full-width'>
                        <input type='password' placeholder='Confirm Password' name = 'confirm' class='input-line full-width'>
                        <input type = date name = 'date' class = 'input-line full-width'>
                        <div class='spacing'>Already have an account ? <a href = 'login.jsp' class='highlight'>Login !</a> <br>
                            Or return to <a href = 'index.jsp' class='spacing' >Homepage</a></div>
                            <% String message  = (String) request.getAttribute("error");
                                if (message != null) { %>
                            <h5><%= message %></h5>
                            <% } %>
                            <input class='ghost-round full-width' type="submit" value="Sign Up" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>