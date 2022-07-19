package com.example.talentchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class login extends AppCompatActivity {

    Button loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginbutton = findViewById(R.id.login2);
        loginbutton.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), home.class));
        });
    }

}

