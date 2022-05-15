package com.sukrutha.investo4;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterMainActivity extends AppCompatActivity {

    private EditText emailReg;
    private EditText passReg;

    private Button btnReg;
    private Button btnLogin;

    //Firebase authentication
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  // oncreate sets the view whenever we open the app //savedInstanceState is used to save the state of application
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);
        mAuth=FirebaseAuth.getInstance();// to perform various operations on database(intializing the FireBaseAuth instance)

        emailReg = findViewById(R.id.register_email);
        passReg = findViewById(R.id.register_password);

        btnReg = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailReg.getText().toString().trim();
                String pass = passReg.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    emailReg.setError("Required Field..");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    passReg.setError("Required Field..");
                    return;
                }
                // register the user into the firebase
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        if (task.isSuccessful()) {
                            // toast- small  message on the screen for a short duration of time
                            Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                        }
                        else
                            Toast.makeText(getApplicationContext(), "Unsuccessfull", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }

        });


    }

}