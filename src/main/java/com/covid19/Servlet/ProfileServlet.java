package com.covid19.Servlet;

import com.covid19.DatabaseSetup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        try {
            request.setAttribute("usertype",dbSetup.typeUser((String) session.getAttribute("username")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("profile.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String current = (String) session.getAttribute("username");
        String friend = request.getParameter("username");
        String removeFriend = request.getParameter("removefriend");
        String location = request.getParameter("location");

        if(friend != "" && removeFriend == ""){
            if (current.equals(friend)){
                request.setAttribute("messageadd","*You can't add yourself");
            } else if (friend.equals("admin")){
                request.setAttribute("messageadd","*This user can't be added");
            } else {
                try {
                    if (dbSetup.addFriend(current, friend)) {
                        dbSetup.addFriend(friend, current);
                        request.setAttribute("messageadd", "*Friend Request Sent");
                    } else {
                        request.setAttribute("messageadd", "*User not found");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (removeFriend != "" && friend == ""){
            try {
                if(dbSetup.removeFriend(current,removeFriend)){
                    dbSetup.removeFriend(removeFriend,current);
                    request.setAttribute("messageremove","*User Unfriended");
                } else {
                    request.setAttribute("messageremove","*User Not found");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (location != "" && friend == "" && removeFriend == ""){
            try {
                if(dbSetup.visitLocation(current,location) > 0) {
                    request.setAttribute("messagelocation","Location Added");
                } else{
                    request.setAttribute("messagelocation","Location Already added");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("profile.jsp").forward(request,response);
    }

}
