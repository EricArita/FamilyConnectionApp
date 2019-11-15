package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class CreateCodeActivity extends AppCompatActivity {

    TextView tvCreate1;
    TextView tvCreate2;
    TextView tvCode;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_code);

        //find id
        tvCode=(TextView)findViewById(R.id.tvCrteateCode);
        tvCreate1=(TextView)findViewById(R.id.tvCreate1);
        done=(Button)findViewById(R.id.btCreateDone);

        //random
        Random temp= new Random();
        int numberCode=100000 + temp.nextInt(899999);

        //set text
        tvCreate1.setText("Share this invite code with the people you want in your circle");
        done.setText("Done");
        tvCode.setText(numberCode+" ");


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreateCodeActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });


    }
}
