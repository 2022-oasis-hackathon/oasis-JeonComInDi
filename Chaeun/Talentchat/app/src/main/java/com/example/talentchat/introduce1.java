package com.example.talentchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class introduce1 extends AppCompatActivity {

    ImageView imageView_button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce1);

        imageView_button1 = findViewById(R.id.imageView19);
        imageView_button1.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), introduce2.class));
        });
    }

}