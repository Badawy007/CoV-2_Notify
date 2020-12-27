<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div align="center">
    <h2>Login</h2>
    <form action="<%=request.getContextPath()%>/login" method="post">

        <table style="with: 100%">
            <tr>
                <td>Username</td>
                <td><input type="text" name="username" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>

        </table><br>
        <input type="submit" value="Submit" /> <br><br>

        Don't have an account ? <a href="signup.jsp">Sign up!</a>

    </form>

</div>
</body>
</html>