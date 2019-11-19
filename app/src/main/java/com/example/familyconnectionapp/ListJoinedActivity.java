package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListJoinedActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_joined);

        listView=(ListView)findViewById(R.id.lvJoined);

        list=new ArrayList<>();
        Intent intent1=getIntent();

        String name = intent1.getStringExtra("code");
        if(name.isEmpty()!=true) {
                list.add(name);
        }


        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);


    }


}
