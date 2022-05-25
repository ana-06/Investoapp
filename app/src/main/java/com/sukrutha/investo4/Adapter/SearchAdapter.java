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

import com.sukrutha.investo4.model.SearchModel;

import java.util.ArrayList;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.viewHolder> {

    ArrayList<SearchModel> list;
    Context context;

    public SearchAdapter(ArrayList<SearchModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout which is created
        View view = LayoutInflater.from(context).inflate(R.layout.search_rv,parent,false);
        //passing the view
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        //  SearchModel model = list.get(position);
        SearchModel model = list.get(position);
        holder.profile.setImageResource(model.getProfile());

        holder.name.setText(model.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{


        ImageView profile;
        TextView name;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profile_image);

            name = itemView.findViewById(R.id.userName);

        }
    }
}
