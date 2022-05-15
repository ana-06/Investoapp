package com.sukrutha.investo4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sukrutha.investo4.fragments.HomeFragment;
import com.sukrutha.investo4.model.post;

import java.util.Date;

public class PostActivity extends AppCompatActivity {

    private Uri uri;

    private ImageView close;
    private ImageView imageAdded;
    private TextView post;
    private TextView description;
    private ImageView img;
    private FirebaseAuth mauth;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mauth = FirebaseAuth.getInstance();
        close=findViewById(R.id.close);
        imageAdded=findViewById(R.id.image_added);
        post=findViewById(R.id.post);

        mauth = FirebaseAuth.getInstance();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostActivity.this, MainActivity.class));
                finish();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                FirebaseDatabase database;
                database = FirebaseDatabase.getInstance();

                database.getReference().child("chal bhag").setValue(69);
                StorageReference storageRef = storage.getReference().child("post").child(FirebaseAuth.getInstance().getUid());
                Intent intent = new Intent(PostActivity.this, FeedActivity.class);
                startActivity(intent);
                                       // .child(new Date().getTime()+"");
                storageRef.putFile(uri);//addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                EditText editText = findViewById(R.id.editTextTextMultiLine);
//                                String sd = editText.toString();
//                                com.sukrutha.investo4.model.post pst = new post();
//                                pst.setPostImage(uri.toString());
//                                pst.setPostedBy(FirebaseAuth.getInstance().getUid());
//                                pst.setPostDescription(sd);
//                                pst.setPostedAt(new Date().getTime());
//                                database.getReference().child("post").push().setValue(pst).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//
//                                    }
//                                });
//                            }
//                        });
//                    }
//                });

            }

        });


    imageAdded.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ImagePicker.with(PostActivity.this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        }
    });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profilemenu, menu);
        return true;
    }


    @Override
    protected void onActivityResult(int rqc , int rc , @Nullable Intent data){
        super.onActivityResult(rqc,rc,data);
        if(data.getData()!=null){
            Uri uri = data.getData();
            imageAdded=findViewById(R.id.image_added);
            //Uri uri = data.getData();
            imageAdded.setImageURI(uri);

        }
 }


}