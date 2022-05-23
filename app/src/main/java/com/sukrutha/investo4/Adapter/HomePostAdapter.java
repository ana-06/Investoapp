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
import com.sukrutha.investo4.model.HomePostModel;

import java.util.ArrayList;


public class HomePostAdapter extends RecyclerView.Adapter<HomePostAdapter.viewHolder> {

    ArrayList<HomePostModel> list;
    Context context;

    public HomePostAdapter(ArrayList<HomePostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout which is created
        View view = LayoutInflater.from(context).inflate(R.layout.homepost_rv,parent,false);
        //passing the view
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        HomePostModel model = list.get(position);

        holder.profile.setImageResource(model.getProfile());
        holder.postImage.setImageResource(model.getPostImage());
        holder.name.setText(model.getName());
        holder.like.setText(model.getLike());
        holder.comment.setText(model.getComment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{


        ImageView profile,postImage;
        TextView name, comment, like;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profile_image);
            postImage = itemView.findViewById(R.id.postImg);
            name = itemView.findViewById(R.id.userName);
            comment = itemView.findViewById(R.id.comment);
            like = itemView.findViewById(R.id.like);
        }
    }
}
