package com.sukrutha.investo4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity { // inheritance MainActivitycanaccessthemethodsinAppcompatAcitivity
    private EditText email;
    private EditText password;

    private Button btnLogin;
    private Button btnRegister;

    private FirebaseAuth mauth;

    //Progress Dialgoue

    private ProgressDialog mdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // reference to a Bundle object that is passed into the onCreate method of every Android Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mauth = FirebaseAuth.getInstance();

        mdialog=new ProgressDialog(this );

        LoginFunction();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mauth.getCurrentUser()!=null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    }

    //    public void login(View view){
//        EditText mail= findViewById(R.id.emailField);
//        String email=mail.toString();
//        EditText password = findViewById(R.id.PasswordField);
//        String pas=password.toString();
//
//
//    }

    private void LoginFunction(){
        email= findViewById(R.id.emailField);
        password = findViewById(R.id.PasswordField);

        btnLogin=findViewById(R.id.btn_login);
        btnRegister=findViewById(R.id.btn_reg);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    email.setError("Required Field..");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    password.setError("Required Field..");
                    return;
                }
                mdialog.setMessage("Processing..");
                mdialog.show();

                mauth.signInWithEmailAndPassword(mEmail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            mdialog.dismiss();
                        }
                        else{

                            mdialog.dismiss();

                            Toast.makeText(getApplicationContext(),"Login Failed..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterMainActivity.class));

            }
        });
   }
   //@Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.profilemenu, menu);
//        return true;
//    }


}