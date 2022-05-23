package com.sukrutha.investo4.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sukrutha.investo4.Adapter.HomePostAdapter;
import com.sukrutha.investo4.R;
import com.sukrutha.investo4.model.HomePostModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView homepostrv;
    ArrayList<HomePostModel> homePostList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_home, container, false);

        homepostrv = view.findViewById(R.id.homepostRv);
        homePostList = new ArrayList<>();
        homePostList.add(new HomePostModel(R.drawable.boat,R.drawable.boatpost,
                "Boat Company","34","12"));
        homePostList.add(new HomePostModel(R.drawable.purplerain,R.drawable.purplerainpost,
                "The PurpleRain","34","12"));
        homePostList.add(new HomePostModel(R.drawable.boat,R.drawable.boatpost,
                "Boat Company","34","12"));
        homePostList.add(new HomePostModel(R.drawable.boat,R.drawable.boatpost,
                "Boat Company","34","12"));
        homePostList.add(new HomePostModel(R.drawable.boat,R.drawable.boatpost,
                "Boat Company","34","12"));

        HomePostAdapter adapter = new HomePostAdapter(homePostList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        homepostrv.setLayoutManager(layoutManager);
        homepostrv.setNestedScrollingEnabled(false);
        homepostrv.setAdapter(adapter);














        return view;


    }
}