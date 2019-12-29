package com.example.familyconnectionapp;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.familyconnectionapp.ui.myLocation.LocationSettingsFragment.TAG;

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

        try{
            dbReference.child(userId).setValue(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUserByCircleCode(String circleCode, MyCallback myCallback){
        dbReference.orderByChild("circleCode").equalTo(circleCode).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    user = snapshot.getValue(UserModel.class);
                    try{
                        myCallback.onCallback(user);
                    }
                    catch (Throwable throwable){

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "getUser:onCancelled", databaseError.toException());
            }
        });
    }

    public void getUserById(String userId, MyCallback myCallback){
        dbReference.orderByChild("userId").equalTo(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    user = snapshot.getValue(UserModel.class);
                    try{
                        myCallback.onCallback(user);
                    }
                    catch (Throwable throwable){

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "getUser:onCancelled", databaseError.toException());
            }
        });
    }

    public void updateUserLocation(String userId, Double lat, Double lng){
        try {
            dbReference.child(userId).child("lat").setValue(lat);
            dbReference.child(userId).child("lng").setValue(lng);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCircleMemberLocation(String userId, String memberId, Double lat, Double lng){
        dbReference.child(userId).child("CircleMembers").child(memberId).child("lat").setValue(lat);
        dbReference.child(userId).child("CircleMembers").child(memberId).child("lng").setValue(lng);
    }

    public void setCircleMemberShareLocation(String userId, String memberId, boolean allowShareLocation){
        try {
            dbReference.child(userId).child("CircleMembers").child(memberId).child("isSharing").setValue(allowShareLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSOS(String userId, String memberId, boolean SOS){
        try {
            dbReference.child(userId).child("CircleMembers").child(memberId).child("SOS").setValue(SOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setShareLocation(String userId, boolean allowShareLocation){
        try {
            dbReference.child(userId).child("isSharing").setValue(allowShareLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCircleMemebers(UserModel updatedUser, UserModel currentUser){
        try {
            UserViewModel currUser = new UserViewModel(currentUser.userId, currentUser.name, currentUser.isSharing, currentUser.SOS,currentUser.lng, currentUser.lat);
            dbReference.child(updatedUser.userId).child("CircleMembers").child(currUser.userId).setValue(currUser);
            dbReference.child(currentUser.userId).child("JoinedCircleList").child(updatedUser.userId).child("circleCode").setValue(updatedUser.circleCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCircleMember(String currUserId, String memberId){
        try{
            dbReference.child(currUserId).child("CircleMembers").orderByChild("userId").equalTo(memberId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        snapshot.getRef().removeValue();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteJoinedCircle(String currUserId, String memberId){
        try{
            dbReference.child(memberId).child("JoinedCircleList").child(currUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        snapshot.getRef().removeValue();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
