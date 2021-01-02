package com.covid19.models;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class BaseUser implements Serializable {

    protected String username;
    protected String password;
    protected String name;
    protected String address;
    protected String contact;
    protected List<BaseUser> friends;
    protected Date dateBirth;
    protected boolean infected;
    protected boolean contactCase;
    protected Date infectionDate;
    public BaseUser(){

    }
    public BaseUser(String username, String password, String name, String address, String contact, List<BaseUser> friends, Date dateBirth, boolean infected, boolean contactCase, Date infectionDate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.friends = friends;
        this.dateBirth = dateBirth;
        this.infected = infected;
        this.contactCase = contactCase;
        this.infectionDate = infectionDate;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<BaseUser> getFriends() {
        return friends;
    }

    public void setFriends(List<BaseUser> friends) {
        this.friends = friends;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    public boolean isContactCase() {
        return contactCase;
    }

    public void setContactCase(boolean contactCase) {
        this.contactCase = contactCase;
    }

    public Date getInfectionDate() {
        return infectionDate;
    }

    public void setInfectionDate(Date infectionDate) {
        this.infectionDate = infectionDate;
    }
}
