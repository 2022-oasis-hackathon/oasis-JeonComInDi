package com.example.talentchat;

public class User {

    String imageUrl; //프로필 사진
    String name; //이름
    int image;
    int mode;

    public User(String name, int image, int mode){
        this.name = name;
        this.image = image;
        this.mode = mode;
    }

}
