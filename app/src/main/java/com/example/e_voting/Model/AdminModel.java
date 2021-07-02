package com.example.e_voting.Model;

public class AdminModel {
    private String ID;
    private String password;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminModel() {
    }

    public AdminModel(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }
}
