package com.covid19.Servlet;

import com.covid19.DatabaseSetup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminPanel")
public class AdminPanelServlet extends HttpServlet {

    private DatabaseSetup dbSetup;

    public AdminPanelServlet() {
        dbSetup = new DatabaseSetup();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteUser = request.getParameter("deleteuser");
        String negUser = request.getParameter("neguser");

        if (deleteUser != "" && negUser == ""){
            try {
                if(dbSetup.deleteUser(deleteUser)){
                    request.setAttribute("messagedelete","*User Deleted");
                } else {
                    request.setAttribute("messagedelete","*User Not found");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (negUser != "" && deleteUser == ""){
            try {
                if(dbSetup.setNegative(negUser)>0){
                request.setAttribute("messagenegative","*User set as Negative");
                } else {
                    request.setAttribute("messagenegative","*An error has occured");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("adminPanel.jsp").forward(request,response);
    }

}
