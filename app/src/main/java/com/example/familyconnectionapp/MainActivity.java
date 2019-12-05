package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnGetStart;
    TextView tvLogin;
    TextView tvConten;

    private FirebaseOperation crudFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crudFirebase = new FirebaseOperation();

        btnGetStart = findViewById(R.id.btGetStarted);
        tvLogin = findViewById(R.id.tvLogin);
        tvConten = findViewById(R.id.tvConten);

        tvLogin.setText("Login");
        tvConten.setText("Already have an account ?");

        btnGetStart.setOnClickListener(v -> {

                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

        });

        tvLogin.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }


}
