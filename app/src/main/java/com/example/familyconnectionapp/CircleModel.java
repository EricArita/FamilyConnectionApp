package com.example.familyconnectionapp;

public class CircleModel {

    public String circleCode;

    public CircleModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public CircleModel( String circleCode) {
        this.circleCode = circleCode;
    }
}
