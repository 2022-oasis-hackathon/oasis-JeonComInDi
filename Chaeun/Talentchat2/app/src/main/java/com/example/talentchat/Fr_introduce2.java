package com.example.talentchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Fr_introduce2 extends AppCompatActivity {

    ImageView imageView_button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce2);

        imageView_button2 = findViewById(R.id.imageView20);
        imageView_button2.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Fr_introduce3.class));
        });
    }
}