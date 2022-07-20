package com.example.talentchat;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class match3 extends AppCompatActivity {


    RecyclerView matchingView3;
    Button receive_button3;
    Button complete_button3;

    Handler handler;
    Runnable runnable;
    JsonObject object;
    int[] images = {R.drawable.profile_logo};

    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match3);


        matchingView3 = findViewById(R.id.matchingView3);
        matchingView3.setLayoutManager(new GridLayoutManager(this, 2));


    }

    @Override
    protected void onResume() {
        super.onResume();
        list = new ArrayList<>();
        Get.get(this, Get.getToken(this), Get.Menu.my_matching, null); // 매칭 정보 불러오기
        handler = new Handler();
        runnable = () -> {
            if(Get.isReady){

                object = Get.jsonObject[Get.Menu.my_matching.ordinal()];
                JsonArray array = object.get("data").getAsJsonArray();
                //Toast.makeText(this, array.toString(), Toast.LENGTH_SHORT).show();

                for(int i=0; i<array.size(); i++){
                    String id = array.get(i).getAsString();
                    list.add(new User(id, images[i], 3));
                    matchingView3.setAdapter(new Adapter_ranking(list));
                }

            }else {
                handler.postDelayed(runnable, 100);
            }
        };
        handler.post(runnable);
    }
}