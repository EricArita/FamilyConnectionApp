package com.example.familyconnectionapp;

public class UserViewModel {

    public String userId;
    public String name;
    public Boolean isSharing;
    public Double lng;
    public Double lat;

    public UserViewModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserViewModel( String userId, String name, Boolean isSharing, Double lng, Double lat) {
        this.userId = userId;
        this.name = name;
        this.isSharing = isSharing;
        this.lng = lng;
        this.lat = lat;
    }
}
