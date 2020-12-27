<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<div align="center">
    <h2>Sign Up</h2>
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
            <tr>
                <td>Confirm Password</td>
                <td><input type="password" name="password-verify" /></td>
            </tr>

        </table><br>
        <input type="submit" value="Submit" /> <br><br>

            Already have an account ? <a href="login.jsp">Login!</a>

    </form>

</div>
</body>
</html>
