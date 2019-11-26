package com.example.familyconnectionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterUser extends BaseAdapter {
    private Context context;
    private int layout;
    private List<User> UserListView;

    public AdapterUser(Context context, int layout, List<User> userListView) {
        this.context = context;
        this.layout = layout;
        UserListView = userListView;
    }

    @Override
    public int getCount() {
        return UserListView.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder
    {
        TextView User;
        ImageView Status;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            viewHolder.User=(TextView)view.findViewById(R.id.tvUserLine);
            viewHolder.Status=(ImageView) view.findViewById(R.id.imUserStatus);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }


        User user=UserListView.get(i);

        viewHolder.User.setText(user.getUser());
        viewHolder.Status.setImageResource(user.getStatus());


        return view;
    }

}
