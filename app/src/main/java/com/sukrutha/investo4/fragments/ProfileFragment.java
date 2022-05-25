package com.sukrutha.investo4.fragments;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;
import com.sukrutha.investo4.R;


public class ProfileFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText number;
    EditText about;
    ImageView profile;
    EditText editText;
    TextView Usname;

    private ProgressDialog mdialog;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //number.setText(FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        number = view.findViewById(R.id.PhoneNumber);
        about = view.findViewById(R.id.editTextTextMultiLine);
        profile  = view.findViewById(R.id.profile_image);
        Usname = view.findViewById(R.id.Uname);


        ProgressDialog progressBar = new ProgressDialog(view.getContext());
       // progressBar.setCancelable(true);//you can cancel it by pressing back button
        //progressBar.setMessage("Loading....");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //progressBar.setProgress(0);//initially progress is 0
        //progressBar.setMax(100);//sets the maximum value 100
        progressBar.show();//displays the progress bar

        db.collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                editText = (EditText) view.findViewById(R.id.mailId);
                String email;
                email= FirebaseAuth.getInstance().getCurrentUser().getEmail().trim();
                editText.setText(email);



                Log.d("gotit", documentSnapshot.getId() + " => " +documentSnapshot.getData()+FirebaseAuth.getInstance().getCurrentUser().getUid());
                number.setText(documentSnapshot.getString("number"));
                about.setText(documentSnapshot.getString("about"));
                Usname.setText(documentSnapshot.getString("name"));


            }
        });


        db.collection("usersPic").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String profileURL = documentSnapshot.getString("profile");
                Picasso.get().load(profileURL).placeholder(R.drawable.img).into(profile);

            }
        });
        progressBar.dismiss();

    }
}
