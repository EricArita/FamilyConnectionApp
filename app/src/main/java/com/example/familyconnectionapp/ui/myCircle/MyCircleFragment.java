package com.example.familyconnectionapp.ui.myCircle;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.familyconnectionapp.AdapterUser;
import com.example.familyconnectionapp.FirebaseOperation;
import com.example.familyconnectionapp.MapActivity;
import com.example.familyconnectionapp.R;
import com.example.familyconnectionapp.UserModel;
import com.example.familyconnectionapp.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MyCircleFragment extends Fragment {

    private ArrayList<UserViewModel> usersArrayList;
    private ListView lvUser;
    private FirebaseOperation firebaseOperation;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_circle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("List circle members fragment");

        firebaseOperation = new FirebaseOperation();
        mAuth = FirebaseAuth.getInstance();

        String currentUserId = mAuth.getCurrentUser().getUid();

        firebaseOperation.getUserById(currentUserId, currUser -> {
            usersArrayList =  new ArrayList<>(currUser.CircleMembers.values());
            lvUser = getActivity().findViewById(R.id.lvListUser);

            for(UserViewModel member : usersArrayList){
                firebaseOperation.getUserById(member.userId, user -> {
                    if (user.isSharing != member.isSharing){
                        member.isSharing = user.isSharing;
                        usersArrayList.set(usersArrayList.indexOf(member), member);
                        firebaseOperation.setCircleMemberShareLocation(currentUserId, member.userId, user.isSharing);
                    }

                    AdapterUser adapterUser = new AdapterUser(getActivity(), R.layout.user_line, usersArrayList);
                    lvUser.setAdapter(adapterUser);
                    lvUser.setOnItemClickListener((parent, View, position, id) -> DialogLogin(position));
                });
            }
        });
    }

    private void DialogLogin(final int memberIndex)
    {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog);
        dialog.show();

        UserViewModel member = usersArrayList.get(memberIndex);

        Button btnDialogOpenMap = dialog.findViewById(R.id.btnDialogOpenMap);
        Button btnDialogDelete = dialog.findViewById(R.id.btnDialogDelete);
        Button btnDialogClose = dialog.findViewById(R.id.btnDialogClose);
        TextView tvDialog = dialog.findViewById(R.id.tvDialog);

        tvDialog.setText(member.name);

        btnDialogOpenMap.setOnClickListener(v -> {
            if (member.isSharing){
                Bundle bundle = new Bundle();
                bundle.putDouble("kinhdo", member.lng);
                bundle.putDouble("vido", member.lat);

                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("dulieu", bundle);
                startActivity(intent);
            }
            else{
                Toast.makeText(getActivity(), "This member is not sharing their location. So you cannot access one !",  Toast.LENGTH_SHORT).show();
            }
        });

        btnDialogDelete.setOnClickListener(v -> {
            String currentUserId = mAuth.getCurrentUser().getUid();

            usersArrayList.remove(memberIndex);
            firebaseOperation.deleteCircleMember(currentUserId, member.userId);

            AdapterUser adapterUser = new AdapterUser(getActivity(), R.layout.user_line, usersArrayList);
            lvUser.setAdapter(adapterUser);

            dialog.dismiss();
        });

        btnDialogClose.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
}