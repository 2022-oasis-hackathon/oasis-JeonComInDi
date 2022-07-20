package com.example.talentchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class category_detail extends AppCompatActivity {

    TextView cate;
    RecyclerView personView;


    Handler handler;
    Runnable runnable;
    JsonObject object;

    ArrayList<detail_button> list;

    public category_detail(){

    }

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_detail);

        list = new ArrayList<>();

        //category ID 찾아서 연결
        cate = findViewById(R.id.category_name);

        //category이름 정보를 가져오기
        String category = getIntent().getStringExtra("category");

        //category에 받아온 글자 지정하기
        cate.setText(category);

        personView = findViewById(R.id.personView);
        personView.setLayoutManager(new LinearLayoutManager(this));



        /////////////////////////////////////////////// 2번
        Get.get(this, Get.getToken(this), Get.Menu.category, category); // 카테고리별 불러오기
        handler = new Handler();
        runnable = () -> {
            if(Get.isReady){

                object = Get.jsonObject[Get.Menu.category.ordinal()];
                JsonArray users = object.get("data").getAsJsonArray();
                //Toast.makeText(this, users.toString(), Toast.LENGTH_SHORT).show();

                for(int i=0; i<users.size(); i++){
                    String req = users.get(i).getAsJsonObject().get("reqtalent").getAsString();
                    String res = users.get(i).getAsJsonObject().get("restalent").getAsString();
                    String name = users.get(i).getAsJsonObject().get("name").getAsString();
                    String id = users.get(i).getAsJsonObject().get("username").getAsString();
                    list.add(new detail_button(req, res, R.drawable.ic_baseline_home_24, name, id, 1));
                    personView.setAdapter(new Adapter_category_detail_button(list));
                }


            }else {
                handler.postDelayed(runnable, 100);
            }
        };
        handler.post(runnable);
        /////////////////////////////////////////////////////////////////


    }

}
