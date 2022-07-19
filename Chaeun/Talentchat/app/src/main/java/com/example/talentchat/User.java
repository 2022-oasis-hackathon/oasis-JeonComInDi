package com.example.talentchat;

public class User {

    String imageUrl; //프로필 사진
    String name; //이름
    String[] tags; //태그 (2개 제한)
    int image;

    public User(String name, String[] tags, int image){
        this.name = name;
        this.tags = tags;
        this.image = image;
    }

}
