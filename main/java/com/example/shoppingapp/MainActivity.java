package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private ImageView shoppingcart;
    private Button joinNow, alreadyMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    //start main activity
                    Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Welcome.class));
                }else {
                    Toast.makeText(getApplicationContext(), "NO USER", Toast.LENGTH_SHORT).show();
                }
            }
        };

        shoppingcart = findViewById(R.id.cartImage);

        joinNow = findViewById(R.id.joinNow);
        joinNow.setOnClickListener(this);

        alreadyMember = findViewById(R.id.alreadyMember);
        alreadyMember.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == joinNow){
            Toast.makeText(getApplicationContext(), "JOIN NOW", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), JoinActivity.class));
        }

        if(v == alreadyMember){
            Toast.makeText(getApplicationContext(), "SIGN IN", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        }
    }

    @Override
    protected void onStart() {
        firebaseAuth.addAuthStateListener(authStateListener);
        super.onStart();
    }

    @Override
    protected void onStop() {
        firebaseAuth.removeAuthStateListener(authStateListener);
        super.onStop();
    }
}
