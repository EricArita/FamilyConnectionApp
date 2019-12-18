package com.example.familyconnectionapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class ChatActivity extends AppCompatActivity {
    private ListView listView;
    private MessageAdapter adapter;
    private String loggedInUserName = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);

        Button btnSend = findViewById(R.id.btn_chat_send);
        TextView msgInput = findViewById(R.id.msg_input);

        showAllOldMessages();
        hideKeyboard(this);

        btnSend.setOnClickListener(view -> {
            if (msgInput.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Please enter some texts!", Toast.LENGTH_SHORT).show();
            }
            else {
                FirebaseDatabase.getInstance()
                        .getReference()
                        .child("Messages")
                        .push()
                        .setValue(new ChatModel(msgInput.getText().toString(),
                                FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                                FirebaseAuth.getInstance().getCurrentUser().getUid())
                        );
                msgInput.setText("");

                showAllOldMessages();
            }
        });
    }

    private void showAllOldMessages () {
        loggedInUserName = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("Main", "user id: " + loggedInUserName);

        listView = findViewById(R.id.list_msg);

        adapter = new MessageAdapter(this, ChatModel.class, R.layout.fragment_chat,
                FirebaseDatabase.getInstance().getReference());
        listView.setAdapter(adapter);
    }

    private void hideKeyboard(Activity activity) {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public String getLoggedInUserName () {
        return loggedInUserName;
    }
}
