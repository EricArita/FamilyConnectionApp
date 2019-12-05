package com.example.familyconnectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import  android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private FirebaseOperation crudFireBase;
    private UserModel userInfo;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        userInfo = new UserModel();

        crudFireBase = new FirebaseOperation();
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(v -> {
            boolean isExistAccount = false;
            boolean isValidEmail = false;
            boolean isValidPassword = false;
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

           //userInfo = crudFireBase.getUser(edtEmail.getText().toString());
            final String email = edtEmail.getText().toString();
            final String password = edtPassword.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Login successfully.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, email+password,
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    });

//            if (userInfo.email != null) {
//                isExistAccount = true;
//
//                if (userInfo.email.equals(edtEmail.getText().toString())) {
//                    isValidEmail = true;
//
//                    if (userInfo.password.equals(edtPassword.getText().toString())) {
//                        isValidPassword = true;
//                    }
//                }
//            }
//
//            if (!isExistAccount){
//                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//                alertBuilder.setMessage("Account does not exists !");
//                alertBuilder.setCancelable(true);
//
//                alertBuilder.setPositiveButton(
//                        "Ok",
//                        (dialog, id) -> dialog.cancel()
//                );
//
//                AlertDialog alert = alertBuilder.create();
//                alert.show();
//            }
//            else if (!isValidEmail) {
//                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//                    alertBuilder.setMessage("Email invalid !");
//                    alertBuilder.setCancelable(true);
//
//                    alertBuilder.setPositiveButton(
//                            "Ok",
//                            (dialog, id) -> dialog.cancel()
//                    );
//
//                    AlertDialog alert = alertBuilder.create();
//                    alert.show();
//            }
//            else if (!isValidPassword) {
//                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//                alertBuilder.setMessage("Password invalid !");
//                alertBuilder.setCancelable(true);
//
//                alertBuilder.setPositiveButton(
//                        "Ok",
//                        (dialog, id) -> dialog.cancel()
//                );
//
//                AlertDialog alert = alertBuilder.create();
//                alert.show();
//            }
//            else{
//                startActivity(intent);
//            }
        });
    }
}
