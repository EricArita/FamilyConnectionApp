package com.example.familyconnectionapp;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
    private List<UserViewModel> userListView;

    public AdapterUser(Context context, int layout, List<UserViewModel> userListView) {
        this.context = context;
        this.layout = layout;
        this.userListView = userListView;
    }

    @Override
    public int getCount() {
        return userListView.size();
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
        ImageView SOS;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.User = view.findViewById(R.id.tvMemberName);
            viewHolder.Status = view.findViewById(R.id.imgUserStatus);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }


        UserViewModel user = userListView.get(i);

        if (user.SOS){
            String text = user.name + "      " + "SOS";
            Spannable spannable = new SpannableString(text);
            spannable.setSpan(new ForegroundColorSpan(Color.RED), text.indexOf("SOS"), text.indexOf("SOS") + "SOS".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.User.setText(spannable);
        }
        else{
            viewHolder.User.setText(user.name);
            viewHolder.Status.setImageResource(user.isSharing ? R.drawable.sharing : R.drawable.unsharing);
        }

        return view;
    }
}
