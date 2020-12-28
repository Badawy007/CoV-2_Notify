package com.covid19;

public class Location {


    private String denomination;
    private String address;
    private double[][] coord ;

    public Location(){
        coord = new double[1][2];
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double[][] getCoord() {
        return coord;
    }

    public void setCoord(double[][] coord) {
        this.coord = coord;
    }

    public void setCoord(double x, double y) {
        this.coord[0][0] = x;
        this.coord[0][1] = y;
    }
}
