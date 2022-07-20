package com.example.talentchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class introduce1 extends AppCompatActivity {

    ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce1);

        Get.firstInit(this);

        viewPager2.setAdapter(new Frags(this));
}

    class Frags2 extends FragmentStateAdapter{
        public Frags2(@NonNull FragmentActivity fragmentActivity) {
                super(fragmentActivity);
            }

    //화면 속 화면
    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position){
            case 0: // 인기
                return new Fr_introduce1();
            case 1: // 카테고리
                return new Fr_introduce2();
            default: // 프로필
                return new Fr_introduce3();
        }

    }

    //화면 개수
    @Override
    public int getItemCount() {
        return 3;
    }
}