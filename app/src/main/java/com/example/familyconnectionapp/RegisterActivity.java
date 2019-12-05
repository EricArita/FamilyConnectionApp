package com.example.familyconnectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.location.Location;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtName;
    private EditText edtPassword;
    private EditText edtConfirm;
    private Button btnRegister;
    private FirebaseOperation crudFirebase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    //private Location mLastLocation;

    private String generateRandomCirclCode(){
        String circleCode = "";
        Random rd = new Random();

        for(int i = 1; i <= 6; i++){
            circleCode = circleCode + rd.nextInt(10);
        }

        return circleCode;
    }

    private Boolean isValidRegisterInfo(String name, String email, String password, String confirmPassword){
        if (password.length() < 6 || !confirmPassword.equals(password))
            return false;

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        crudFirebase =  new FirebaseOperation();
        mAuth = FirebaseAuth.getInstance();

        edtConfirm = findViewById(R.id.edtConfirmPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);

        //Intent intentGet = getIntent();
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, CreateCodeActivity.class);
            final String name = edtName.getText().toString();
            final String email = edtEmail.getText().toString();
            final String password = edtPassword.getText().toString();
            final String confirmPassword = edtConfirm.getText().toString();
            final String circleCode = generateRandomCirclCode();
            final Boolean isSharing = false;
            intent.putExtra("code", circleCode);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
            final String date = dateFormat.format(Calendar.getInstance().getTime());

            if (isValidRegisterInfo(name, email, password, confirmPassword)) {

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("", "createUserWithEmail:success");
                                String userId = mAuth.getCurrentUser().getUid();
                                crudFirebase.createUserInfo(userId, name, email, password, date, circleCode, isSharing, 0.0, 0.0);
                                intent.putExtra("userId", userId);
                                startActivity(intent);
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        });
            }
            else {

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setMessage("Your password is not valid. Try again !");
                alertBuilder.setCancelable(true);

                alertBuilder.setPositiveButton(
                        "Ok",
                        (dialog, id) -> dialog.cancel()
                );

                AlertDialog alert = alertBuilder.create();
                alert.show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}
