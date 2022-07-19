package com.example.talentchat;

public class detail_button {

    String category_req;
    String category_res;
    String username;
    String userid;
    int category_detail_image;

    public detail_button(String category_req, String category_res, int category_detail_image, String name, String id) {
        this.category_req = category_req;
        this.category_res = category_res;
        this.category_detail_image = category_detail_image;
        this.username = name;
        this.userid = id;
    }
}
