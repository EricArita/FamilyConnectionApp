package com.example.familyconnectionapp;

import java.util.*;

public class UserViewModel {

    public String userId;
    public String name;
    public Boolean isSharing;

    public UserViewModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserViewModel( String userId, String name, Boolean isSharing) {
        this.userId = userId;
        this.name = name;
        this.isSharing = isSharing;
    }
}
