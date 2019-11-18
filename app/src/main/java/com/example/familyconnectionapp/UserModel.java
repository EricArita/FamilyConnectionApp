package com.example.familyconnectionapp;

public class UserModel {

    public String userId;
    public String name;
    public String email;
    public String password;
    public String date;
    public String circleCode;
    public Boolean isSharing;
    public String lat;
    public String lng;

    public UserModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }

    public UserModel( String userId, String name, String email, String password, String date, String circleCode, Boolean isSharing, String lat, String lng) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = date;
        this.circleCode = circleCode;
        this.isSharing = isSharing;
        this.lat = lat;
        this.lng = lng;
    }
}
