package com.covid19.Servlet;

import com.covid19.DatabaseSetup;
import com.covid19.User;

import javax.servlet.RequestDispatcher;
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
        user.setName(request.getParameter("name"));
        user.setUsername(request.getParameter("username"));
        user.setBirthdate(request.getParameter("date"));
        String pass1 = request.getParameter("password");
        String pass2 = request.getParameter("confirm");
        if (pass1.equals(pass2)){
            user.setPassword(pass1);
            try {
                if(!dbSetup.verifyUsername(user.getUsername())){
                    dbSetup.registerUser(user);
                    response.sendRedirect("login.jsp");
                }
                else{
                    request.setAttribute("error","*Username already taken");
                    RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
                    rd.forward(request,response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("error","*Passwords don't match");
            RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
            rd.forward(request,response);
        }
    }
}
