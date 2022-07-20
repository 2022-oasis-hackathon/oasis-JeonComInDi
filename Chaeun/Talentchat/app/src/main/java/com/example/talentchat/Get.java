package com.example.talentchat;

import android.content.Context;
import android.content.SharedPreferences;
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

    public static String tempToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmQ2NjY5NmNhZDBhYTA0NmNkMmQyMjYiLCJ1c2VybmFtZSI6ImtpbTE1ODQiLCJpYXQiOjE2NTgyMjk0NjIsImV4cCI6MTY1ODMxNTg2Mn0.4MKFRiuW7ElVKGy1UV0VYEmZzuxl7S1H9I_NcgqqHgY";

    private static Retrofit retrofit;
    private static ApiService service;
    private static final String TAG = "MainActivityLog";
    private static final String URL = "http://192.168.0.29:3001/api/";
    public static Gson gson;
    public static JsonObject jsonObject[];

    public static Context context;
    public static Handler handler;
    public static Runnable runnable;

    public static boolean isReady = false;
    public static boolean isLogin = false;

    public static String getToken(Context context){
        SharedPreferences preferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        return preferences.getString("token", "invalid");
    }


    public static void firstInit(Context c) {

        jsonObject = new JsonObject[15];

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
        me, user, matching_list, category, responser, my_matching, apply, accept, reject
    }


    // activity_main에 있던 get 코드....
    public static void get(Context context, String token, Menu mode, String extra){

        isReady = false;

        if(service==null){
            Toast.makeText(context, "연결 끊김 상태", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<ResponseBody> call_get;
        switch (mode){
            case me: // 나를 불러오는 주소
                call_get = service.getMe(token);
                break;
            case user:
                call_get = service.getUser(token, extra);
                break;
            case matching_list:
                call_get = service.getMyRequset(token);
                break;
            case category:
                call_get = service.getUsersByCategory(token, extra);
                break;
            case responser:
                call_get = service.getMyResponser(token);
                break;
            case my_matching:
                call_get = service.getMyMatching(token);
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
                        jsonObject[mode.ordinal()] = gson.fromJson(result, JsonObject.class);
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



    // activity_main에 있던 get 코드....
    public static void login(Context context, LoginData data){

        isLogin = false;

        if(service==null){
            Toast.makeText(context, "연결 끊김 상태", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<ResponseBody> call_get = service.login(data);

        call_get.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {

                        String result = response.body().string(); // json {"": ""}

                        // json
                        JsonObject r = gson.fromJson(result, JsonObject.class);
                        isLogin = r.get("success").getAsBoolean();
                        if(!isLogin)Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show();
                        else{
                            SharedPreferences preferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
                            preferences.edit().putString("token", r.get("data").getAsString()).apply();
                        }
                       // Toast.makeText(context, r.get("data").getAsString(), Toast.LENGTH_SHORT).show();


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

    static boolean isSent = false;

    public static void apply(Context context, String username, Menu mode){

        Call<ResponseBody> call_get;
        switch (mode){
            case apply: // 나를 불러오는 주소
                call_get = service.reqApply(getToken(context), username);
                break;
            case accept:
                call_get = service.accept(getToken(context), username);
                break;
            case reject:
                call_get = service.reject(getToken(context), username);
                break;
            default:
                call_get = service.reqApply(getToken(context), username);
        }

        isSent = false;

        if(service==null){
            Toast.makeText(context, "연결 끊김 상태", Toast.LENGTH_SHORT).show();
            return;
        }

        call_get.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {

                        String result = response.body().string(); // json {"": ""}
                        //Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                        // json
                        JsonObject r = gson.fromJson(result, JsonObject.class);
                        isSent = r.get("success").getAsBoolean();



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
