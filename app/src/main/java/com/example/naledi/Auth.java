package com.example.naledi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class Auth extends AppCompatActivity {

    TextView registerText;
    EditText email;
    EditText password;
    Button loginBtn;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    final LoadingCard loadingCard = new LoadingCard(Auth.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_auth);

        registerText = findViewById(R.id.l_register);

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerActivity();
            }
        });

        loginBtn = findViewById(R.id.l_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void registerActivity() {
        Intent intent = new Intent(Auth.this, register.class);
        startActivity(intent);
    }

    public void login() {
        email = findViewById(R.id.l_email);
        password = findViewById(R.id.l_password);

        String e = email.getText().toString();
        String p = password.getText().toString();

        if (e.isEmpty() || p.isEmpty()) {
            Toast.makeText(Auth.this, "Fill in both email and password", Toast.LENGTH_LONG).show();
        } else {
            loadingCard.startLoading();
            mFirebaseAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        loadingCard.stopLoading();
                        Toast.makeText(Auth.this, "Your Logged in", Toast.LENGTH_LONG).show();
                    } else {
                        loadingCard.stopLoading();
                        Toast.makeText(Auth.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}