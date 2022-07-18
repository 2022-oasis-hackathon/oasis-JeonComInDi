package com.example.talentchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fr_match extends Fragment {

    RecyclerView matchingView;
    Button receive_button;
    Button complete_button;

    public Fr_match(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_match, container);

        matchingView = view.findViewById(R.id.matchingView);
        matchingView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        matchingView.setAdapter(new Adapter_ranking());

        return view;

        receive_button = findViewById(R.id.receive_matching);
        receive_button.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Fr_match2.class));
        });

        complete_button = findViewById(R.id.matching_complete);
        complete_button.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Fr_match3.class));
        });

    }
}
