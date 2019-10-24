package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText email, password;
    private Button signin_now;
    private String email_enter, password_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    startActivity(new Intent(getApplicationContext(), Welcome.class));
                }
            }
        };


        email = findViewById(R.id.signin_email);
        password = findViewById(R.id.signin_password);
        signin_now = findViewById(R.id.signin_button);
        signin_now.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == signin_now){
            email_enter = email.getText().toString().trim();
            password_enter = password.getText().toString().trim();

            if (email_enter.isEmpty() || password_enter.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please check your credentials", Toast.LENGTH_SHORT).show();
                return;
            }
            firebaseAuth.signInWithEmailAndPassword(email_enter, password_enter).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Welcome.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "TRY AGAIN", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}
