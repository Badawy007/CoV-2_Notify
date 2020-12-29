package com.covid19;

import javax.servlet.http.HttpServletRequest;
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
                "  (name, username, password) VALUES " +
                " (?, ?, ?);";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
            PreparedStatement Query = connection.prepareStatement(INSERT_USERS_SQL)) {
            Query.setString(1, user.getName());
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

    public void addFriend(String username, String friend) throws ClassNotFoundException {

        String ADD_FRIEND_SQL = "INSERT INTO user_relationship (RelatingUserID,RelatedUserID,Type) " +
                                "VALUES (?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
            PreparedStatement Query = connection.prepareStatement(ADD_FRIEND_SQL)) {
            Query.setString(1, username);
            Query.setString(2, friend);
            Query.setString(3, "friend");
            Query.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void removeFriend(String username, String friend) throws ClassNotFoundException {

        String ADD_FRIEND_SQL = "DELETE FROM user_relationship " +
                                "WHERE RelatingUserID = ? AND RelatedUserID = ?";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(ADD_FRIEND_SQL)) {
            Query.setString(1, username);
            Query.setString(2, friend);
            Query.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
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

    public boolean verifyUsername(String username) throws ClassNotFoundException{
        boolean exist = false;
        String VERIFY_USERS_SQL =   "SELECT COUNT(*) " +
                                    "FROM user " +
                                    "WHERE user.username = ?";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(VERIFY_USERS_SQL)) {
             Query.setString(1, username);
             ResultSet resultSet = Query.executeQuery();
             resultSet.next();
             int result = resultSet.getInt(1);
             if (result > 0){
                 exist = true;
             }
             } catch (SQLException e) {
                printSQLException(e);
             }
        return exist;
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
