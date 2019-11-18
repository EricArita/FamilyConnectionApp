package com.example.familyconnectionapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class FirebaseOperation {

    private FirebaseDatabase firebaseDb;
    private DatabaseReference dbReference;

    public  FirebaseOperation() {
        firebaseDb = FirebaseDatabase.getInstance();
        dbReference = firebaseDb.getReference();
    }

    public void testOperation(){
        for (int i = 0; i <= 10; i++) {
            dbReference.child("Users").child("key " + i).setValue("Value " + i);
        }
    }

    public void createUser(String name, String email, String password, String date, String circleCode, Boolean isSharing, String lat, String lng) {
        String userId = dbReference.child("Users").push().getKey();
        UserModel user = new UserModel(userId, name, email, password, date, circleCode, isSharing, lat, lng);
        dbReference.child("Users").child(userId).setValue(user);
    }

    public void getUser(){

    }
}
