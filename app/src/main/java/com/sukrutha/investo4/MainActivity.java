package com.sukrutha.investo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;

    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginFunction();


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

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterMainActivity.class));

            }
        });
    }


}