package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class CreateCodeActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvCode;
    Button btDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_code);
        tvTitle=(TextView)findViewById(R.id.tvCodeTitle);
        tvCode=(TextView)findViewById(R.id.tvCode);
        btDone=(Button)findViewById(R.id.btDone);

        tvTitle.setText("Share this code with the people you want in your circle");

        Intent intent=getIntent();
        tvCode.setText(intent.getStringExtra("code"));
        btDone.setText("Done");
        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CreateCodeActivity.this,MenuActivity.class);
                startActivity(intent1);

            }
        });




    }
}
