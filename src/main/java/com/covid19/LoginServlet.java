package com.covid19;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private DatabaseSetup dbSetup;
    private User user;


    public LoginServlet() {
        dbSetup = new DatabaseSetup();
        user = new User();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);

        try {
            if (dbSetup.validateUser(user)) {

                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                session.setAttribute("password",password);

                //session.invalidate();

                response.sendRedirect("profile.jsp");
            } else {
                out.println("Wrong username/password");

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}