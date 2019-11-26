package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import  android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private FirebaseOperation crudFireBase;
    private UserModel userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        userInfo = new UserModel();

        crudFireBase = new FirebaseOperation();

        btnLogin.setOnClickListener(v -> {
            boolean isExistAccount = false;
            boolean isValidEmail = false;
            boolean isValidPassword = false;
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

           userInfo = crudFireBase.getUser(edtEmail.getText().toString());

           Log.d("tag", userInfo.toString());

            if (userInfo.email != null) {
                isExistAccount = true;

                if (userInfo.email.equals(edtEmail.getText().toString())) {
                    isValidEmail = true;

                    if (userInfo.password.equals(edtPassword.getText().toString())) {
                        isValidPassword = true;
                    }
                }
            }

            if (!isExistAccount){
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setMessage("Account does not exists !");
                alertBuilder.setCancelable(true);

                alertBuilder.setPositiveButton(
                        "Ok",
                        (dialog, id) -> dialog.cancel()
                );

                AlertDialog alert = alertBuilder.create();
                alert.show();
            }
            else if (!isValidEmail) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                    alertBuilder.setMessage("Email invalid !");
                    alertBuilder.setCancelable(true);

                    alertBuilder.setPositiveButton(
                            "Ok",
                            (dialog, id) -> dialog.cancel()
                    );

                    AlertDialog alert = alertBuilder.create();
                    alert.show();
            }
            else if (!isValidPassword) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setMessage("Password invalid !");
                alertBuilder.setCancelable(true);

                alertBuilder.setPositiveButton(
                        "Ok",
                        (dialog, id) -> dialog.cancel()
                );

                AlertDialog alert = alertBuilder.create();
                alert.show();
            }
            else{
                startActivity(intent);
            }
        });
    }
}
