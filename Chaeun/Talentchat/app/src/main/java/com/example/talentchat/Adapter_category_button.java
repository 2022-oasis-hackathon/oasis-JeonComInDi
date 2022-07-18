package com.example.talentchat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_category_button extends RecyclerView.Adapter<Adapter_category_button.MyVH> {

    ArrayList<button> list;

    //작은 뷰 하나
    static class MyVH extends  RecyclerView.ViewHolder{

        //4가지 정보를 선언! (번호, 이름, 설명, 이미지)
        Button button;
        TextView tag;
        ImageView imageView;

        public MyVH(@NonNull View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.category_button);
            tag = itemView.findViewById(R.id.category_tag);
            imageView = itemView.findViewById(R.id.category_image);
        }
    }

    //위에서 만든 작은 뷰 하나를 반환 (끝)
    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.round_button, parent, false));
    }

    //만들어진 뷰에 내용을 넣는 거(for)
    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {

        list = new ArrayList<>();
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));
        list.add(new button("Exercise", R.drawable.ic_baseline_person_24));

        holder.tag.setText(list.get(position).category_tag);

        holder.imageView.setBackground(ResourcesCompat.getDrawable(holder.itemView.getResources(), list.get(position).category_image, holder.itemView.getContext().getTheme()));

    }

    //반복 횟수
    @Override
    public int getItemCount() {
        return 16;
    }
}
