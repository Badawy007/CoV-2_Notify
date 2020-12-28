package com.covid19;

import java.util.Date;

public class Activity {

    private Date date;
    private String startHour;
    private String endHour;
    private Location location;

    public Activity(){
        date = new Date();
        startHour = "00:00";
        endHour = "23:59";
        location = new Location();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
