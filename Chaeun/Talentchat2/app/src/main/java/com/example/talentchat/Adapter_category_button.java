package com.example.talentchat;

import android.content.Intent;
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
import java.util.Locale;

public class Adapter_category_button extends RecyclerView.Adapter<Adapter_category_button.MyVH> {

    ArrayList<button> list;

    //작은 뷰 하나
    static class MyVH extends  RecyclerView.ViewHolder{

        //4가지 정보를 선언! (번호, 이름, 설명, 이미지)
        //Button button;
        TextView tag;
        ImageView imageView;

        public MyVH(@NonNull View itemView) {
            super(itemView);

           // button = itemView.findViewById(R.id.cate_detail_button);
            tag = itemView.findViewById(R.id.cate_detail_tag);
            imageView = itemView.findViewById(R.id.cate_detail_image);
        }
    }

    //위에서 만든 작은 뷰 하나를 반환 (끝)
    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.base_round_button, parent, false));
    }

    //만들어진 뷰에 내용을 넣는 거(for)
    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {

        list = new ArrayList<>();
        //이런거 alt 누르고 드래그 쫙 해서 한 번에 써주면 개꿀
        list.add(new button("coding"    ,   R.drawable.cat_coding       ));
        list.add(new button("photo"    ,   R.drawable.cat_camera       ));
        list.add(new button("health"  ,   R.drawable.cat_health       ));
        list.add(new button("language"     ,   R.drawable.cat_lang         ));
        list.add(new button("music"    ,   R.drawable.cat_music        ));
        list.add(new button("cook"    ,   R.drawable.cat_cook         ));
        list.add(new button("game"    ,   R.drawable.cat_game         ));
        list.add(new button("fashion"    ,   R.drawable.cat_fashion      ));
        list.add(new button("stock"    ,   R.drawable.cat_downtodown   ));
        list.add(new button("english"    ,   R.drawable.cat_english      ));
        list.add(new button("act"    ,   R.drawable.cat_yeongi       ));
        list.add(new button("leisure"    ,   R.drawable.cat_boat         ));
        list.add(new button("korean"    ,   R.drawable.cat_korean       ));
        list.add(new button("craft"    ,   R.drawable.cat_gongyeah     ));
        list.add(new button("volunteer"    ,   R.drawable.cat_volunteer    ));
        list.add(new button("etc"    ,   R.drawable.cat_etc          ));



        holder.tag.setText(list.get(position).category_tag);

        holder.imageView.setBackground(ResourcesCompat.getDrawable(holder.itemView.getResources(),
                list.get(position).category_image, holder.itemView.getContext().getTheme()));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            //category 눌렀을 때 실행하게 하는 코드(#헬스와 같은 정보도 같이 넘어가게)
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), category_detail.class);
                intent.putExtra("category", list.get(holder.getAdapterPosition()).category_tag);
                holder.itemView.getContext().startActivity(intent);
            }
        };

        //holder 안에서 itemView, button, imageView를 눌렀을 때 인식되도록
        holder.itemView.setOnClickListener(listener);
        //holder.button.setOnClickListener(listener);
        holder.imageView.setOnClickListener(listener);
    }

    //반복 횟수
    @Override
    public int getItemCount() {
        return 16;
    }
}
