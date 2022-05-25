package com.sukrutha.investo4.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.okhttp.internal.DiskLruCache;
import com.sukrutha.investo4.Adapter.HomePostAdapter;
import com.sukrutha.investo4.Adapter.SearchAdapter;
import com.sukrutha.investo4.R;
import com.sukrutha.investo4.model.HomePostModel;
import com.sukrutha.investo4.model.SearchModel;

import java.util.ArrayList;


public class SearchFragment extends Fragment {


    RecyclerView searchrv;


    ArrayList<SearchModel> searchList;
    TextView textView;
    String searchName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchrv = view.findViewById(R.id.searchRv);
        searchList = new ArrayList<>();
        searchList.add(new SearchModel(R.drawable.boat,
                "Boat Company"));
        searchList.add(new SearchModel(R.drawable.purplerain, "The PurpleRain"));
       // searchList.add(new SearchModel(R.drawable.purplerain, "The PurpleRain"));
       // searchList.add(new SearchModel(R.drawable.purplerain, "The PurpleRain"));
       // searchList.add(new SearchModel(R.drawable.purplerain, "The PurpleRain"));


        SearchAdapter adapter = new SearchAdapter(searchList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        searchrv.setLayoutManager(layoutManager);
        searchrv.setNestedScrollingEnabled(false);
        searchrv.setAdapter(adapter);

        return view;
    }

        @Override
        public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            EditText editText = view.findViewById(R.id.searchField);
            Button button = view.findViewById(R.id.button);

            textView = view.findViewById(R.id.textView5);
            searchName = editText.getText().toString();



            searchrv = view.findViewById(R.id.searchRv);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseFirestore ref = FirebaseFirestore.getInstance();
                    ref.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            for (QueryDocumentSnapshot val : value) {
                                Log.d("peace", "onEvent: Me yaha hu ya hu");
                                if (val.getString("name").equals(searchName)) {
                                    Log.d("users", "onEvent: " + val.getString("name"));
                                    //textView.setText(val.getString("name"));
                                    searchList.add(new SearchModel(R.drawable.boat,val.getString("name")));
                                }

                            }
                            SearchAdapter adapter = new SearchAdapter(searchList, getContext());
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                            searchrv.setLayoutManager(layoutManager);
                            searchrv.setNestedScrollingEnabled(false);
                            searchrv.setAdapter(adapter);



                        }
                    });
                }
            });

        }
}