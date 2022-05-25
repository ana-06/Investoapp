package com.sukrutha.investo4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class PostActivity extends AppCompatActivity {

    public Uri uri;

    private ImageView close;
    private ImageView imageAdded;
    private TextView post;
    private TextView description;
    private ImageView img;
    private FirebaseAuth mauth;
    FirebaseDatabase database;
    StorageReference filePath;
    post pst = new post();
    String des;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mauth = FirebaseAuth.getInstance();
        close=findViewById(R.id.close);
        imageAdded=findViewById(R.id.image_added);
        post=findViewById(R.id.post);
        database = FirebaseDatabase.getInstance();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostActivity.this, MainActivity.class));
                finish();
            }
        });


        imageAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(PostActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
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
            uri = data.getData();
            imageAdded=findViewById(R.id.image_added);
            //Uri uri = data.getData();

            imageAdded.setImageURI(uri);

            pst.setPostImage(uri.toString());
                            pst.setPostedAt(new Date().getTime());

                            pst.setPostedBy(FirebaseAuth.getInstance().getUid());
                            pst.setPostDescription(des);
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("Post").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(pst);

//            filePath = FirebaseStorage.getInstance().getReference("Posts").child(FirebaseAuth.getInstance().getUid()).child(new Date().getTime()
//                    + "");
//            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            pst.setPostImage(uri.toString());
//                            pst.setPostedAt(new Date().getTime());
//                            //pst.setPostImage(uri.toString());
//                            pst.setPostedBy(FirebaseAuth.getInstance().getUid());
//                            pst.setPostDescription(des);
//                            database.getReference().child("Post").push().setValue(pst).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    Toast.makeText(PostActivity.this, "Added", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(PostActivity.this,FeedActivity.class);
//                                    startActivity(intent);
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.d("lode lag gaye", "onFailure: I cant do that");
//                                }
//                            });
//                        }
//                    });


//                    FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().trim();
//                    db.collection("Post").document(uid).set(pst);
                    // FirebaseDatabase database = FirebaseDatabase.getInstance();
//                database.getReference().child("Post").push().setValue(pst).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//
//                    }
//                }
//                ).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(PostActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                }
//            });




        }
    }

    public void posted(View view){

        EditText editText = findViewById(R.id.editTextTextMultiLine );
        des= editText.getText().toString();



         filePath = FirebaseStorage.getInstance().getReference("Posts").child(FirebaseAuth.getInstance().getUid()).child(new Date().getTime()
                + "");




        Intent intent = new Intent(PostActivity.this, FeedActivity.class);
        startActivity(intent);
    }
}













