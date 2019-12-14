package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class JoinCircleActivity extends AppCompatActivity {

    EditText num1;
    EditText num2;
    EditText num3;
    EditText num4;
    EditText num5;
    EditText num6;
    Button btSubmit;
    TextView joinTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_circle);

        num1=(EditText) findViewById(R.id.edJoin1);
        num2=(EditText) findViewById(R.id.edJoin2);
        num3=(EditText) findViewById(R.id.edJoin3);
        num4=(EditText) findViewById(R.id.edJoin4);
        num5=(EditText) findViewById(R.id.edJoin5);
        num6=(EditText) findViewById(R.id.edJoin6);
        btSubmit=(Button)findViewById(R.id.btJoinSubmit);
        joinTitle=(TextView)findViewById(R.id.tvJoinTitle);


        joinTitle.setText("Enter your invite code");

        btSubmit.setText("Submit");
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String number1 = num1.getText().toString();
                String number2 = num2.getText().toString();
                String number3 = num3.getText().toString();
                String number4 = num4.getText().toString();
                String number5 = num5.getText().toString();
                String number6 = num6.getText().toString();


                if(number1.isEmpty()==true
                        || number2.isEmpty()==true||number3.isEmpty()==true
                        ||number4.isEmpty()==true ||number5.isEmpty()==true||number6.isEmpty()==true)
                {

                    Toast.makeText(JoinCircleActivity.this, "please input number code", Toast.LENGTH_SHORT).show();
                }
                    String code=number1+number2+number3+number4+number5+number6;
                Intent intent =new Intent(JoinCircleActivity.this,ListJoinedActivity.class);
                intent.putExtra("code",code);
                startActivity(intent);
                  //chuoi code
                }

        });
    }
}
