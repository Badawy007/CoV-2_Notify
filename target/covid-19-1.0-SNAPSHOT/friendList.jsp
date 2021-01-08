<%@ page import="com.covid19.DatabaseSetup" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset = "UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Friend List</title>
    <link rel="stylesheet" href="styleindex.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script>src="https://code.jquery.com/jquery-3.2.1.slim.min.js"</script>
    <script>src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"</script>
</head>
<body>
<div class="header">
    <div class="menu-bar">
        <nav class="navbar navbar-expand-lg navbar-light">
            <a class="navbar-brand" href="index.jsp"><img src="resources/logo.png" alt=""></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
            </button>
            <% if (session == null || session.getAttribute("username") == null) { %>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="signup.jsp">Sign up</a>
                    </li>
                        <% }  else if(session.getAttribute("type").equals("user")) { %>
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
                                <% } else {%>
                            <div class="collapse navbar-collapse" id="navbarNav">
                                <ul class="navbar-nav ml-auto">
                                    <li class="nav-item">
                                        <a class="nav-link" href="index.jsp">Home</a>
                                    </li>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="notifications.jsp">Notifications</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="requests.jsp">Requests</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="adminPanel.jsp">Panel</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="<%=request.getContextPath()%>/logout">Logout</a>
                                    </li>
                                    <%}%>
                                </ul>
                            </div>
        </nav>
    </div>
</div>
<div class="container">
<%
    String current  = (String) session.getAttribute("username");
    DatabaseSetup dbSetup = new DatabaseSetup();

    try {
        List<String> friendList = new ArrayList<>(dbSetup.getFriends(current));
         if(!friendList.isEmpty()){
             for (String friend : friendList){ %>
            <h5> <%= friend %> </h5>
             <% }
         }
         else { %>
            <h5> <font size="30px"> You don't have friends </font></h5>
          <%}
    }

    catch (ClassNotFoundException e) {
        e.printStackTrace();
    } %>

</div>
</body>
</html>