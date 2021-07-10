package com.example.draw_nav;

public class Houses {
    private   String name;
    private String email;
    private int phone;
    private String image;


    public Houses(String name, String email, int phone, String image) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public Houses() {

    }

    public int getImage() {
        return Integer.parseInt(image);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
