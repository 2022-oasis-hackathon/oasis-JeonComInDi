package com.example.talentchat;

public class User {

    String imageUrl; //프로필 사진
    String name; //이름
    String school; //학교
    int follower; //팔로워 수
    String[] tags; //태그 (2개 제한)

    public User(String name, String school, int follower, String[] tags){
        this.name = name;
        this.school = school;
        this.follower = follower;
        this.tags = tags;
    }

}
