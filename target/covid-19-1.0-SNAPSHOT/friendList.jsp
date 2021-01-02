<%@ page import="com.covid19.DatabaseSetup" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friend List</title>
</head>
<body>

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
    }

    catch (ClassNotFoundException e) {
        e.printStackTrace();
    } %>
</body>
</html>