package com.example.talentchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class home extends AppCompatActivity {

    ViewPager2 viewPager;
    TabLayout tabLayout;

    String[] label = {"홈", "카테고리", "매칭", "프로필"};
    int[] icon = {R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_auto_awesome_mosaic_24,
    R.drawable.ic_baseline_contact_mail_24, R.drawable.ic_baseline_person_24};

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

                tab.setText(label[position]);
                tab.setIcon(icon[position]);
            }
        }).attach();
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
                return new Fr_popular();
            case 1: //랭킹
                return new Fr_ranking();
            case 2: //이벤트
                return new Fr_event();
            default: //3번, 추천
                return new Fr_recommend();
        }

    }

    //화면 개수
    @Override
    public int getItemCount() {
        return 4;
    }
}