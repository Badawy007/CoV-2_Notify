<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%String messagedelete = (String) request.getAttribute("messagedelete");%>
<html>
<head>
    <title>Admin Panel</title>
</head>
<body>
<h1>Admin panel</h1>

<h3> Delete User </h3>
<form action="<%=request.getContextPath()%>/adminPanel" method="post">
<input type='text' placeholder='Username' name = 'deleteuser' class='input-line full-width'>
<input type="submit" value="Delete"/>
<% if (messagedelete != null){ %>
<h5><%= messagedelete %> </h5>
<% } %>
</form> <br>
<a href="login.jsp"/>Logout</a>
</body>
</html>