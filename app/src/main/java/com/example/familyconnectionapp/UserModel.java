package com.example.familyconnectionapp;

import java.util.*;

public class UserModel {

    public String userId;
    public String name;
    public String email;
    public String password;
    public String date;
    public String circleCode;
    public Boolean isSharing;
    public Boolean SOS;
    public Double lat;
    public Double lng;
    public  Map<String, UserViewModel> CircleMembers;
    public  Map<String, CircleModel> JoinedCircleList;


    public UserModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
        CircleMembers = new HashMap<>();
        JoinedCircleList = new HashMap<>();
        SOS = false;
    }

    public UserModel( String userId, String name, String email, String password, String date, String circleCode, Boolean isSharing, Double lat, Double lng) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = date;
        this.circleCode = circleCode;
        this.isSharing = isSharing;
        this.lat = lat;
        this.lng = lng;
        CircleMembers = new HashMap<>();
        JoinedCircleList = new HashMap<>();
        SOS = false;
    }
}
