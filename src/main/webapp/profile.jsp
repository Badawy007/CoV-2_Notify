<%  String name = (String) session.getAttribute("username");
    String messageadd = (String) request.getAttribute("messageadd");
    String messageremove = (String) request.getAttribute("messageremove");
    String messagelocation = (String) request.getAttribute("messagelocation");
    String pp = (String) request.getAttribute("pp");
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
                <a class="nav-link" href="notifications.jsp">Notification</a>
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

<h3> Add Friend </h3>
<form action="<%=request.getContextPath()%>/profile" method="post">
    <input type='text' placeholder='Username' name = 'username' class='input-line full-width'>
    <input type="submit" value="Add" />
    <% if (messageadd != null){ %>
        <h5><%= messageadd %> </h5>
    <% } %>

   <%-- Remove Friend --%>

    <h3> Remove Friend </h3>
    <input type='text' placeholder='Username' name = 'removefriend' class='input-line full-width'>
    <input type="submit" value="Remove"/>

    <% if (messageremove != null){ %>
    <h5><%= messageremove %> </h5>
    <% } %>

    <%-- Declare location --%>

    <h3> Declare location </h3>
    <select id="loc" name="location">
        <option value="location1">location1</option>
        <option value="location2">location2</option>
        <option value="location3">location3</option>
        <option value="location4">location4</option>
        <option value="location4">Other</option>
    </select>
    <input type="submit" value = "Add location">

    <% if (messagelocation != null){ %>
    <h5><%= messagelocation %> </h5>
    <% } %>
</form>

<br> <br>
<a href="pcrPos.jsp"/>I am positive</a>

</body>
</html>
