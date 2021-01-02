<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.covid19.DatabaseSetup" %>
<%
    String messageadd = (String) request.getAttribute("messageadd");
    String messageremove = (String) request.getAttribute("messageremove");
    String messagelocation = (String) request.getAttribute("messagelocation");
    String username = (String) session.getAttribute("username");

    %>
<html>
<head>
    <meta charset = "UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="styleindex.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script>src="https://code.jquery.com/jquery-3.2.1.slim.min.js"</script>
    <script>src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"</script>

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


<div class="container">

<% if (username != null) {%>

<h5>Username : <%= username  %></h5>


<p> Add Friend </p>
<form action="<%=request.getContextPath()%>/profile" method="post">
  <div class="form-group">
    <input type='text' placeholder='Username' name = 'username' class="form-control" class='input-line full-width'>
    <input type="submit" class="btn btn-primary" value="Add" />
    <% if (messageadd != null){ %>
        <h5><%= messageadd %> </h5>
    <% } %>

   <%-- Remove Friend --%>

    <h3> Remove Friend </h3>
    <input type='text' placeholder='Username' name = 'removefriend' class="form-control" class='input-line full-width'>
    <input type="submit" class="btn btn-primary" value="Remove"/>

    <% if (messageremove != null){ %>
    <h5><%= messageremove %> </h5>
    <% } %>


    </div>
</form>

<a class="btn btn-warning" href="activity.jsp"/>Declare Activity</a>

<a class="btn btn-warning" href="friendList.jsp"/>Display Friendlist</a>

<a class="btn btn-warning" href="edit.jsp"/>Edit Profile</a>

<br> <br>

    <%
        DatabaseSetup  dbSetup = new DatabaseSetup();
        if (dbSetup.getState(username) == 0){%>

            <a class="btn btn-danger" href="pcrPos.jsp"/>I am positive</a>

    <% } else {%>

            <a class="btn btn-success" href="pcrNeg.jsp"/>I am negative</a>

<% } %>
    <%} else { response.sendRedirect("login.jsp");}%>
</div>

</body>
</html>
