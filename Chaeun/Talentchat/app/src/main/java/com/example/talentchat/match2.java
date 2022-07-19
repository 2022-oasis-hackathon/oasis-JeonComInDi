package com.example.talentchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class match2 extends AppCompatActivity {

    RecyclerView matchingView2;

    public match2(){

    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_match, container);

        matchingView2 = view.findViewById(R.id.matchingView2);
        matchingView2.setLayoutManager(new LinearLayoutManager(view.getContext()));
        matchingView2.setAdapter(new Adapter_ranking());

        return view;
    }
}