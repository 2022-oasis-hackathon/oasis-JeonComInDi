package com.example.talentchat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_ranking extends RecyclerView.Adapter<Adapter_ranking.MyVH> {

    ArrayList<User> list;

    //작은 뷰 하나
    static class MyVH extends  RecyclerView.ViewHolder{

        //4가지 정보를 선언! (번호, 이름, 설명, 이미지)
        TextView rankNum;
        TextView name;
        TextView description;
        ImageView imageView;

        public MyVH(@NonNull View itemView) {
            super(itemView);

            rankNum = itemView.findViewById(R.id.rank_num);
            name = itemView.findViewById(R.id.rank_name);
            description = itemView.findViewById(R.id.rank_university);
            imageView = itemView.findViewById(R.id.rank_image);
        }
    }

    //위에서 만든 작은 뷰 하나를 반환 (끝)
    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.base_ranking, parent, false));
    }

    //만들어진 뷰에 내용을 넣는 거(for)
    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {

        list = new ArrayList<>();
        list.add(new User("이채은", "전남대학교", 12345, new String[]{"예술", "음악"}));
        list.add(new User("강대희", "전남대학교", 12345, new String[]{"예술", "음악"}));
        list.add(new User("김민주", "전남대학교", 12345, new String[]{"예술", "음악"}));
        list.add(new User("김민주", "전남대학교", 12345, new String[]{"예술", "음악"}));
        list.add(new User("김민주", "전남대학교", 12345, new String[]{"예술", "음악"}));
        list.add(new User("김민주", "전남대학교", 12345, new String[]{"예술", "음악"}));
        list.add(new User("김민주", "전남대학교", 12345, new String[]{"예술", "음악"}));
        list.add(new User("김민주", "전남대학교", 12345, new String[]{"예술", "음악"}));
        list.add(new User("김민주", "전남대학교", 12345, new String[]{"예술", "음악"}));
        list.add(new User("김민주", "전남대학교", 12345, new String[]{"예술", "음악"}));

        holder.rankNum.setText(String.valueOf(position+1));
        holder.name.setText(list.get(position).name);

        String description = String.format("%s | ● %s | #%s #%s",
                list.get(position).school, list.get(position).follower,
                list.get(position).tags[0], list.get(position).tags[1]);

        holder.description.setText(description);
    }

    //반복 횟수
    @Override
    public int getItemCount() {
        return 10;
    }
}
