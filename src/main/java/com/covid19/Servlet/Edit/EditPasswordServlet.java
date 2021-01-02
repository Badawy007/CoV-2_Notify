package com.covid19.Servlet.Edit;

import com.covid19.DatabaseSetup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editPassword")
public class EditPasswordServlet extends HttpServlet {

    private DatabaseSetup dbSetup;

    public EditPasswordServlet() {
        dbSetup = new DatabaseSetup();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String current = (String) session.getAttribute("username");
        String newpass = (String) request.getAttribute("newpass");
        String cnewpass = (String) request.getAttribute("cnewpass");



    }
}
