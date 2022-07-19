package com.example.talentchat;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class match3 extends AppCompatActivity {


    RecyclerView matchingView3;
    Button receive_button3;
    Button complete_button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match3);


        matchingView3 = findViewById(R.id.matchingView3);
        matchingView3.setLayoutManager(new GridLayoutManager(this, 2));
        matchingView3.setAdapter(new Adapter_ranking());

    }
}