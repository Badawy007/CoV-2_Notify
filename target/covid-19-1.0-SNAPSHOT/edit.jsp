<%@ page import="com.covid19.DatabaseSetup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% DatabaseSetup dbSetup = new DatabaseSetup();
    String username = (String) session.getAttribute("username");
%>
<html>
<head>
    <meta charset = "UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="styleindex.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="header">
    <div class="menu-bar">
        <nav class="navbar navbar-expand-lg navbar-light">
            <a class="navbar-brand" href="index.jsp"><img src="resources/logo.png" alt="Home"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="profile.jsp">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="notifications.jsp">Notifications</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="requests.jsp">Requests</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/logout">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>

<form action="<%=request.getContextPath()%>/editName" method="post" >
    <input type="text" placeholder="Enter new Name" name="newname">
    <input type="text" placeholder="Confirm new Name" name="cnewname" >
    <input type="submit" class="btn btn-primary" value="Change Name"/>
</form>

<form action="<%=request.getContextPath()%>editUsername" method="post" >
    <input type="text" name="newuser">
    <input type="submit" class="btn btn-primary" value="Change Username"/>
</form>

<form action="<%=request.getContextPath()%>/Edit/editPassword" method="post" >
    <input type="password" placeholder="Enter new Password" name="newpass">
    <input type="password" placeholder="Confirm new Password" name="cnewpass" >
    <input type="submit" class="btn btn-primary" value="Change Password"/>
</form>

<form action="<%=request.getContextPath()%>/Edit/editBirthday" method="post" >
    <input type="date" name="newdate">
    <input type="submit" class="btn btn-primary" value="Change Birthdate"/>
</form>

</body>
</html>