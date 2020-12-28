package com.covid19;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private DatabaseSetup dbSetup;
    private User user;

    public RegisterServlet() {
        user  = new User();
        dbSetup = new DatabaseSetup();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        user.setUsername(request.getParameter("username"));
        String pass1 = request.getParameter("password");
        String pass2 = request.getParameter("confirm");
        if (pass1.equals(pass2)){
            user.setPassword(pass1);
            try {
                dbSetup.registerUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("login.jsp");

        } else {
            out.println("Passwords don't match");
            response.sendRedirect("signup.jsp");
        }

    }
}
