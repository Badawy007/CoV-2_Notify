package com.covid19;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseSetup {

    public int registerUser(User user) throws ClassNotFoundException {

        int result = 0;

        String INSERT_USERS_SQL = "INSERT INTO user" +
                "  (id,username, password) VALUES " +
                " (?, ?, ?);";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(INSERT_USERS_SQL)) {

            Query.setInt(1, 101);
            Query.setString(2, user.getUsername());
            Query.setString(3, user.getPassword());

            System.out.println(Query);
            result = Query.executeUpdate();

        }

        catch(SQLException e){
            printSQLException(e);
        }

    return result;
    }


    public boolean validateUser(User user) throws ClassNotFoundException {

        boolean status = false;

        String VALIDATE_USERS_SQL = "select * from user where username = ? and password = ? ";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");

             PreparedStatement Query = connection
                     .prepareStatement(VALIDATE_USERS_SQL)) {
            Query.setString(1, user.getUsername());
            Query.setString(2, user.getPassword());

            System.out.println(Query);
            ResultSet resultSet = Query.executeQuery();
            status = resultSet.next();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
    }

    public String addUser(String username) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String temp = "";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
            PreparedStatement Query = connection.prepareStatement("select * from user where username = ?")) {
            Query.setString(1, username);
            ResultSet resultSet = Query.executeQuery();
            while(resultSet.next()){
                temp = resultSet.getString("username");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return temp;
    }

    public int deleteUser(String username) throws ClassNotFoundException {
        int result = 0;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement("delete from user where username = ?")) {
            Query.setString(1, username);
            result = Query.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
