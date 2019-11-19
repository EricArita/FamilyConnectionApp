package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvConfirm;
    private EditText edtEmail;
    private EditText edtName;
    private EditText edtPassword;
    private EditText edtConfirm;
    private Button btnRegister;
    private FirebaseOperation crudFirebase;

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

        edtConfirm = findViewById(R.id.edtConfirmPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);

        //Intent intentGet = getIntent();
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

            final String name = edtName.getText().toString();
            final String email = edtEmail.getText().toString();
            final String password = edtPassword.getText().toString();
            final String confirmPassword = edtConfirm.getText().toString();
            final String circleCode = generateRandomCirclCode();
            final Boolean isSharing = false;
            final Map<String, Double> deviceCoordinates = MapService.getCoordinates();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
            final String date = dateFormat.format(Calendar.getInstance().getTime());

            if (isValidRegisterInfo(name, email, password, confirmPassword)) {
                startActivity(intent);
                crudFirebase.createUser(name, email, password, date, circleCode, isSharing, deviceCoordinates.get("lat"), deviceCoordinates.get("lng"));
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
}
