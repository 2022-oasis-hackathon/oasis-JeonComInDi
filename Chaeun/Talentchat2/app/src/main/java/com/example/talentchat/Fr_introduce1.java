package com.example.talentchat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Fr_introduce1 extends AppCompatActivity {

    public Fr_introduce1(){

    }

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