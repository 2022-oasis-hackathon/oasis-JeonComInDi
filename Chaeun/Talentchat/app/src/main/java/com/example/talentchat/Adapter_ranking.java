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

        //3가지 정보를 선언! (이름, 설명, 이미지)
        TextView name;
        TextView tag;
        ImageView imageView;
        ImageView imageView2;

        public MyVH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.rank_name);
            tag = itemView.findViewById(R.id.rank_tag);
            imageView = itemView.findViewById(R.id.rank_image);
            imageView = itemView.findViewById(R.id.rank_profile);
        }
    }

    //위에서 만든 작은 뷰 하나를 반환 (끝)
    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.base_matching, parent, false));
    }

    //만들어진 뷰에 내용을 넣는 거(for)
    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {

        list = new ArrayList<>();
        list.add("이채은", )


        holder.name.setText(list.get(position).name);

        String description = String.format("#%s #%s",
                list.get(position).tags[0], list.get(position).tags[1]);

        holder.description.setText(description);
    }

    //반복 횟수
    @Override
    public int getItemCount() {
        return 10;
    }
}
