package com.covid19.Servlet;

import com.covid19.DatabaseSetup;
import com.covid19.models.Activity;
import com.covid19.models.Location;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/activity")
public class ActivityServlet extends HttpServlet {

    private DatabaseSetup dbSetup;
    private Activity activity;
    private Location location;

    public ActivityServlet() {
        dbSetup = new DatabaseSetup();
        activity = new Activity();
        location = new Location();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String current = (String) session.getAttribute("username");
        activity.setName(request.getParameter("activity"));
        activity.setStartHour(request.getParameter("starttime"));
        activity.setEndHour(request.getParameter("endtime"));
        if (!(request.getParameter("location").equals("Other"))){
            location.setDenomination(request.getParameter("location"));
        } else if ((request.getParameter("location").equals("Other"))){
            location.setDenomination(request.getParameter("denomination"));
            location.setAddress(request.getParameter("address"));
            location.setCoordX(request.getParameter("coordx"));
            location.setCoordY(request.getParameter("coordy"));
            try {
                dbSetup.visitLocation(location.getDenomination(), location.getAddress(),location.getCoordX(), location.getCoordY());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        activity.setLocation(location);

        try {
            dbSetup.doActivity(activity.getName(),activity.getStartHour(),activity.getEndHour(),activity.getLocation().getDenomination(),current);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("activity.jsp").forward(request,response);
    }



}

