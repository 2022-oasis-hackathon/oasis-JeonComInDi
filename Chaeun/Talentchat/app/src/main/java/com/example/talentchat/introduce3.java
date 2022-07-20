package com.example.talentchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class introduce3 extends AppCompatActivity {

    ImageView imageView_button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce3);

        imageView_button3 = findViewById(R.id.imageView21);
        imageView_button3.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), home.class));
        });
    }
}