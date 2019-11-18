package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLogin extends AppCompatActivity {

    EditText edEmail;
    EditText edPass;
    TextView tvEmail;
    TextView tvPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail=(EditText)findViewById(R.id.edLogEmail);
        edPass=(EditText)findViewById(R.id.edLogPass);



        tvEmail=(TextView)findViewById(R.id.tvLogEmail);
        tvPass=(TextView)findViewById(R.id.tvLogPass);

        tvEmail.setText("Email");
        tvPass.setText("PassWord");



    }
}
