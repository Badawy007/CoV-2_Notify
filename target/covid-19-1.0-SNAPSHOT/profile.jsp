<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>CoV-2 Notify</title>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<body>
<nav class="navbar fixed-top navbar-expand-md">
    <a class="navbar-brand ml-5" href="index.jsp"><span id="blue">CoV-2</span> NOTIFY</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <div id="wrapper">
            <div class="icon">
                <span class="line top"></span>
                <span class="line middle"></span>
                <span class="line bottom"></span>
            </div>
        </div>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-5 w-100 justify-content-end">
            <li class="nav-item">
                <a class="nav-link" href="news.jsp">NEWS</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="login.jsp">LOGIN</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="signup.jsp">SIGN UP</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="profile.jsp">PROFILE</a>
            </li>
        </ul>
    </div>
</nav>
</body>
<div id="conteneur">
<% String name = (String) session.getAttribute("username");%>
    <h1> <font color="black" size="30">Welcome <%= name %> </font></h1>
<form action="<%=request.getContextPath()%>/profile" method="post">
    <p> <font color="black" size="5px"> Add user</font> </p>
    <input class="w3-input w3-animate-input" type='text' style="width: 30%" placeholder='Username' name = 'username' class='input-line full-width'>
    <input type="submit" value="Add" />
    <p> <font color="black" size="5px"> Delete user</font> </p>
    <input class="w3-input w3-animate-input" style="width: 30%" type='text' placeholder='Username' name = 'username-delete' class='input-line full-width'>
    <input type="submit" value="Delete"/> <br><br>
    <a href="login.jsp"/>Logout</a>
</form>
</div>

