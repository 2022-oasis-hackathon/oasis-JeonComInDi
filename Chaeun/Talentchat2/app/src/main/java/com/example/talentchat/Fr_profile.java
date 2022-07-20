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


/////////////////////////////////////////////////////////////////


    public Fr_profile(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container);

        /////////////////////////////////////////////// 2번
        Get.get(getContext(), Get.getToken(getContext()), Get.Menu.me, null); // 내 정보를 불러오기
        handler = new Handler();
        runnable = () -> {
            if(Get.isReady){

                object = Get.jsonObject[Get.Menu.me.ordinal()];
                if(object==null) return;

                object = object.get("data").getAsJsonObject();
                //Toast.makeText(getContext(), object.toString(), Toast.LENGTH_SHORT).show();
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
        username.setText(object.get("name").getAsString());
        TextView universityName = view.findViewById(R.id.universityName);
        universityName.setText(object.get("universityName").getAsString());
        TextView department = view.findViewById(R.id.department);
        department.setText("학과 : "+object.get("department").getAsString());
        TextView age = view.findViewById(R.id.age);
        age.setText("나이 : "+object.get("age").getAsString());
        TextView gender = view.findViewById(R.id.gender);
        gender.setText("성별 : "+object.get("gender").getAsString());

        TextView restalent = view.findViewById(R.id.restalent3);
        restalent.setText("#want : "+object.get("restalent").getAsString());
        TextView reqtalent = view.findViewById(R.id.reqtalent3);
        reqtalent.setText("#give : "+object.get("reqtalent").getAsString());
        TextView portfolio = view.findViewById(R.id.portfolio);
        portfolio.setText("portfolio : "+object.get("portfolio").getAsString());



    }
    /////////////////////////////////////////////////////////////////


}
