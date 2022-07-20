package com.example.talentchat;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Adapter_ranking extends RecyclerView.Adapter<Adapter_ranking.MyVH> {

    ArrayList<User> list;

    public Adapter_ranking(ArrayList<User> list){
        this.list = list;
    }

    //작은 뷰 하나
    static class MyVH extends  RecyclerView.ViewHolder{

        //3가지 정보를 선언! (이름, 설명, 이미지)
        TextView name;
        ImageView imageView;

        public MyVH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.rank_name);
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

        //HashMap<String, Integer> pics = new HashMap<>();
        //pics.put("kang1111", R.drawable.kangdaehee);
        //pics.put("eun2222", R.drawable.kimeunbin);
        //pics.put("lee3333", R.drawable.kimhanna);
        //pics.put("cha4444", R.drawable.chayujin);
        //pics.put("shin7777", R.drawable.sinhyunghwan);
        //pics.put("oh8888", R.drawable.odongic);
        //pics.put("juwon6666", R.drawable.leejuwon);
        //pics.put("lim5555", R.drawable.imildo);
        //holder.name.setText(list.get(position).name);

        //holder.imageView.setBackground(ResourcesCompat.getDrawable(holder.itemView.getResources(),
                //pics.get(list.get(position).name), holder.itemView.getContext().getTheme()));

        View.OnClickListener listener = new View.OnClickListener() {
            Handler handler;
            Runnable runnable;
            JsonObject object;

            @Override
            public void onClick(View view) {
                if(list.get(holder.getAdapterPosition()).mode == 4) {
                    Get.get(holder.itemView.getContext(), Get.getToken(holder.itemView.getContext()), Get.Menu.user, list.get(holder.getAdapterPosition()).name); // 유저 정보 불러오기
                    handler = new Handler();
                    runnable = () -> {
                        if(Get.isReady){

                            object = Get.jsonObject[Get.Menu.user.ordinal()];
                            object = object.get("data").getAsJsonObject();
                            Toast.makeText(holder.itemView.getContext(), object.get("contact").getAsString(), Toast.LENGTH_SHORT).show();

                        }else {
                            handler.postDelayed(runnable, 100);
                        }
                    };
                    handler.post(runnable);

                }
                else {
                    Intent intent = new Intent(holder.itemView.getContext(), UserProfile.class);
                    intent.putExtra("id", list.get(holder.getAdapterPosition()).name);
                    intent.putExtra("mode", list.get(holder.getAdapterPosition()).mode);
                    holder.itemView.getContext().startActivity(intent);
                }
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
