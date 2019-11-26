package com.example.familyconnectionapp;

public class User {
    private String User ;
    private int Status;

    public User(String user, int status) {
        User = user;
        Status = status;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
