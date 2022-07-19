package com.example.talentchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class login extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    JsonObject object;

    Button loginbutton;

    EditText id;
    EditText pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Get.firstInit(this);

        loginbutton = findViewById(R.id.login2);
        loginbutton.setOnClickListener(view -> {

            id = findViewById(R.id.id);
            pw = findViewById(R.id.pw);

            LoginData data = new LoginData(id.getText().toString(), pw.getText().toString());



            Get.login(this, data); // 매칭 정보 불러오기
            handler = new Handler();
            runnable = () -> {
                if(Get.isLogin){

                    SharedPreferences preferences = getSharedPreferences("token", MODE_PRIVATE);
                    String t = preferences.getString("token", "invalid");
                    //Toast.makeText(this, t, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), home.class));

                    here();

                }else {
                    handler.postDelayed(runnable, 100);
                }
            };
            handler.post(runnable);

            //
        });
    }

    void here(){

    }

}

