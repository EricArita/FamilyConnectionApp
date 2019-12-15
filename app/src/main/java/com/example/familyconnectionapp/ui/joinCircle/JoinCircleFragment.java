package com.example.familyconnectionapp.ui.joinCircle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.familyconnectionapp.FirebaseOperation;
import com.example.familyconnectionapp.MenuActivity;
import com.example.familyconnectionapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class JoinCircleFragment extends Fragment {

    private EditText edtDigit1;
    private EditText edtDigit2;
    private EditText edtDigit3;
    private EditText edtDigit4;
    private EditText edtDigit5;
    private EditText edtDigit6;
    private Button btnJoin;
    private FirebaseOperation firebaseOperation;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_join_circle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("join circle fragment");
        firebaseOperation = new FirebaseOperation();

        edtDigit1 = getView().findViewById(R.id.edtDigit1);
        edtDigit2 = getView().findViewById(R.id.edtDigit2);
        edtDigit3 = getView().findViewById(R.id.edtDigit3);
        edtDigit4 = getView().findViewById(R.id.edtDigit4);
        edtDigit5 = getView().findViewById(R.id.edtDigit5);
        edtDigit6 = getView().findViewById(R.id.edtDigit6);
        btnJoin = getView().findViewById(R.id.btnJoin);

        btnJoin.setOnClickListener(v -> {
            String number1 = edtDigit1.getText().toString();
            String number2 = edtDigit2.getText().toString();
            String number3 = edtDigit3.getText().toString();
            String number4 = edtDigit4.getText().toString();
            String number5 = edtDigit5.getText().toString();
            String number6 = edtDigit6.getText().toString();
            String circleCode = number1 + number2 + number3 + number4 + number5 + number6;

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            String currentUserId = mAuth.getCurrentUser().getUid();

            if(number1.isEmpty() || number2.isEmpty()||number3.isEmpty() ||number4.isEmpty() ||number5.isEmpty()||number6.isEmpty())
            {
                Toast.makeText(getActivity(), "Please input number code", Toast.LENGTH_SHORT).show();
            }
            else{
                firebaseOperation.getUserById(currentUserId, currUser -> {
                    firebaseOperation.getUserByCircleCode(circleCode, user -> firebaseOperation.updateCircleMemebers(user.userId, currUser));
                });

                Intent intent =new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}