package com.example.draw_nav;

import java.security.PrivateKey;

public class ACCOUNTSS {
    private String name;
    private  String location;
    private String user;
    private double no;
    private int id;

    public ACCOUNTSS() {
    }

    public ACCOUNTSS(String name, int id, double no, String location, String useRs) {
        this.name = name;
        this.location = location;
        this.user = user;
        this.no = no;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getNo() {
        return no;
    }

    public void setNo(double no) {
        this.no = no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
