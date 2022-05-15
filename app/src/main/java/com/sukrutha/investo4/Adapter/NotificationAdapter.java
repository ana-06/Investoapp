package com.sukrutha.investo4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sukrutha.investo4.R;
import com.sukrutha.investo4.model.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder> {

    ArrayList<NotificationModel> list;
    Context context;

    public NotificationAdapter(ArrayList<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification2sample,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
       NotificationModel model = list.get(position);
       // to set profile image by getting the data
        holder.profile.setImageResource(model.getProfile());
       // to set the notification and time by getting their respective data
        holder.notification.setText(model.getNotification());
        holder.time.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        // to get more than one notification
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile;
        TextView notification,time;


        public viewHolder(@NonNull View itemView) {

            super(itemView);
            profile = itemView.findViewById(R.id.profile_image);
            notification=itemView.findViewById(R.id.notification);
            time=itemView.findViewById(R.id.time);
        }
    }
}
