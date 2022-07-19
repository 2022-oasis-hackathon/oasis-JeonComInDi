package com.example.talentchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class category_detail extends AppCompatActivity {

    TextView cate;
    RecyclerView personView;

    public category_detail(){

    }

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_detail);

        //category ID 찾아서 연결
        cate = findViewById(R.id.category_name);

        //category이름 정보를 가져오기
        String category = getIntent().getStringExtra("category");

        //category에 받아온 글자 지정하기
        cate.setText(category);

        personView = findViewById(R.id.personView);
        personView.setLayoutManager(new LinearLayoutManager(this));
        personView.setAdapter(new Adapter_category_detail_button());
    }

}
