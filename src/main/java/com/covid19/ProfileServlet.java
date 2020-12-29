package com.covid19;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private DatabaseSetup dbSetup;
    private User user;
    private String username;
    private String result;

    public ProfileServlet() {
        dbSetup = new DatabaseSetup();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String current = (String) session.getAttribute("username");
        String friend = request.getParameter("username");
        String username_delete = request.getParameter("username-delete");
        if(friend != "" && username_delete == ""){
            try {
                dbSetup.addFriend(current,friend);
                System.out.println("done add");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (username_delete != "" && friend == ""){
            try {
                dbSetup.removeFriend(current,username_delete);
                System.out.println("done remove");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
        rd.forward(request,response);


        /*HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        username = request.getParameter("username");
        String username_delete = request.getParameter("username-delete");
        result = null;

        if (username != null) {
            try {
                result = dbSetup.addUser(username);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (result != "" && !username.equals(session.getAttribute("username"))) {
                out.println(result);}
            else {
                out.println("User not found add");
        }
    }

        if (username_delete != null){
        try {
            if (dbSetup.deleteUser(username_delete) != 0){
            out.println("User Deleted");
            if(username_delete.equals(session.getAttribute("username"))){
                response.sendRedirect("index.jsp");
                session.invalidate();
            }

            }
            else {
                out.println("User not found delete");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
        }*/
    }

}
