package com.covid19.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseSetup {

    public int registerUser(User user) throws ClassNotFoundException {

        int result = 0;

        String INSERT_USERS_SQL = "INSERT INTO user" +
                "  (id,username, password) VALUES " +
                " (?, ?, ?);";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(INSERT_USERS_SQL)) {

            Query.setInt(1, 102);
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
