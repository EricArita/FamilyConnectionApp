package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLogin extends AppCompatActivity {

    EditText edEmail;
    EditText edPass;
    TextView tvEmail;
    TextView tvPass;
    Button btLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail=(EditText)findViewById(R.id.edLogEmail);
        edPass=(EditText)findViewById(R.id.edLogPass);
        btLogin=(Button)findViewById(R.id.btLogin);



        tvEmail=(TextView)findViewById(R.id.tvLogEmail);
        tvPass=(TextView)findViewById(R.id.tvLogPass);

        tvEmail.setText("Email");
        tvPass.setText("PassWord");
        btLogin.setText("Login");

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityLogin.this,MenuActivity.class);
                startActivity(intent);
            }
        });



    }
}
