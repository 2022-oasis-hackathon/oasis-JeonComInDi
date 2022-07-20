package com.example.talentchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.HashMap;

public class UserProfile extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    JsonObject object;

    ImageView userPic;

    //String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmQ2NjY5NmNhZDBhYTA0NmNkMmQyMjYiLCJ1c2VybmFtZSI6ImtpbTE1ODQiLCJpYXQiOjE2NTgyMjYwNzAsImV4cCI6MTY1ODMxMjQ3MH0.cJmzfNZL2qRLfVT7RAVrGtxoEadQaRle7p4Vx9XZlJs";

    String userid;
    //String username;

    ImageView requestBt_apply;
    ImageView requestBt_accept;
    ImageView requestBt_reject;
    TextView name;
    int mode;

    TextView universityName;
    TextView department;
    TextView age;
    TextView gender;
    TextView reqtalent;
    TextView restalent;
    TextView portfolio;
    TextView contact;

    HashMap<String, Integer> pics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //pics = new HashMap<>();
        //pics.put("강대희", R.drawable.kangdaehee);
        //pics.put("김은빈", R.drawable.kimeunbin);
        //pics.put("이채은", R.drawable.kimhanna);
        //pics.put("차유진", R.drawable.chayujin);
        //pics.put("신형환", R.drawable.sinhyunghwan);
        //pics.put("오동익", R.drawable.odongic);
        //pics.put("이주원", R.drawable.leejuwon);
        //pics.put("임일도", R.drawable.imildo);
        //pics.put("김민주", R.drawable.profile);

        userPic =findViewById(R.id.imageView14);

        requestBt_apply = findViewById(R.id.apply);
        requestBt_accept = findViewById(R.id.accept);
        requestBt_reject = findViewById(R.id.reject);

        universityName = findViewById(R.id.universityName2);
        department = findViewById(R.id.department2);
        age = findViewById(R.id.age2);
        gender = findViewById(R.id.gender2);
        reqtalent = findViewById(R.id.reqtalent2);
        restalent = findViewById(R.id.restalent2);
        portfolio = findViewById(R.id.portfolio2);
        contact = findViewById(R.id.contact);


        //username = getIntent().getStringExtra("name");
        userid = getIntent().getStringExtra("id");
        mode = getIntent().getIntExtra("mode",1);
        if(mode==0) { // 신청 매칭
            requestBt_apply.setVisibility(View.GONE);
            requestBt_accept.setVisibility(View.GONE);
            requestBt_reject.setVisibility(View.GONE);
            universityName.setVisibility(View.VISIBLE);
            department.setVisibility(View.VISIBLE);
            age.setVisibility(View.VISIBLE);
            gender.setVisibility(View.VISIBLE);
            reqtalent.setVisibility(View.VISIBLE);
            restalent.setVisibility(View.VISIBLE);
            portfolio.setVisibility(View.VISIBLE);
            contact.setVisibility(View.GONE);

        }else if(mode==1) { //카테고리
            requestBt_apply.setVisibility(View.VISIBLE);
            requestBt_accept.setVisibility(View.GONE);
            requestBt_reject.setVisibility(View.GONE);
            universityName.setVisibility(View.VISIBLE);
            department.setVisibility(View.VISIBLE);
            age.setVisibility(View.VISIBLE);
            gender.setVisibility(View.VISIBLE);
            reqtalent.setVisibility(View.VISIBLE);
            restalent.setVisibility(View.VISIBLE);
            portfolio.setVisibility(View.VISIBLE);
            contact.setVisibility(View.GONE);

        }else if(mode==2) { //매칭 성사 목록
            requestBt_apply.setVisibility(View.GONE);
            requestBt_accept.setVisibility(View.VISIBLE);
            requestBt_reject.setVisibility(View.VISIBLE);
            universityName.setVisibility(View.VISIBLE);
            department.setVisibility(View.VISIBLE);
            age.setVisibility(View.VISIBLE);
            gender.setVisibility(View.VISIBLE);
            reqtalent.setVisibility(View.VISIBLE);
            restalent.setVisibility(View.VISIBLE);
            portfolio.setVisibility(View.VISIBLE);
            contact.setVisibility(View.GONE);
        }
        else{ // 받은 매칭
            requestBt_apply.setVisibility(View.GONE);
            requestBt_accept.setVisibility(View.GONE);
            requestBt_reject.setVisibility(View.GONE);
            universityName.setVisibility(View.VISIBLE);
            department.setVisibility(View.VISIBLE);
            age.setVisibility(View.VISIBLE);
            gender.setVisibility(View.VISIBLE);
            reqtalent.setVisibility(View.VISIBLE);
            restalent.setVisibility(View.VISIBLE);
            portfolio.setVisibility(View.VISIBLE);
            contact.setVisibility(View.VISIBLE);
        }

        /////////////////////////////////////////////// 2번
        Get.get(this, Get.getToken(this), Get.Menu.user, userid); // 유저 정보 불러오기
        handler = new Handler();
        runnable = () -> {
            if(Get.isReady){

                object = Get.jsonObject[Get.Menu.user.ordinal()];
                if(object==null) return;

                object = object.get("data").getAsJsonObject();
                //Toast.makeText(this, object.toString(), Toast.LENGTH_SHORT).show();
                here();

            }else {
                handler.postDelayed(runnable, 100);
            }
        };
        handler.post(runnable);
        /////////////////////////////////////////////////////////////////
        match2 Match2 = (match2)match2.Match2;
        Match2.finish();
    }

    ///////////////////////////////////////////////////////////////// 3번
    //////////// 불러온 이후
    // 여기다 쓰세요
    void here(){

        TextView username = findViewById(R.id.Username2);
        //{"_id":"62d66696cad0aa046cd2d226","name":"kimminju","age":24,"username":"kim1584","certification":false,"universityName":"None","department":"None","reqtalent":"coding","restalent":"soccer","degree":100,"reqapply":[],"resapply":[],"matchuser":["eun1584"],"__v":15}}
        username.setText(object.get("name").getAsString());

        universityName.setText(object.get("universityName").getAsString());
        department.setText(object.get("department").getAsString());
        age.setText("나이 : "+object.get("age").getAsString());
        gender.setText("성별 : "+object.get("gender").getAsString());
        reqtalent.setText("#want : "+object.get("reqtalent").getAsString());
        restalent.setText("#give : "+object.get("restalent").getAsString());
        portfolio.setText("portfolio : "+object.get("portfolio").getAsString());
        contact.setText("contact : "+object.get("contact").getAsString());

        //userPic.setBackground(ResourcesCompat.getDrawable(getResources(), pics.get(object.get("name").getAsString()), getTheme()));


        requestBt_apply.setOnClickListener( view -> {

            Get.apply(this, object.get("username").getAsString(), Get.Menu.apply); // 매칭 정보 불러오기
            handler = new Handler();
            runnable = () -> {
                if(Get.isSent){
                    Toast.makeText(this, "신청되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    handler.postDelayed(runnable, 100);
                }
            };
            handler.post(runnable);
        });
        requestBt_accept.setOnClickListener( view -> {

            Get.apply(this, object.get("username").getAsString(), Get.Menu.accept); //
            handler = new Handler();
            runnable = () -> {
                if(Get.isSent){
                    Toast.makeText(this, "신청이 수락되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    handler.postDelayed(runnable, 100);
                }
            };
            handler.post(runnable);
        });
        requestBt_reject.setOnClickListener( view -> {

            Get.apply(this, object.get("username").getAsString(), Get.Menu.reject); //
            handler = new Handler();
            runnable = () -> {
                if(Get.isSent){
                    Toast.makeText(this, "신청이 거절되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    handler.postDelayed(runnable, 100);
                }
            };
            handler.post(runnable);
        });

    }


    /////////////////////////////////////////////////////////////////
}