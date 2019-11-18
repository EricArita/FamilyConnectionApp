package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private TextView tvEmail;
    private TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtPassword=(EditText)findViewById(R.id.edtPassword);

        tvEmail=(TextView)findViewById(R.id.tvEmail);
        tvPassword=(TextView)findViewById(R.id.tvPassword);

        tvEmail.setText("Email");
        tvPassword.setText("PassWord");

    }
}
