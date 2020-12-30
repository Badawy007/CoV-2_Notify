package com.covid19.Servlet;

import com.covid19.DatabaseSetup;
import com.covid19.User;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private DatabaseSetup dbSetup;
    private User user;
    private boolean result;


    public LoginServlet() {
        dbSetup = new DatabaseSetup();
        user = new User();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        user.setUsername(username);
        user.setPassword(password);
        result = true;
        try {
          result = dbSetup.validateUser(user);
          String type =   dbSetup.typeUser(username);

            if (result) {
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                switch (type){
                    case "admin":
                    response.sendRedirect("adminPanel.jsp");
                    System.out.println(type);
                    break;
                    case "user":
                    response.sendRedirect("profile.jsp");
                    break;
                }
            } else {
                request.setAttribute("failmessage","*Wrong username/password");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request,response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}