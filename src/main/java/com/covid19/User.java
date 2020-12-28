package com.covid19;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    private String contact;
    private List<User> friends;
    private boolean infected;

    public User() {
        this.friends = new ArrayList<>();
        this.infected = false;
    }

    public void addFriend(User user){
        friends.add(user);
    }

    public void removeFriends(User user){
        friends.remove(user);
    }

    public boolean isInfected(){
        return infected;
    }

    // Setters and Getters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
