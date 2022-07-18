package com.example.talentchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class home extends AppCompatActivity {

    ViewPager2 viewPager;
    TabLayout tabLayout;

    //private int[] icon;
    int[] icon = {R.drawable.home_logo, R.drawable.category_logo,
    R.drawable.matching_logo, R.drawable.profile_logo};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        viewPager = findViewById(R.id.view);
        viewPager.setAdapter(new Frags(this));

        tabLayout = findViewById(R.id.tabLayout);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                tab.setIcon(icon[position]);
            }
        }).attach();

        Gson gson = new Gson();
        String json = "{\n" +
                "\"name\": \"이름\",\n" +
                "\"age\": 24,\n" +
                "\"username\": \"유저id\",\n" +
                "\"password\": \"비번\",\n" +
                "\"certification\": false,\n" +
                "\"universityName\": \"전남대학교\",\n" +
                "\"department\": \"소프트웨어공학과\",\n" +
                "\"reqtalent\": \"코딩\",\n" +
                "\"restalent\": \"음악\",\n" +
                "\"degree\": 10,\n" +
                "\"reqapply\": [\"가\", \"나\"],\n" +
                "\"resapply\": [\"가\", \"나\"],\n" +
                "\"matchuser\": [\"가\", \"나\"]\n" +
                "}";
        JsonObject object = gson.fromJson(json, JsonObject.class);
        Toast.makeText(this, object.get("name").getAsString(), Toast.LENGTH_SHORT).show();
    }
}

class Frags extends FragmentStateAdapter{

    public Frags(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //화면 속 화면
    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position){
            case 0: //인기
                return new Fr_home();
            case 1: //랭킹
                return new Fr_category();
            case 2: //이벤트
                return new Fr_match();
            default: //3번, 추천
                return new Fr_profile();
        }

    }

    //화면 개수
    @Override
    public int getItemCount() {
        return 4;
    }
}