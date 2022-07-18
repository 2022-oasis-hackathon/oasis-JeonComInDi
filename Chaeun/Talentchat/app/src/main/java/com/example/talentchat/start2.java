package com.example.talentchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class start2 extends AppCompatActivity {

    Button loginbutton;
    Button signupbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start2);

        loginbutton = findViewById(R.id.login);
        loginbutton.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), login.class));
        });

        signupbutton = findViewById(R.id.signup);
        signupbutton.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), activity_main.class));
        });

    }

}