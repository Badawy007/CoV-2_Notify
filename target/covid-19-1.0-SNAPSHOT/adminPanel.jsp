<%  String messagedelete = (String) request.getAttribute("messagedelete");
    String messagenegative = (String) request.getAttribute("messagenegative");  %>

<html>
<head>
    <meta charset = "UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
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
            <a class="nav-link" href="<%=request.getContextPath()%>/logout">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</div>

    <h3> Delete User </h3>
    <form action="<%=request.getContextPath()%>/adminPanel" method="post">
    <input type='text' placeholder='Username' name = 'deleteuser' class='input-line full-width'>
    <input type="submit" value="Delete"/>
    <% if (messagedelete != null){ %>
    <h5><%= messagedelete %> </h5>
    <% } %>
<br>
    <h3> Declare User as Negative </h3>
    <input type='text' placeholder='Username' name = 'neguser' class='input-line full-width'>
    <input type="submit" value="Declare Negative"/>
    <% if (messagenegative != null){ %>
    <h5><%= messagenegative %> </h5>
    <% } %>
</form>
</body>
</html>
