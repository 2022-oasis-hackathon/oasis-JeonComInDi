package com.example.talentchat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fr_match extends Fragment {

    View view;
    RecyclerView matchingView;
    Button receive_button;
    Button complete_button;

    Handler handler;
    Runnable runnable;
    JsonObject object;
    int[] images = {R.drawable.profile_logo, R.drawable.profile_logo, R.drawable.profile_logo, R.drawable.profile_logo, R.drawable.profile_logo};

   // String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmQ2NjY5NmNhZDBhYTA0NmNkMmQyMjYiLCJ1c2VybmFtZSI6ImtpbTE1ODQiLCJpYXQiOjE2NTgyMjYwNzAsImV4cCI6MTY1ODMxMjQ3MH0.cJmzfNZL2qRLfVT7RAVrGtxoEadQaRle7p4Vx9XZlJs";

    ArrayList<User> list;

    public Fr_match(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_match, container);

        matchingView = view.findViewById(R.id.matchingView);
        matchingView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        receive_button = view.findViewById(R.id.receive_matching);
        receive_button.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), match2.class));
        });

        complete_button = view.findViewById(R.id.matching_complete);
        complete_button.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), match3.class));
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        list = new ArrayList<>();

        Get.get(getContext(), Get.getToken(getContext()), Get.Menu.matching_list, null); // 매칭 정보 불러오기
        handler = new Handler();
        runnable = () -> {
            if(Get.isReady){

                object = Get.jsonObject[Get.Menu.matching_list.ordinal()];
                JsonArray array = object.get("data").getAsJsonArray();
                //Toast.makeText(getContext(), array.toString(), Toast.LENGTH_SHORT).show();

                for(int i=0; i<array.size(); i++){
                    String id = array.get(i).getAsString();
                    list.add(new User(id, images[i], 0));
                    matchingView.setAdapter(new Adapter_ranking(list));
                }
                here();

            }else {
                handler.postDelayed(runnable, 100);
            }
        };
        handler.post(runnable);
    }

    void here(){


        //{"_id":"62d66696cad0aa046cd2d226","name":"kimminju","age":24,"username":"kim1584","certification":false,"universityName":"None","department":"None","reqtalent":"coding","restalent":"soccer","degree":100,"reqapply":[],"resapply":[],"matchuser":["eun1584"],"__v":15}}


    }
}
