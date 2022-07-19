package com.example.talentchat;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

public class match2 extends AppCompatActivity {

    RecyclerView matchingView2;
    Button receive_button2;
    Button complete_button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match2);

        matchingView2 = findViewById(R.id.matchingView2);
        matchingView2.setLayoutManager(new GridLayoutManager(this, 2));
        matchingView2.setAdapter(new Adapter_ranking());


    }
}

