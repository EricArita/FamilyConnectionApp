package com.example.familyconnectionapp;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FirebaseOperation {

    private FirebaseDatabase firebaseDb;
    private DatabaseReference dbReference;
    private UserModel userInfo = new UserModel();

    public  FirebaseOperation() {
        firebaseDb = FirebaseDatabase.getInstance();
        dbReference = firebaseDb.getReference();
    }

    public void testOperation(){
        for (int i = 0; i <= 10; i++) {
            dbReference.child("Users").child("key " + i).setValue("Value " + i);
        }
    }

    public void createUserInfo(String userId, String name, String email, String password, String date, String circleCode, Boolean isSharing, Double lat, Double lng) {
        //String userId = dbReference.child("Users").push().getKey();
        UserModel user = new UserModel(userId, name, email, password, date, circleCode, isSharing, lat, lng);
        dbReference.child("Users").child(userId).setValue(user);

        return;
    }

    public void updateUser(String userId,Double lat, Double lng){
        try {
            dbReference.child("Users").child(userId).child("lat").setValue(lat);
            dbReference.child("Users").child(userId).child("lng").setValue(lng);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
