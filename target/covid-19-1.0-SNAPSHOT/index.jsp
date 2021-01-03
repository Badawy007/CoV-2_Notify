<html>
<head>
    <meta charset = "UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cov-2_Notify Homepage</title>
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
<h1 align="center"> WELCOME </h1>
    <h2 align="center"> Cov-2 Notify is here for you</h2>
    <h3 align="center"> Unity is strength </h3>
<h4>Symptoms</h4>
<p>
    <b> Most common symptoms:</b> <br>
     - fever <br>
     - dry cough <br>
     - tiredness <br>
     - Less common symptoms: <br>
     - aches and pains <br>
     - sore throat <br>
     - diarrhoea <br>
     - conjunctivitis <br>
     - headache <br>
     - loss of taste or smell <br>
     - a rash on skin, or discolouration of fingers or toes <br> <br>

    <b> Serious symptoms:</b> <br>
     - difficulty breathing or shortness of breath <br>
     - chest pain or pressure <br>
     - loss of speech or movement <br> <br>

    <b> **Seek immediate medical attention if you have serious symptoms. Always call before visiting your doctor or health facility.
       People with mild symptoms who are otherwise healthy should manage their symptoms at home.
       On average it takes 5–6 days from when someone is infected with the virus for symptoms to show, however it can take up to 14 days.</b>
</p>



</div>
</body>
</html>
