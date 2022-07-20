package com.example.talentchat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fr_category extends Fragment {

    RecyclerView rankView;

    public Fr_category(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container);

        rankView = view.findViewById(R.id.rankView);
        rankView.setLayoutManager(new GridLayoutManager(view.getContext(), 4));
        rankView.setAdapter(new Adapter_category_button());

        return view;

    }
}
