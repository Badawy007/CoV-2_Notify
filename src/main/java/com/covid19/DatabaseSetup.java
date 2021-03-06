package com.covid19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseSetup {
    /**
     * enregistre un utilisateur
     * @param user
     * @return
     * @throws ClassNotFoundException
     */

    public int registerUser(User user) throws ClassNotFoundException {

        int result = 0;

        String INSERT_USERS_SQL =   "INSERT INTO user" +
                                    "(name, username, password, birthdate) VALUES " +
                                    " (?, ?, ?, ?);";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
            PreparedStatement Query = connection.prepareStatement(INSERT_USERS_SQL)) {
            Query.setString(1, user.getName());
            Query.setString(2, user.getUsername());
            Query.setString(3, user.getPassword());
            Query.setString(4, user.getBirthdate());
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

    /**
     * ajouter un ami
     * @param username
     * @param friend
     * @return
     * @throws ClassNotFoundException
     */
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


    /**
     * Supprimer un ami
     * @param username
     * @param friend
     * @return
     * @throws ClassNotFoundException
     */
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

    public int doActivity(String activityName, String startTime ,String endTime,String locationName,String current) throws ClassNotFoundException{
        int result = 0;

        String DO_ACTIVITY_SQL = "INSERT INTO practiced_activity (ActivityName,ActivityStartTime,ActivityEndTime,LocationName,ActivityActor) " +
                                  "VALUES (?,?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(DO_ACTIVITY_SQL)) {
            Query.setString(1, activityName);
            Query.setString(2, startTime);
            Query.setString(3, endTime);
            Query.setString(4, locationName);
            Query.setString(5, current);
            result = Query.executeUpdate();

        } catch (SQLException e){
            printSQLException(e);
        }
        return result;
    }

    public int visitLocation(String locatioName, String locationAddress, String x, String y) throws ClassNotFoundException {
        int result = 0;

        String VISIT_LOCATION_SQL = "INSERT INTO location (LocationName,LocationAddress, LocationX, LocationY) " +
                                    "VALUES (?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(VISIT_LOCATION_SQL)) {
            Query.setString(1, locatioName);
            Query.setString(2, locationAddress);
            Query.setString(3, x);
            Query.setString(4, y);
            result = Query.executeUpdate();

        } catch (SQLException e){
            printSQLException(e);
        }
        return result;
    }

    /**
     *
     * @return la liste des endroits enregistrés
     * @throws ClassNotFoundException
     */
    public List<String> getLocations() throws ClassNotFoundException{
        List<String> locations = new ArrayList<>();

        String GET_LOCATIONS_SQL = "SELECT LocationName " +
                                   "FROM location " ;

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(GET_LOCATIONS_SQL)) {
            ResultSet resultSet = Query.executeQuery();
            while(resultSet.next()){
                locations.add(resultSet.getString("LocationName"));
            }
        } catch (SQLException e){
            printSQLException(e); }
        return locations;
    }

    /**
     * la fonction qui permet de se declarer positif
     * @param username
     * @return
     * @throws ClassNotFoundException
     */
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

    /**
     *
     * @param username
     * @return le nom
     * @throws ClassNotFoundException
     */
    public String getName(String username) throws ClassNotFoundException {
        String Name = "";

        String GET_NAME_SQL =   "SELECT Name "+
                                "FROM user "+
                                "WHERE username = ? ";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(GET_NAME_SQL)) {
            Query.setString(1, username);
            ResultSet resultSet = Query.executeQuery();
            resultSet.next();
            Name = resultSet.getString("name");

        } catch (SQLException e){
            printSQLException(e);
        }
        return Name;
    }

    /**
     *
     * @param username
     * @return le jour de naissance
     * @throws ClassNotFoundException
     */
    public String getBirthdate(String username) throws ClassNotFoundException {
        String Name = "";

        String GET_BIRTHDATE_SQL =  "SELECT birthdate "+
                                    "FROM user "+
                                    "WHERE username = ? ";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(GET_BIRTHDATE_SQL)) {
            Query.setString(1, username);
            ResultSet resultSet = Query.executeQuery();
            resultSet.next();
            Name = resultSet.getString("birthdate");

        } catch (SQLException e){
            printSQLException(e);
        }
        return Name;
    }

    /**
     * fonction qui permet de changer le nom
     * @param username
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    public int changeName(String username, String name) throws ClassNotFoundException {
        int result = 0;

        String CHANGE_NAME_SQL =   "UPDATE user "+
                "SET name = ? "+
                "WHERE  username = ? ";


        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(CHANGE_NAME_SQL)) {
            Query.setString(1, name);
            Query.setString(2, username);
            result = Query.executeUpdate();

        } catch (SQLException e){
            printSQLException(e);
        }
        return result;

    }

    /**
     * fonction qui permet de se declarer negatif
     * @param username
     * @return
     * @throws ClassNotFoundException
     */
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

    public int getState(String username) throws ClassNotFoundException {
        int state = 0;
        String GET_STATE_SQL =  "SELECT PCR "+
                                "FROM user "+
                                "WHERE  username = ? ";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(GET_STATE_SQL)) {
            Query.setString(1, username);
            ResultSet resultSet = Query.executeQuery();
            resultSet.next();
            if (resultSet.getInt("PCR") == 1){
                state = 1;
            }

        } catch (SQLException e){
            printSQLException(e);
        }
        return state;
    }

    /**
     *
     * @param current
     * @return la liste d'amis d'un utilisateur
     * @throws ClassNotFoundException
     */
    public List<String> getFriends(String current) throws ClassNotFoundException{
        List<String> friends = new ArrayList<>();

        String GET_FRIENDS_SQL = "SELECT RelatedUserID " +
                                 "FROM user_relationship " +
                                 "WHERE RelatingUserID = ? AND State = ? ";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(GET_FRIENDS_SQL)) {
            Query.setString(1, current);
            Query.setString(2,"1");
            ResultSet resultSet = Query.executeQuery();
            while(resultSet.next()){
                if (!resultSet.getString("RelatedUserID").equals(current)){
                friends.add(resultSet.getString("RelatedUserID"));
                }
            }
        } catch (SQLException e){
            printSQLException(e); }
        return friends;
    }

    /**
     *
     * @param current
     * @return la liste des invitations d'amitiés
     * @throws ClassNotFoundException
     */
    public List<String> getFriendRequests(String current) throws ClassNotFoundException{
        List<String> friendRequests = new ArrayList<>();

        String GET_FRIENDS_SQL = "SELECT RelatingUserID " +
                                 "FROM user_relationship " +
                                 "WHERE RelatedUserID = ? AND State = ? ";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(GET_FRIENDS_SQL)) {
            Query.setString(1, current);
            Query.setString(2,"0");
            ResultSet resultSet = Query.executeQuery();
            while(resultSet.next()){
                friendRequests.add(resultSet.getString("RelatingUserID"));
            }
        } catch (SQLException e){
            printSQLException(e); }
        return friendRequests;
    }

    /**
     * fonction qui permet d'accepter une invitation d'amitié
     * @param current
     * @param friend
     * @throws ClassNotFoundException
     */
    public void acceptFriend(String current, String friend) throws ClassNotFoundException {

        String ACCEPT_FRIEND_SQL =  "UPDATE user_relationship " +
                                    "SET State = ? " +
                                    "WHERE (RelatingUserID = ? AND RelatedUserID = ?)";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(ACCEPT_FRIEND_SQL)) {
            Query.setString(1,"1");
            Query.setString(2,friend);
            Query.setString(3,current);
            Query.executeUpdate();
        } catch (SQLException e){
            printSQLException(e); }
    }

    /**
     * fonction qui permet de refuser une invitation d'amitié
     * @param current
     * @param friend
     * @throws ClassNotFoundException
     */
    public void refuseFriend(String current, String friend) throws ClassNotFoundException {

        String REFUSE_FRIEND_SQL =  "DELETE FROM user_relationship " +
                                    "WHERE (RelatingUserID = ? AND RelatedUserID = ?)";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(REFUSE_FRIEND_SQL)) {
            Query.setString(1,friend);
            Query.setString(2,current);
            Query.executeUpdate();
        } catch (SQLException e){
            printSQLException(e); }
    }

    /**
     * fonction qui permet d'envoyer les notifactions aux utilisateurs
     * @param current
     * @return
     * @throws ClassNotFoundException
     */
    public List<String> notifyUser(String current) throws ClassNotFoundException {
        List<String> infected = new ArrayList<>();

        String NOTIFY_USER_SQL = "SELECT RelatedUserID " +
                                 "FROM user_relationship " +
                                 "FULL JOIN user " +
                                 "ON RelatingUserID = ? AND RelatedUserID = user.username AND user.PCR = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(NOTIFY_USER_SQL)) {
            Query.setString(1,current);
            Query.setString(2,"1");
            ResultSet resultSet = Query.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString(1));
                infected.add(resultSet.getString(1));
            }

        } catch (SQLException e){
            printSQLException(e); }

        return infected;
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

    /**
     * permet d'avoir la liste des personnes ayant ete au meme endrtoit d'une personne infectee sur une periode de 10 jours
     * @param lieu
     * @return
     */
    private List<String> getListePersonLocation(String lieu) throws ClassNotFoundException {
        List<String> liste=new ArrayList<>();
        String LOCATION_PARTAGEE_USER_SQL=" SELECT Visitor_Name "+
                                           " FROM visited_location "+
                                            " WHERE Location_Name= ?";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/covdb?useSSL=false", "root", "root");
             PreparedStatement Query = connection.prepareStatement(LOCATION_PARTAGEE_USER_SQL)) {
            Query.setString(1, lieu);
            ResultSet resultSet = Query.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString(1));
                liste.add(resultSet.getString(1));
            }

        }
        catch (SQLException e){
        printSQLException(e); }
        return liste;
    }
}