package com.example.talentchat;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class match2 extends AppCompatActivity {

    RecyclerView matchingView2;
    Button receive_button2;
    Button complete_button2;

    Handler handler;
    Runnable runnable;
    JsonObject object;
    int[] images = {R.drawable.profile_logo, R.drawable.profile_logo, R.drawable.profile_logo, R.drawable.profile_logo, R.drawable.profile_logo};

    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match2);

        list = new ArrayList<>();

        matchingView2 = findViewById(R.id.matchingView2);
        matchingView2.setLayoutManager(new GridLayoutManager(this, 2));



    }

    @Override
    protected void onResume() {
        super.onResume();

        Get.get(this, Get.getToken(this), Get.Menu.responser, null); // 매칭 정보 불러오기
        handler = new Handler();
        runnable = () -> {
            if(Get.isReady){

                object = Get.jsonObject[Get.Menu.responser.ordinal()];
                JsonArray array = object.get("data").getAsJsonArray();
                //Toast.makeText(this, array.toString(), Toast.LENGTH_SHORT).show();

                for(int i=0; i<array.size(); i++){
                    String id = array.get(i).getAsString();
                    list.add(new User(id, images[i], 2));
                    matchingView2.setAdapter(new Adapter_ranking(list));
                }

            }else {
                handler.postDelayed(runnable, 100);
            }
        };
        handler.post(runnable);
    }
}

