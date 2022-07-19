package com.example.talentchat;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fr_profile extends Fragment {


//////////////////////////////////////////////////////////////// 1번
    View view; // 화면

    Handler handler;
    Runnable runnable;
    JsonObject object;

    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmQ2NjY5NmNhZDBhYTA0NmNkMmQyMjYiLCJ1c2VybmFtZSI6ImtpbTE1ODQiLCJpYXQiOjE2NTgyMjYwNzAsImV4cCI6MTY1ODMxMjQ3MH0.cJmzfNZL2qRLfVT7RAVrGtxoEadQaRle7p4Vx9XZlJs";
/////////////////////////////////////////////////////////////////


    public Fr_profile(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container);

        /////////////////////////////////////////////// 2번
        Get.get(getContext(), token, Get.Menu.me); // 내 정보를 불러오기
        handler = new Handler();
        runnable = () -> {
            if(Get.isReady){

                object = Get.jsonObject;
                object = object.get("data").getAsJsonObject();
                Toast.makeText(getContext(), object.toString(), Toast.LENGTH_SHORT).show();
                here();

            }else {
                handler.postDelayed(runnable, 100);
            }
        };
        handler.post(runnable);
        /////////////////////////////////////////////////////////////////

        return view;
    }


    ///////////////////////////////////////////////////////////////// 3번
    //////////// 불러온 이후
    // 여기다 쓰세요
    void here(){

        TextView username = view.findViewById(R.id.Username);
        //{"_id":"62d66696cad0aa046cd2d226","name":"kimminju","age":24,"username":"kim1584","certification":false,"universityName":"None","department":"None","reqtalent":"coding","restalent":"soccer","degree":100,"reqapply":[],"resapply":[],"matchuser":["eun1584"],"__v":15}}
        username.setText(object.get("name").getAsString());

    }
    /////////////////////////////////////////////////////////////////


}
