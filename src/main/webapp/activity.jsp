<%@ page import="com.covid19.DatabaseSetup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  DatabaseSetup dbSetup = new DatabaseSetup();       %>
<html>
<head>
    <meta charset = "UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Declare Location</title>
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

<form action="<%=request.getContextPath()%>/activity" method="post">
    <div class="form-group">
        Activity
        <input type="text" placeholder='Location name' name = 'activity' class="form-control">
        Start time
        <input type="datetime-local" name="starttime" class="form-control" >
        End time
        <input type="datetime-local" name="endtime" class="form-control" >

        Choose location
        <select name="location">
            <% for (String location : dbSetup.getLocations()) {%>
            <option value="<%=location%>"><%=location%></option>
            <%}%>
            <option value="Other">Other</option>
        </select>
        <br><br>

        If your Activity is not in the List, Create a new one! <br><br>

        Location
        <input type="text" name="denomination" placeholder='Denomination' class="form-control" >
        Address
        <input type="text" name="address" placeholder='Address' class="form-control" >
        Coordinates
        <input type="text" name="coordx" placeholder='x coordinates' class="form-control" >
        <br>
        <input type="text" name="coordy" placeholder='Y coordinates' class="form-control" >

        <br>
        <input type="submit" class="btn btn-primary" value="Add" />

    </div>
</form>
</body>
</html>
