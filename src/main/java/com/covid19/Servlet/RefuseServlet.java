package com.covid19.Servlet;

import com.covid19.DatabaseSetup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/refuse")
public class RefuseServlet extends HttpServlet {

    private DatabaseSetup dbSetup;

    public RefuseServlet() {
        dbSetup = new DatabaseSetup();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String current = (String) session.getAttribute("username");
        String friend = request.getParameter("friendref");
        DatabaseSetup dbSetup = new DatabaseSetup();
        try {
            dbSetup.refuseFriend(current, friend);
            dbSetup.refuseFriend(friend, current);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("requests.jsp");
        rd.forward(request,response);
    }
}
