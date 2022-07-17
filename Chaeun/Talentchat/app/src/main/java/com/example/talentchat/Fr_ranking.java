package com.example.talentchat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fr_ranking extends Fragment {

    RecyclerView rankView;

    public Fr_ranking(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ranking, container);

        rankView = view.findViewById(R.id.rankView);
        rankView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rankView.setAdapter(new Adapter_ranking());

        return view;

    }
}
