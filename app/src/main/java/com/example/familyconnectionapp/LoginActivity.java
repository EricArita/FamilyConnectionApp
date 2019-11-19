package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import  android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private FirebaseOperation crudFireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        crudFireBase = new FirebaseOperation();

        btnLogin.setOnClickListener(v -> {
            boolean isValidEmail = false;
            boolean isValidPassword = false;
            boolean isExistAccount = false;
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

            UserModel userInfo = crudFireBase.getUser(edtEmail.getText().toString());

            if (userInfo != null) {
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
                alertBuilder.setMessage("Account does not exist! Please create an account");
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
