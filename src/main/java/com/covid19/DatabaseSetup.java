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

        String INSERT_USERS_SQL =   "INSERT INTO user" +
                                    "(name, username, password) VALUES " +
                                    " (?, ?, ?);";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
            PreparedStatement Query = connection.prepareStatement(INSERT_USERS_SQL)) {
            Query.setString(1, user.getName());
            Query.setString(2, user.getUsername());
            Query.setString(3, user.getPassword());
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

            ResultSet resultSet = Query.executeQuery();
            status = resultSet.next();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
    }

    public boolean addFriend(String username, String friend) throws ClassNotFoundException {

        boolean temp = false;

        if(this.verifyUsername(friend)){

            String ADD_FRIEND_SQL = "INSERT INTO user_relationship (RelatingUserID,RelatedUserID,Type) " +
                                    "VALUES (?,?,?)";

            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
                 PreparedStatement Query = connection.prepareStatement(ADD_FRIEND_SQL)) {
                Query.setString(1, username);
                Query.setString(2, friend);
                Query.setString(3, "friend");
                Query.executeUpdate();
                temp = true;

            } catch (SQLException e) {
                printSQLException(e);
            }
        }
        return temp;
    }



    public boolean removeFriend(String username, String friend) throws ClassNotFoundException {

        boolean temp = false;

        if(this.verifyRelationship(username,friend)) {

            String REMOVE_FRIEND_SQL =  "DELETE FROM user_relationship " +
                                        "WHERE RelatingUserID = ? AND RelatedUserID = ?";

            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
                 PreparedStatement Query = connection.prepareStatement(REMOVE_FRIEND_SQL)) {
                Query.setString(1, username);
                Query.setString(2, friend);
                Query.executeUpdate();
                temp = true;

            } catch (SQLException e) {
                printSQLException(e);
            }
        }
        return temp;
    }

    //Verify relationship between 2 users, friends or not

    public boolean verifyRelationship(String user1, String user2) throws ClassNotFoundException {
        boolean temp = false;
        String VERIFY_RELATIONSHIP_SQL =    "SELECT COUNT(*) " +
                                            "FROM user_relationship " +
                                            "WHERE RelatingUserID = ? AND RelatedUserID = ?";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(VERIFY_RELATIONSHIP_SQL)) {
            Query.setString(1, user1);
            Query.setString(2, user2);ResultSet resultSet = Query.executeQuery();
            resultSet.next();
            int result = resultSet.getInt(1);
            if (result > 0){
                temp = true;
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return temp;
    }


    //Verify if user exists
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

    public boolean deleteUser(String username) throws ClassNotFoundException {
        boolean temp = false;

        String DELETE_USER_SQL = "DELETE FROM user " +
                                 "WHERE username = ?";

        Class.forName("com.mysql.jdbc.Driver");
        if (verifyUsername(username)) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
                 PreparedStatement Query = connection.prepareStatement(DELETE_USER_SQL)) {
                Query.setString(1, username);
                Query.executeUpdate();
                temp = true ;
            } catch (SQLException e) {
                printSQLException(e);
            }
        }
        return temp;
    }

    public String typeUser(String username) throws ClassNotFoundException {
        String type = "test";

        String TYPE_USER_SQL =  "Select type " +
                                "FROM user " +
                                "WHERE username = ?";

        Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
                 PreparedStatement Query = connection.prepareStatement(TYPE_USER_SQL)) {
                Query.setString(1, username);
                ResultSet resultSet = Query.executeQuery();
                while(resultSet.next()){
                    type = resultSet.getString(1);
                    return type;
                }
            } catch (SQLException e){
                printSQLException(e);
            }
        return type;
    }

    public int visitLocation(String username, String location) throws ClassNotFoundException {
        int result = 0;

        String VISIT_LOCATION_SQL = "INSERT INTO visited_location (Visitor_Name,Location_Name) " +
                                    "VALUES (?,?)";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(VISIT_LOCATION_SQL)) {
            Query.setString(1, username);
            Query.setString(2, location);
            result = Query.executeUpdate();

        } catch (SQLException e){
            printSQLException(e);
        }
        return result;
    }

    public int setPositive(String username) throws ClassNotFoundException {
        int result = 0;

        String SET_POSITIVE_SQL =   "UPDATE user "+
                                    "SET PCR = ? "+
                                    "WHERE user.username = ? ";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(SET_POSITIVE_SQL)) {
            Query.setString(1, "1");
            Query.setString(2, username);
            result = Query.executeUpdate();

        } catch (SQLException e){
            printSQLException(e);
        }
        return result;
    }

    public int setNegative(String username) throws ClassNotFoundException {
        int result = 0;

        String SET_NEGATIVE_SQL =   "UPDATE user "+
                                    "SET PCR = ? "+
                                    "WHERE user.username = ? ";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(SET_NEGATIVE_SQL)) {
            Query.setString(1, "0");
            Query.setString(2, username);
            result = Query.executeUpdate();

        } catch (SQLException e){
            printSQLException(e);
        }
        return result;
    }





    /* UPDATE user
    SET PCR = 'pos'
    WHERE user.username = 'ab1'; */

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