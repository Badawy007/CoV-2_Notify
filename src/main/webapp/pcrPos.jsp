<%@ page import="com.covid19.DatabaseSetup" %>

<% DatabaseSetup dbSetup = new DatabaseSetup();
    String username = (String) session.getAttribute("username");

    try {
        dbSetup.setPositive(username);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
    rd.forward(request,response); %>