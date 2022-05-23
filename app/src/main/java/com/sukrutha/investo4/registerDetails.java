package com.sukrutha.investo4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class registerDetails extends AppCompatActivity {
    public MyUser user = new MyUser();


    private EditText emailReg;
    private EditText passReg;

    private Button btnReg;
    private Button btnLogin;
    public Uri uri;
    ImageView putPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);



        emailReg = findViewById(R.id.register_email);
        passReg = findViewById(R.id.register_password);

        btnReg = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);


        putPic = findViewById(R.id.profile_image);



        Button button = findViewById(R.id.btn_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText eName = findViewById(R.id.register_name);
                EditText eAbout = findViewById(R.id.register_about);
                EditText ePhone = findViewById(R.id.register_phone);

                String name = eName.getText().toString();
                String about = eAbout.getText().toString();
                String phone = ePhone.getText().toString();


                user.setName(name);
                user.setAbout(about);
                user.setNumber(phone);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Log.d("done", about+name+"");
                db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(user);
                Intent intent = new Intent(registerDetails.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        putPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(registerDetails.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });


    }


    @Override
    protected void onActivityResult(int rqc , int rc , @Nullable Intent data){
        super.onActivityResult(rqc,rc,data);
        if(data.getData()!=null){

            uri = data.getData();

            //Uri uri = data.getData();
            putPic.setImageURI(uri);
            Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getPhotoUrl();
            user.setUri(uri);
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().trim();

            Map<String, Uri> pic = new HashMap<>();
            pic.put("profile",uri);


            db.collection("usersPic").document(uid).set(pic);


        }
    }
}








