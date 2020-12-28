package com.covid19;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private DatabaseSetup dbSetup;

    public ProfileServlet() {
        dbSetup = new DatabaseSetup();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        out.println("Welcome " + session.getAttribute("username") + "!");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String username_delete = request.getParameter("username-delete");
        String result = null;
        try {
            result = dbSetup.addUser(username);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (result != "") {
            out.println(result);
        }else if (username == null);
        else {
            out.println("User not found add");
        }

        try {
            if (dbSetup.deleteUser(username_delete) != 0){
            out.println("User Deleted");
            } else if (username_delete == null);
            else {
                out.println("User not found delete");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
