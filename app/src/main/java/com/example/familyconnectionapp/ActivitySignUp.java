package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitySignUp extends AppCompatActivity {

    TextView tvEmail;
    TextView tvName;
    TextView  tvPass;
    TextView tvConfirm;
    EditText edEmail;
    EditText edName;
    EditText edPass;
    EditText edConfirm;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tvConfirm=(TextView)findViewById(R.id.tvSignUpConFirm);
        tvEmail=(TextView)findViewById(R.id.tvSignUpEmail);
        tvName=(TextView)findViewById(R.id.tvSignName);
        tvPass=(TextView)findViewById(R.id.tvSignUpPassW);

        tvConfirm.setText("Confirm");
        tvEmail.setText("Email");
        tvPass.setText("PassWord");
        tvName.setText("Name");

        edConfirm=(EditText)findViewById(R.id.edSignConfirm);
        edEmail=(EditText)findViewById(R.id.edSignEmail);
        edName=(EditText)findViewById(R.id.edSignName);
        edPass=(EditText)findViewById(R.id.edSignPass);

        Intent intentGet=getIntent();
        bt=(Button)findViewById(R.id.btSign);
        bt.setText("Create");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             Intent intent = new Intent(ActivitySignUp.this,MenuActivity.class);
                startActivity(intent);
            }
        });




    }
}
