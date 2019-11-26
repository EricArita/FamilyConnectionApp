package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListUserActivity extends AppCompatActivity {

    ArrayList<User> usersArrayList;
    ListView lvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user2);
        lvUser=(ListView)findViewById(R.id.lvListUser);

        usersArrayList=new ArrayList<>();

        //nhan code sau khi tao


        usersArrayList.add(new User("Tho",R.drawable.xanh));
        usersArrayList.add(new User("Nguyen",R.drawable.do1));
        AdapterUser adapterUser = new AdapterUser(ListUserActivity.this, R.layout.user_line, usersArrayList);
        lvUser.setAdapter(adapterUser);
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogLogin(position);

            }
        });


    }
    private void  DialogLogin(final int i)
    {

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog);

        dialog.show();
        Button button1=(Button)dialog.findViewById(R.id.btDialogGoMap);
        Button button2=(Button)dialog.findViewById(R.id.btDialogDelete);
        TextView textView1=(TextView)dialog.findViewById(R.id.tvDialog1);


        button1.setText("Go Location");
        button2.setText("Delete User");

        textView1.setText(usersArrayList.get(i).getUser());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListUserActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersArrayList.remove(i);
                dialog.dismiss();
                AdapterUser adapterUser = new AdapterUser(ListUserActivity.this, R.layout.user_line, usersArrayList);
                lvUser.setAdapter(adapterUser);

            }
        });





    }
}
