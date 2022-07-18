package com.example.talentchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class category_detail extends AppCompatActivity {

    TextView cate;

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
    }
}