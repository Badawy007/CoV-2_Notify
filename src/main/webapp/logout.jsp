<%--
  Created by IntelliJ IDEA.
  User: BJR-Desktop
  Date: 12/29/2020
  Time: 9:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>

<%  session.invalidate();
    response.sendRedirect("index.jsp"); %>

</body>
</html>
