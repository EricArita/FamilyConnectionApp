package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btGetStar;
    TextView tvLogin;
    TextView tvConten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGetStar=(Button)findViewById(R.id.btGetStarted);
        tvLogin=(TextView)findViewById(R.id.tvLogin);
        tvConten=(TextView)findViewById(R.id.tvConten);

        tvLogin.setText("Login");
        tvConten.setText("Already have an account ?");

        btGetStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,ActivitySignUp.class);
                startActivity(intent);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityLogin.class);
                startActivity(intent);


            }
        });

    }


}
