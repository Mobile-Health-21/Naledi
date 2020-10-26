package com.example.naledi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    EditText name, email, password, cpassword;
    Button registerbtn;
    TextView login;
    FirebaseAuth mfirebaseAuth;

    final LoadingCard loadingCard = new LoadingCard(register.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_register);

        mfirebaseAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.r_name);
        email = findViewById(R.id.r_email);
        password = findViewById(R.id.r_password);
        cpassword = findViewById(R.id.r_cpassword);
        registerbtn = findViewById(R.id.r_btn);
        login = findViewById(R.id.r_login);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String cpassword1 = cpassword.getText().toString();

                if (name1.isEmpty() || email1.isEmpty() || password1.isEmpty() || cpassword1.isEmpty()) {
                    Toast.makeText(register.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                } else {
                    if (password1.equals(cpassword1)) {
                        loadingCard.startLoading();
                        mfirebaseAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    loadingCard.stopLoading();
                                    Toast.makeText(register.this, "Failed", Toast.LENGTH_LONG).show();
                                } else {
                                    loadingCard.stopLoading();
                                    Toast.makeText(register.this, "Successful", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(register.this, "Passwords Don't match", Toast.LENGTH_LONG).show();
                        Log.d("Passwords", "p1: " + password1 + "p2:  " + cpassword1);
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivity();
            }
        });
    }

    public void loginActivity() {
        Intent intent = new Intent(register.this, Auth.class);
        startActivity(intent);
    }
}