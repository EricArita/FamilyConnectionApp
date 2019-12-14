package com.example.familyconnectionapp;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.familyconnectionapp.LocationSettingsFragment.TAG;

public class FirebaseOperation {

    private FirebaseDatabase firebaseDb;
    private DatabaseReference dbReference;
    public static UserModel user = new UserModel();

    public  FirebaseOperation() {
        firebaseDb = FirebaseDatabase.getInstance();
        dbReference = firebaseDb.getReference("Users");
    }

    public void createUserInfo(String userId, String name, String email, String password, String date, String circleCode, Boolean isSharing, Double lat, Double lng) {
        //String userId = dbReference.child("Users").push().getKey();
        UserModel user = new UserModel(userId, name, email, password, date, circleCode, isSharing, lat, lng);
        dbReference.child(userId).setValue(user);

        return;
    }

    public void getUserByCircleCode(String circleCode, MyCallback myCallback){
        dbReference.orderByChild("circleCode").equalTo(circleCode).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    user = snapshot.getValue(UserModel.class);
                    myCallback.onCallback(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "getUser:onCancelled", databaseError.toException());
            }
        });
    }

    public void updateUser(String userId, Double lat, Double lng){
        try {
            dbReference.child(userId).child("lat").setValue(lat);
            dbReference.child(userId).child("lng").setValue(lng);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCircleMemebers(String updatedUserId, String currentUserId){
        try {
            dbReference.child(updatedUserId).child("CircleMembers").child(currentUserId).child("memberId").setValue(currentUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
