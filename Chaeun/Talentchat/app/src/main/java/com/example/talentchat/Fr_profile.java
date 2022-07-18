package com.example.talentchat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private Retrofit retrofit;
    private ApiService service;
    private final String TAG = "MainActivityLog";
    private final String URL = "http://192.168.0.29:3001/api/";

    Gson gson;
    JsonObject object;

    View view; // 화면

    public void firstInit() {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
        gson = new Gson();
    }

    // activity_main에 있던 get 코드....
    void get(){

        Call<ResponseBody> call_get = service.getFunc("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmQ1NTBmN2U4Y2Q3Yzk0YzkyNzk1MTUiLCJ1c2VybmFtZSI6ImtpbTIxNTg0IiwiaWF0IjoxNjU4MTQ3MDcyLCJleHAiOjE2NTgyMzM0NzJ9.vrlzRhYtD0KjEQ0jvHHxjZWxIfdZmEtarb8pCfDQTMk");
        call_get.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string(); // json {"": ""}

                        // json
                        JsonObject object = gson.fromJson(result, JsonObject.class);
                        object = object.get("data").getAsJsonObject();

                        // 토스트 메시지
                        /*
                        * result = {"success":true,"message":null,"errors":null
                        * "data":{"_id":"62d550f7e8cd7c94c9279515","name":"kimminju2","age":24,"username":"kim21584","certification":false,"universityName":"None","department":"None","reqtalent":"coding","restalent":"design","degree":100,"reqapply":["kang1584","eun1584"],"resapply":[],"matchuser":[],"__v":2}}
                        * */

                        Toast.makeText(getContext(), object.get("name").getAsString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), object.get("username").getAsString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), object.get("reqtalent").getAsString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), object.get("restalent").getAsString(), Toast.LENGTH_SHORT).show();

                       // view.findViewById()


                        Log.v(TAG, "result = " + result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.v(TAG, "error = " + String.valueOf(response.code()));
                    Toast.makeText(getContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v(TAG, "Fail");
                Toast.makeText(getContext(), "Response Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public Fr_profile(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container);

        firstInit();
        get();
        return view;

    }
}
