package com.example.talentchat;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Get {

    /*
    *
    *
    *
    *
    *
    * */






    private static Retrofit retrofit;
    private static ApiService service;
    private static final String TAG = "MainActivityLog";
    private static final String URL = "http://192.168.0.29:3001/api/";
    public static Gson gson;
    public static JsonObject jsonObject;

    public static Context context;
    public static Handler handler;
    public static Runnable runnable;

    public static boolean isReady = false;


    public static void firstInit(Context c) {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
        gson = new Gson();

        context = c;
        handler = new Handler();
    }


    public enum Menu{
        me, user, matching_list
    }


    // activity_main에 있던 get 코드....
    public static void get(Context context, String token, Menu mode){

        isReady = false;

        Call<ResponseBody> call_get;
        switch (mode){
            case me: // 나를 불러오는 주소
                call_get = service.getMe(token);
                break;
            case user:
                call_get = service.getFunc(token);
                break;
            case matching_list:
                call_get = service.getFunc(token);
                break;
            default:
                call_get = service.getFunc(token);
        }

        call_get.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string(); // json {"": ""}

                        // json
                        jsonObject = gson.fromJson(result, JsonObject.class);
                        isReady = true;

                        // 토스트 메시지
                        /*
                         * result = {"success":true,"message":null,"errors":null
                         * "data":{"_id":"62d550f7e8cd7c94c9279515","name":"kimminju2","age":24,"username":"kim21584","certification":false,"universityName":"None","department":"None","reqtalent":"coding","restalent":"design","degree":100,"reqapply":["kang1584","eun1584"],"resapply":[],"matchuser":[],"__v":2}}
                         * */

                        Log.v(TAG, "result = " + result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.v(TAG, "error = " + String.valueOf(response.code()));
                    Toast.makeText(context, "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v(TAG, "Fail");
                Toast.makeText(context, "Response Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
