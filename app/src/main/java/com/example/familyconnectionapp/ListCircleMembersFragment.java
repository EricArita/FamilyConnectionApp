package com.example.familyconnectionapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.familyconnectionapp.MapActivity;
import com.example.familyconnectionapp.R;

import java.util.ArrayList;

public class ListCircleMembersFragment extends Fragment {

    private ArrayList<User> usersArrayList;
    private ListView lvUser;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_circle_members, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("List circle members fragment");

        usersArrayList = new ArrayList<>();
        lvUser = getActivity().findViewById(R.id.lvListUser);

        usersArrayList.add(new User("Tho",R.drawable.xanh));
        usersArrayList.add(new User("Nguyen",R.drawable.do1));

        AdapterUser adapterUser = new AdapterUser(getActivity(), R.layout.user_line, usersArrayList);
        lvUser.setAdapter(adapterUser);
        lvUser.setOnItemClickListener((parent, View, position, id) -> DialogLogin(position));
    }

    private void DialogLogin(final int i)
    {
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog);
        dialog.show();

        Button btnDialogOpenMap = dialog.findViewById(R.id.btnDialogOpenMap);
        Button btnDialogDelete = dialog.findViewById(R.id.btnDialogDelete);
        Button btnDialogClose = dialog.findViewById(R.id.btnDialogClose);
        TextView tvDialog = dialog.findViewById(R.id.tvDialog);

        btnDialogOpenMap.setText("Get Location");
        btnDialogDelete.setText("Delete this member");
        tvDialog.setText(usersArrayList.get(i).getUser());

        btnDialogOpenMap.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MapActivity.class);
            startActivity(intent);
        });

        btnDialogDelete.setOnClickListener(v -> {
            usersArrayList.remove(i);
            AdapterUser adapterUser = new AdapterUser(getActivity(), R.layout.user_line, usersArrayList);
            lvUser.setAdapter(adapterUser);
        });

        btnDialogClose.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
}