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
import java.util.HashMap;

public class Adapter_category_detail_button extends RecyclerView.Adapter<Adapter_category_detail_button.MyVH> {

    ArrayList<detail_button> list;

    public Adapter_category_detail_button(ArrayList<detail_button> list){
        this.list = list;
    }

    //작은 뷰 하나
    static class MyVH extends RecyclerView.ViewHolder{

        //3가지 정보를 선언! (번호, 이름, 설명, 이미지)
        TextView req;
        TextView res;
        ImageView detail_image;

        public MyVH(@NonNull View itemView) {
            super(itemView);

            req = itemView.findViewById(R.id.reqtalent);
            res = itemView.findViewById(R.id.restalent);
            detail_image = itemView.findViewById(R.id.cate_image);
        }
    }

    //위에서 만든 작은 뷰 하나를 반환 (끝)
    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.base_category_detail, parent, false));
    }

    //만들어진 뷰에 내용을 넣는 거(for)
    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {

        HashMap<String, Integer> pics = new HashMap<>();
        pics.put("강대희", R.drawable.kangdaehee);
        pics.put("김은빈", R.drawable.kimeunbin);
        pics.put("이채은", R.drawable.kimhanna);
        pics.put("차유진", R.drawable.chayujin);
        pics.put("신형환", R.drawable.sinhyunghwan);
        pics.put("오동익", R.drawable.odongic);
        pics.put("이주원", R.drawable.leejuwon);
        pics.put("임일도", R.drawable.imildo);
        pics.put("김민주", R.drawable.profile);

//        list = new ArrayList<>();
//        list.add(new detail_button("#헬스", "#코딩", R.drawable.profile_logo));
//        list.add(new detail_button("#헬스", "#코딩", R.drawable.profile_logo));

        holder.req.setText("#"+list.get(position).category_req);
        holder.res.setText("#"+list.get(position).category_res);

        //이미지
        holder.detail_image.setBackground(ResourcesCompat.getDrawable(holder.itemView.getResources(),
                pics.get(list.get(holder.getAdapterPosition()).username), holder.itemView.getContext().getTheme()));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), UserProfile.class);
                intent.putExtra("name", list.get(holder.getAdapterPosition()).username);
                intent.putExtra("id", list.get(holder.getAdapterPosition()).userid);
                intent.putExtra("mode", list.get(holder.getAdapterPosition()).mode);
                holder.itemView.getContext().startActivity(intent);
            }
        };

        holder.itemView.setOnClickListener(listener);
    }

    //반복 횟수
    @Override
    public int getItemCount() {
        return list.size();
    }
}
